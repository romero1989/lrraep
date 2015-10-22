/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import lrraep.bean.MdlLrraep;
import lrraep.bean.MdlLrraepExecucao;
import lrraep.dao.MdlLrraepExecucaoDAO;
import lrraep.utils.SSH;

/**
 *
 * @author Romero
 */
public class Robo extends Agent {

    @Override
    public void setup() {
        SSH ssh = new SSH();
        ssh.connect();
        int i = 0;
        
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                
                MdlLrraepExecucaoDAO ExecucaoDAO = new MdlLrraepExecucaoDAO();
                MdlLrraepExecucao codigoExecucao = ExecucaoDAO.getCodigoExecucao();

                System.out.println("Codigo: " + codigoExecucao.getCodigoFonte());

                FileWriter arq;

                try {

                    
                    

                    //String teste = "/home/thais/Área de Trabalho/exempo.py";
                    
                    if (ssh.isConnected()) {
                        //arq = new FileWriter("/Users/leandro/Desktop/executarRomero.py");
                        //arq = new FileWriter("/home/thais/Área de Trabalho/executarRomero.py");
                        arq = new FileWriter("/Users/leandro/Desktop/executar"+i+".py");
                        PrintWriter gravarArq = new PrintWriter(arq);

                        gravarArq.printf("ID = '00:16:53:11:25:73'\n");
                        gravarArq.printf("from nxt.bluesock import BlueSock\n");
                        gravarArq.printf("sock = BlueSock(ID)\n");
                        gravarArq.printf("if sock:\n");
                        gravarArq.printf("brick = sock.connect()\n");
                        gravarArq.printf("brick.play_tone_and_wait(40,1000)\n");
                        gravarArq.printf("sock.close()\n");
                        gravarArq.printf("else:\n");
                        gravarArq.printf("print 'No NXT bricks found'\n");
                        arq.close();
                        
                        //String path = null;

                        //System.out.print("\n#Mkdir criado em java#");
                        System.out.print("\n##Inciando processo");
                        Runtime run = Runtime.getRuntime();

                        //if (path == null) {
                            //System.out.print("\n\nÉ necessário informar o nome da pasta a ser criada");
                            //System.out.print("\nSintaxe: \n\t mkdirInJava nome-pasta");
                            //System.exit(0);
                        //}

                        try {
                            //System.out.print("\n#Criando arquivo " + path);

                            String command = "python /opt/exemplo.py";
                            //System.out.println(" "+command);
                            run.exec(command);
                            //System.out.println(""+exec.getOutputStream());
                            System.out.print("\n##Processo Finalizado.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //gravarArq.printf(codigoExecucao.getCodigoFonte());
                    //gravarArq.printf("+-------------+%n");
                    

                } catch (IOException ex) {
                    Logger.getLogger(Robo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                block(10000);
                
            }

        });
    }
}
