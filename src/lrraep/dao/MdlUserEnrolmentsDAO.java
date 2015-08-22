/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.dao;

import java.util.Calendar;
import java.util.List;
import lrraep.bean.MdlUser;
import lrraep.bean.MdlUserEnrolments;
import lrraep.utils.Configuration;
import lrraep.utils.GenericDAO;

/**
 *
 * @author Romero
 */
public class MdlUserEnrolmentsDAO extends GenericDAO<MdlUserEnrolments> {

    public void AtribuirPerfilCadastrosPendentes(List<MdlUser> ListaCadastrosPendentes) {

        for (MdlUser mdlUser : ListaCadastrosPendentes) {
            Long UserID = mdlUser.getId();
            System.out.println(""+UserID);
            
            MdlUserEnrolments enrol = new MdlUserEnrolments();
            enrol.setStatus(Configuration.CONFIRMADO); //Autorizado a acessar a sala/curso
            enrol.setEnrolid(Configuration.ENROL); //ID Metodo de Autenticação que a gente criar
            enrol.setUserid(UserID);//Id do usuario
            enrol.setTimestart(0);
            enrol.setTimeend(0);
            enrol.setModifierid(2);
            enrol.setTimecreated(Calendar.getInstance().getTimeInMillis());
            enrol.setTimemodified(0);
            
            persist(enrol);
        }

    }

}
