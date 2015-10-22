/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Proxy;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RomeroGomes
 */
public class SSH {
    // Logger

    private final static Logger LOGGER =
            Logger.getLogger(SSH.class.getName());
    public Session session;
    // 
    private int assigned_port;
    private final int local_port = 65022;
    // Remote host and port
    private final int remote_port = 22;
    private final String remote_host = "192.168.0.107";
    private final JSch jsch = new JSch();

    public int connect() {


        //final String remote_host = "192.168.0.109";

        try {


            // Create SSH session.  Port 22 is your SSH port which
            // is open in your firewall setup.
            session = jsch.getSession("thais", remote_host, 22);
            session.setPassword("123");
            //session = jsch.getSession("romero", remote_host, 22);
            //session.setPassword("123");

            // Additional SSH options.  See your ssh_config manual for
            // more options.  Set options according to your requirements.
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("Compression", "yes");
            config.put("ConnectionAttempts", "2");
            //config.put("timeout", "0");
            //session.setProxy(new ProxyHTTP("172.23.0.254", 2013).setUserPasswd("romero", "admin"));
            session.setConfig(config);

            if (!session.isConnected()) {
                // Connect
                
                System.out.println("" + session.getClientVersion());

                session.connect();

                assigned_port = session.setPortForwardingL(local_port,
                        remote_host, remote_port);
                
                System.out.println("SESSSAAAAAOOOOO: " + session.isConnected());
                
                System.out.println("PortForwardingL: "+session.getPortForwardingL().toString());
                
                if (assigned_port == 0) {
                    LOGGER.log(Level.SEVERE, "Port forwarding failed !");
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }

            // Create the tunnel through port forwarding.  
            // This is basically instructing jsch session to send 
            // data received from local_port in the local machine to 
            // remote_port of the remote_host
            // assigned_port is the port assigned by jsch for use,
            // it may not always be the same as
            // local_port.



        } catch (JSchException e) {
            //MailUtil.sendEmailError(e);
            LOGGER.log(Level.SEVERE, e.getMessage());
            return -1;
        }
        //return 0;

    }

    public Boolean isConnected() {
        System.out.println("Sessao SSH: " + session.isConnected());
        return session.isConnected();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
