package tools;

import entityBeans.ComboCategory;
import java.util.Comparator;

public class TriParComboCategory implements Comparator<ComboCategory>{

    @Override
    public int compare(ComboCategory obj, ComboCategory cible) {
        int d01 = obj.getNumber();
        int d02 = cible.getNumber();
        int delta = d01 - d02;
        if(delta < 0){
            return -1;
        }else if(delta > 0){
            return 1;
        }else{
            return 0;
        }
    }
    
}
