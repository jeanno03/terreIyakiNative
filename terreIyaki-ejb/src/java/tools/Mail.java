/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.Serializable;

/**
 *
 * @author jeanno
 */
public class Mail implements Serializable {
   private String mail;
   private String mdp;

    public Mail() {
    }

    public Mail(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Mail{" + "mail=" + mail + ", mdp=" + mdp + '}';
    }
   
   
    
}
