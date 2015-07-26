
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lrraep.bean.MdlUser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leandro
 */
public class Lrraep {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("moodle");
        EntityManager manager = factory.createEntityManager();

        Long id = new Long("2");
        MdlUser encontrada = manager.find(MdlUser.class, id);
        System.out.println("Username: " + encontrada.getUsername());

        List <MdlUser> lista = manager.createQuery("from MdlUser").getResultList();

        for (MdlUser tarefa : lista) {
            System.out.println(tarefa.getUsername());
        }

        manager.close();
        factory.close();
    }

}
