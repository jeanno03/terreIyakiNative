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
public class MyLog implements Serializable {
 
    private int firstNumber;
    private int secondNumber;
    private int thirdNumber;
    private int fourthNumber;

    public MyLog() {
    }

    public MyLog(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
        this.fourthNumber = fourthNumber;
    }

    public int getFourthNumber() {
        return fourthNumber;
    }

    public void setFourthNumber(int fourthNumber) {
        this.fourthNumber = fourthNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(int thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    @Override
    public String toString() {
        return "MyLog{" + "firstNumber=" + firstNumber + ", secondNumber=" + secondNumber + ", thirdNumber=" + thirdNumber + ", fourthNumber=" + fourthNumber + '}';
    }
    
    
    
    
}
