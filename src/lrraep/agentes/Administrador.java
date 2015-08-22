/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.List;
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
    public void setup(){
        
        /** Verifica se há novos cadastros pendentes no AVA
         * Coloca Perfil de Estudante nesses cadastros pendentes
         * Lota usuários recem cadastrados na sala de experimentos
         */
        addBehaviour(new CyclicBehaviour(this) {
            
            @Override
            public void action() {
                MdlUserDAO userDAO = new MdlUserDAO();
                System.out.println("Verificando se há novos cadastros de usuários pendentes...");
                List<MdlUser> CadastrosPendentes = userDAO.recuperarTodosCadastrosPendentes();
                System.out.println(CadastrosPendentes.size()+" Foram recuperados da base de dados...");
                
                
                System.out.println("Iniciando processo de recuperação do ID do Contexto...");
                MdlContextDAO contextDAO = new MdlContextDAO();
                Long ContextID = contextDAO.recuperarContextID(Configuration.CURSO, Configuration.CONTEXT_LEVEL);
                
                System.out.println("Iniciando processo de atribuição de perfil de Estudante");
                MdlUserEnrolmentsDAO enrolDAO = new MdlUserEnrolmentsDAO();
                enrolDAO.AtribuirPerfilCadastrosPendentes(CadastrosPendentes);
                System.out.println("Processo finalizado.");
                
                System.out.println("Iniciando processo de concessão de acesso ao AVA...");
                int registrosAfetados = userDAO.autorizarCadastroPendentes(CadastrosPendentes);
                System.out.println(registrosAfetados+" Foram atualizados...");
                
                System.out.println("Iniciando processo de lotação de usuário com perfil de estudante na sala de experimento...");
                MdlRoleAssignmentsDAO roleAssignmentsDAO = new MdlRoleAssignmentsDAO();
                roleAssignmentsDAO.LotarUsuarioNaSala(CadastrosPendentes, ContextID);
                
                System.out.println("Processo Finalizado...");
                System.out.println("Encerrando as atividades do agente Admnistrador...");
                System.out.println("Dormindo!");
                block();
            }
        });
        
        
    }
    
}
