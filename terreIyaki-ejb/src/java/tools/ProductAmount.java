/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author jeanno
 */
public class ProductAmount implements Serializable {
  private float montantHT;
  private float montantTTC;

    public ProductAmount() {
    }

    public ProductAmount(float montantHT, float montantTTC) {
        this.montantHT = montantHT;
        this.montantTTC = montantTTC;
    }

    public float getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(float montantHT) {
        this.montantHT = montantHT;
    }

    public float getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(float montantTTC) {
        this.montantTTC = montantTTC;
    }

    @Override
    public String toString() {
        return "ProductAMount{" + "montantHT=" + montantHT + ", montantTTC=" + montantTTC + '}';
    }
  
  
    
}
