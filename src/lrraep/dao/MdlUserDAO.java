/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.dao;

import java.util.Set;
import lrraep.bean.MdlUser;
import lrraep.utils.GenericDAO;

/**
 *
 * @author Romero
 */
public class MdlUserDAO extends GenericDAO<MdlUser> {
 
    public void printListUsername(){
        Set<MdlUser> list = list();
        
        for (MdlUser mdlUser : list) {
            System.out.println(""+mdlUser.getUsername());
        }
    }
    
}
