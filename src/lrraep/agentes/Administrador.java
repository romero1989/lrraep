/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lrraep.bean.MdlUser;
import lrraep.dao.MdlContextDAO;
import lrraep.dao.MdlRoleAssignmentsDAO;
import lrraep.dao.MdlUserDAO;
import lrraep.dao.MdlUserEnrolmentsDAO;
import lrraep.utils.Configuration;

/**
 *
 * @author Romero
 */
public class Administrador extends Agent {

    @Override
    public void setup() {

        /**
         * Registra o agente nas paginas amarelas
         */
        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                registrarYellowPages("Administrador");
            }
        });

        /**
         * Verifica se há novos cadastros pendentes no AVA Coloca Perfil de
         * Estudante nesses cadastros pendentes Lota usuários recem cadastrados
         * na sala de experimentos
         */
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                MdlUserDAO userDAO = new MdlUserDAO();
                System.out.println("Verificando se há novos cadastros de usuários pendentes...");
                List<MdlUser> CadastrosPendentes = userDAO.recuperarTodosCadastrosPendentes();
                System.out.println(CadastrosPendentes.size() + " Foram recuperados da base de dados...");

                if (!CadastrosPendentes.isEmpty()) {

                    System.out.println("Iniciando processo de recuperação do ID do Contexto...");
                    MdlContextDAO contextDAO = new MdlContextDAO();
                    Long ContextID = contextDAO.recuperarContextID(Configuration.CURSO, Configuration.CONTEXT_LEVEL);

                    System.out.println("Iniciando processo de atribuição de perfil de Estudante");
                    MdlUserEnrolmentsDAO enrolDAO = new MdlUserEnrolmentsDAO();
                    enrolDAO.AtribuirPerfilCadastrosPendentes(CadastrosPendentes);
                    System.out.println("Processo finalizado.");

                    System.out.println("Iniciando processo de concessão de acesso ao AVA...");
                    int registrosAfetados = userDAO.autorizarCadastroPendentes(CadastrosPendentes);
                    System.out.println(registrosAfetados + " Foram atualizados...");

                    System.out.println("Iniciando processo de lotação de usuário com perfil de estudante na sala de experimento...");
                    MdlRoleAssignmentsDAO roleAssignmentsDAO = new MdlRoleAssignmentsDAO();
                    roleAssignmentsDAO.LotarUsuarioNaSala(CadastrosPendentes, ContextID);

                    System.out.println("Processo Finalizado...");
                    System.out.println("Encerrando as atividades do agente Admnistrador...");

                    System.out.println("Avisando o Agente Assistente sobre novo usuário adicionado...");
                    //comunicarAgente("Assistente", "new", ACLMessage.INFORM);

                    System.out.println("Dormindo!");
                    block();
                } else {
                    System.out.println("Não há novos cadastros...");
                    System.out.println("Dormindo!");
                    block();
                }
            }
        });

    }

    private void registrarYellowPages(String ID) {
        try {

            ServiceDescription sd = new ServiceDescription(); //Seu servi ̧co  ́e salvar vidas
            sd.setType(ID);
            sd.setName(this.getLocalName());

            DFAgentDescription dfd = new DFAgentDescription();
            dfd.addServices(sd);
            DFService.register(this, dfd);

        } catch (FIPAException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que realiza a busca nas pa ́ginas amarelas da plataforma
     */
    private String buscaAgenteResponsavel(final String Pedido) {
        String AgentName = "";

        ServiceDescription sd = new ServiceDescription();
        sd.setType(Pedido);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        try {
            DFAgentDescription[] resultado = DFService.search(this, dfd);

            if (resultado.length != 0) {
                AgentName = resultado[0].getName().getLocalName();
            } else {
                AgentName = "-1";
            }

        } catch (FIPAException e) {
            e.getMessage();
        } finally {
            return AgentName;
        }

    }

    private void comunicarAgente(String Servico, String content, Integer performative) {
        String AgenteResponsavel = buscaAgenteResponsavel(Servico);

        if (!AgenteResponsavel.equals("-1")) {
            ACLMessage msg = new ACLMessage(performative);
            msg.addReceiver(new AID(AgenteResponsavel, AID.ISLOCALNAME));
            msg.setContent(content);
            send(msg);
        } else {
            System.out.println("Nao encontrou o agente responsavel...");
        }
    }

}
