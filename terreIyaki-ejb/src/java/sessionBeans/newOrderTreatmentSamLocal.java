/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Account;
import entityBeans.MyOrder;
import entityBeans.MyTable;
import entityBeans.Status;
import javax.ejb.Local;
import tools.CustomException;

/**
 *
 * @author samira
 */
@Local
public interface newOrderTreatmentSamLocal {

    public MyTable getSeletedTableNumber(int numeroTable) throws CustomException;

    public MyOrder newOrder(MyTable table, Account monCompte) throws CustomException;

    public Account getAccountByCode(int leCode) throws CustomException;
    
}
