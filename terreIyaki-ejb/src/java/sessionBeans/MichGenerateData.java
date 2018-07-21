package sessionBeans;

import entityBeans.Product;
import entityBeans.Property;
import entityBeans.Unit;
import entityBeans.VAT;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chelmix
 */
@Stateless
public class MichGenerateData implements MichGenerateDataLocal {

    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;

    @Override
    public void createData() {
        Product prod06 = new Product("Kagoshima", 90f, null, "Entrecôte de boeuf Wagyu de Kagoshima");
        Product prod07 = new Product("Torikatsu Toji", 29f, null, "Torikatsu Toji");
        Product prod08 = new Product("Shake-Terriyaki", 30f, null, "Saumon Royal de l'Océan Pacifique");
        Product prod09 = new Product("Gohan", 7f, null, "Riz nature");
        Product prod10 = new Product("Miso-Shiro", 7f, null, "Soupe Miso");
        Product prod11 = new Product("Yasai Mushi", 11f, null, "Légumes de saison à la vapeur, coulis de sésame");
        Product prod17 = new Product("Châteauneuf du Pape", 120f, null, "Millésime 2007 grand cru");
        
        VAT vat01 = new VAT(10f, "consommation immédiate");
        VAT vat02 = new VAT(5.5f, "consommation différée");
        VAT vat03 = new VAT(10f, "alcool");
        
        prod06.setVat(vat01);
        prod07.setVat(vat01);
        prod08.setVat(vat01);
        prod09.setVat(vat01);
        prod10.setVat(vat01);
        prod11.setVat(vat01);
        prod17.setVat(vat03);
        
        
        Property prop01 = new Property("Origine", "Japon");
        Property prop02 = new Property("Origine", "UE");
        Property prop03 = new Property("Origine", "Nouvelle Zélande");
        Property prop04 = new Property("Contenance", "75");
        Property prop05 = new Property("Alcoolémie", "75");
        
        Unit u01 = new Unit("centilitre", "cl");
        Unit u02 = new Unit("degré", "°");
        Unit u03 = new Unit("gramme", "g");
        
        prop04.setUnit(u01);
        prop05.setUnit(u02);
        prod17.getProperties().add(prop04);
        
        em.persist(prod08);
        em.persist(prod09);
        em.persist(prod10);
        em.persist(prod11);
        em.persist(prod17);
        
        em.persist(vat01);
        em.persist(vat02);
        em.persist(vat03);
        
        em.persist(u01);
        em.persist(u02);
        em.persist(u03);
        
        em.persist(prop01);
        em.persist(prop02);
        em.persist(prop03);
        em.persist(prop04);
        em.persist(prop05);
        
    }

}
