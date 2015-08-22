/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.dao;

import java.util.Calendar;
import java.util.List;
import lrraep.bean.MdlRoleAssignments;
import lrraep.bean.MdlUser;
import lrraep.utils.Configuration;
import lrraep.utils.GenericDAO;

/**
 *
 * @author Romero
 */
public class MdlRoleAssignmentsDAO extends GenericDAO<MdlRoleAssignments> {

    public void LotarUsuarioNaSala(List<MdlUser> ListaCadastrosPendentes, Long ContextID) {
        for (MdlUser mdlUser : ListaCadastrosPendentes) {
            Long UserID = mdlUser.getId();
            
            MdlRoleAssignments assignments = new MdlRoleAssignments();
            assignments.setRoleid(Configuration.STUDENT);
            assignments.setContextid(ContextID);
            assignments.setUserid(UserID);
            assignments.setTimemodified(Calendar.getInstance().getTimeInMillis());
            assignments.setModifierid(Configuration.MODIFIED);
            assignments.setComponent("");
            assignments.setItemid(0);
            assignments.setSortorder(0);
            persist(assignments);
        }
    }

}
