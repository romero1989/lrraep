/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.dao;

import lrraep.bean.MdlLrraepMovimentoRobo;
import lrraep.utils.GenericDAO;

/**
 *
 * @author Romero
 */
public class MdlLrraepMovimentoRoboDAO extends GenericDAO<MdlLrraepMovimentoRobo> {
 
    public void initValues(){
        MdlLrraepMovimentoRobo mov1 = new MdlLrraepMovimentoRobo();
        MdlLrraepMovimentoRobo mov2 = new MdlLrraepMovimentoRobo();
        MdlLrraepMovimentoRobo mov3 = new MdlLrraepMovimentoRobo();
        MdlLrraepMovimentoRobo mov4 = new MdlLrraepMovimentoRobo();
               
        mov1.setAcao("Frente");
        mov2.setAcao("Trás");
        mov3.setAcao("Direta");
        mov4.setAcao("Esquerda");
        
        merge(mov1);
        merge(mov2);
        merge(mov3);
        merge(mov4);        
    }
    
}
