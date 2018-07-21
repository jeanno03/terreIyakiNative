package tools;

import java.util.HashMap;

public class CustomException extends Exception {
    public static final int SQL_ERR = 10;
    public static final int USER_ERR = 20;
    
    
    private int typeErreur;
    private HashMap<String, String> erreurs;

    public CustomException() {
        super();
        erreurs = new HashMap<>();
    }

    public CustomException(String message) {
        super(message);
        erreurs = new HashMap<>();
    }

    public CustomException(int typeErreur, String message) {
        super(message);
        this.typeErreur = typeErreur;
        erreurs = new HashMap<>();
    }
    
    

    public CustomException(String message, int typeErreur, HashMap<String, String> erreurs) {
        super(message);
        this.typeErreur = typeErreur;
        this.erreurs = erreurs;
    }

    public int getTypeErreur() {
        return typeErreur;
    }

    public void setTypeErreur(int typeErreur) {
        this.typeErreur = typeErreur;
    }

    public HashMap<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(HashMap<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    public void addErreur(String cle, String erreur){
        erreurs.put(cle, erreur);
    }
    
}
