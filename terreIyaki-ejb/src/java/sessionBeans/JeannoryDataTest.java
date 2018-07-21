/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Account;
import entityBeans.Category;
import entityBeans.Combo;
import entityBeans.ComboCategory;
import entityBeans.Ingredient;
import entityBeans.MyGrant;
import entityBeans.MyOrder;
import entityBeans.MyTable;
import entityBeans.Option;
import entityBeans.OrderItem;
import entityBeans.Payment;
import entityBeans.PaymentOption;
import entityBeans.Product;
import entityBeans.Property;
import entityBeans.Status;
import entityBeans.Unit;
import entityBeans.VAT;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeanno
 */
@Stateless
public class JeannoryDataTest implements JeannoryDataTestLocal {

    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;

    @Override
    public void dataTest() {

        //Status de s00 à s21
        Status s00 = new Status(0, "Prise de commande en cours", "Order Item");
        Status s01 = new Status(1, "A préparer ", "Order Item ");
        Status s02 = new Status(2, "En cours de préparation ", "Order Item");       
        Status s03 = new Status(3, "Prêt à être servi ", "Order Item");
        Status s04 = new Status(4, "Annulé", "Order Item");
        Status s05 = new Status(5, "A payer", "Payment ");
        Status s06 = new Status(6, "Payé", "Payment ");
        Status s07 = new Status(7, "Actif", "Account");
        Status s08 = new Status(8, "Inactif", " Account ");
        Status s09 = new Status(9, "En cours", "My Order ");
        Status s10 = new Status(10, "Terminé ", " My Order");
        Status s11 = new Status(11, "Annulé", " My Order ");
        Status s12 = new Status(12, "Actif", "Combo ");
        Status s13 = new Status(13, "Inactif ", "Combo");
        Status s14 = new Status(14, "Actif", "My Table ");
        Status s15 = new Status(15, "Inactif ", "My Table");
        Status s16 = new Status(16, "Actif", "Payment Option  ");
        Status s17 = new Status(17, "Inactif ", "Payment Option ");
        Status s18 = new Status(18, "Actif", "VAT  ");
        Status s19 = new Status(19, "Inactif ", "VAT ");
        Status s20 = new Status(20, "Disponible", "Product  ");
        Status s21 = new Status(21, "Indisponible ", "Product ");
        
        //PaymentOption de p01 à p03
        PaymentOption po01 = new PaymentOption("CB");
        PaymentOption po02 = new PaymentOption("Espece");
        PaymentOption po03 = new PaymentOption("Ticket Restaurant");

        // Date d01 à d10
        Date d01 = new GregorianCalendar(2018, 1, 22, 12, 30).getTime();
        Date d02 = new GregorianCalendar(2018, 1, 22, 12, 45).getTime();
        Date d03 = new GregorianCalendar(2018, 1, 22, 12, 15).getTime();
        Date d04 = new GregorianCalendar(2018, 1, 22, 13, 00).getTime();
        Date d05 = new GregorianCalendar(2018, 1, 22, 13, 15).getTime();
        Date d06 = new GregorianCalendar(2018, 1, 24, 20, 55).getTime();
        Date d07 = new GregorianCalendar(2018, 1, 24, 20, 33).getTime();
        Date d08 = new GregorianCalendar(2018, 1, 24, 21, 25).getTime();
        Date d09 = new GregorianCalendar(2018, 1, 24, 20, 15).getTime();
        Date d10 = new GregorianCalendar(2018, 1, 24, 19, 15).getTime();

        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();

        formater = new SimpleDateFormat("EEEE d MMM yyyy");

        Date d11 = aujourdhui;

        // pay01 à pay10
        Payment pay01 = new Payment(d01, 20f);
        pay01.setPaymentOption(po01);
        Payment pay02 = new Payment(d02, 30f);
        pay01.setPaymentOption(po02);
        Payment pay03 = new Payment(d03, 40f);
        pay01.setPaymentOption(po01);
        Payment pay04 = new Payment(d04, 50f);
        pay01.setPaymentOption(po03);
        Payment pay05 = new Payment(d05, 20f);
        pay01.setPaymentOption(po01);
        Payment pay06 = new Payment(d06, 90f);
        pay01.setPaymentOption(po02);
        Payment pay07 = new Payment(d07, 25f);
        pay01.setPaymentOption(po01);
        Payment pay08 = new Payment(d08, 35f);
        pay01.setPaymentOption(po02);

        // opt01 à opt05
        Option opt01 = new Option("Salée", "Sauce soja salée");
        Option opt02 = new Option("Sucrée", "Sauce soja sucrée");
        Option opt03 = new Option("Epicé", "Sauce épicé");
        Option opt04 = new Option("Non épicé", "Sauce non épicé");
        Option opt05 = new Option("sauce sésame", "Sauce à base de sésame");

        // ing01 à ing05
        Ingredient ing01 = new Ingredient("riz", "Made in China");
        Ingredient ing02 = new Ingredient("nouille", "Made in China");
        Ingredient ing03 = new Ingredient("saumon", "Made in France");
        Ingredient ing04 = new Ingredient("thon", "Made in France");
        Ingredient ing05 = new Ingredient("crevette", "Made in France");

        // myTable01 à myTable08
        MyTable myTable01 = new MyTable(1);
        MyTable myTable02 = new MyTable(2);
        MyTable myTable03 = new MyTable(3);
        MyTable myTable04 = new MyTable(4);
        MyTable myTable05 = new MyTable(5);
        MyTable myTable06 = new MyTable(6);
        MyTable myTable07 = new MyTable(7);
        MyTable myTable08 = new MyTable(8);
        MyTable myTable09 = new MyTable(9);
        MyTable myTable10 = new MyTable(10);
        
        
                // mettre un statut à une table 
        myTable01.setStatus(s15);
        myTable02.setStatus(s15);
        myTable03.setStatus(s15);
        myTable04.setStatus(s15);
        myTable05.setStatus(s15);
        myTable06.setStatus(s15);
        myTable07.setStatus(s15);
        myTable08.setStatus(s15);
        myTable09.setStatus(s15);
        myTable10.setStatus(s15);
        
        
        

        //myOrder01 à myOrder10
        MyOrder myOrder01 = new MyOrder(d11);
        myOrder01.setStatus(s05);

        MyOrder myOrder02 = new MyOrder(d11);

        myOrder02.setStatus(s10);

        myOrder02.setStatus(s05);

        MyOrder myOrder03 = new MyOrder(d03);
        List<MyTable> listMyTable = new ArrayList();
        listMyTable.add(myTable01);
        myOrder03.setMyTables(listMyTable);

        MyOrder myOrder04 = new MyOrder(d04);
        MyOrder myOrder05 = new MyOrder(d11);
        MyOrder myOrder06 = new MyOrder(d11);
        MyOrder myOrder07 = new MyOrder(d07);
        MyOrder myOrder08 = new MyOrder(d08);
        MyOrder myOrder09 = new MyOrder(d09);
        MyOrder myOrder10 = new MyOrder(d10);

        myOrder05.setStatus(s05);
        myOrder06.setStatus(s05);

        //myGrant01 à myGrant03
        MyGrant myGrant01 = new MyGrant("serveur");
        MyGrant myGrant02 = new MyGrant("cuisinier");
        MyGrant myGrant03 = new MyGrant("caissier");
        MyGrant myGrant04 = new MyGrant("client");

        // account01 à account10
        Account account01 = new Account(1234, "Jean", "Dupond");
        Account account02 = new Account(4589, "Fabien", "Neymar");
        Account account03 = new Account(1564, "Laure", "Keyes");
        Account account04 = new Account(1984, "Laetitia", "Courte");
        Account account05 = new Account(4964, "Michel", "Jordine");
        Account account06 = new Account(4444, "Albert", "Dagobert");
        Account account07 = new Account(4369, "Francois", "Le Petit");
        Account account08 = new Account(4785, "Alex", "Kidd");
        Account account09 = new Account(4234, "Vincent", "Rattant");
        Account account10 = new Account(3264, "Jeanne", "Durand");

        Account account11 = new Account(1, "Table", "1");
        Account account12 = new Account(2, "Table", "2");
        Account account13 = new Account(3, "Table", "3");
        Account account14 = new Account(4, "Table", "4");
        Account account15 = new Account(5, "Table", "5");
        Account account16 = new Account(6, "Table", "6");
        Account account17 = new Account(7, "Table", "7");
        Account account18 = new Account(8, "Table", "8");
        Account account19 = new Account(9, "Table", "9");
        Account account20 = new Account(10, "Table", "10");

        account11.setMyTable(myTable01);
        account12.setMyTable(myTable02);
        account13.setMyTable(myTable03);
        account14.setMyTable(myTable04);
        account15.setMyTable(myTable05);
        account16.setMyTable(myTable06);
        account17.setMyTable(myTable07);
        account18.setMyTable(myTable08);
        account19.setMyTable(myTable09);
        account20.setMyTable(myTable10);

        // prod01 à prod16
        Product prod01 = new Product("Yasai", 20f, "images/default-product.jpg", "Tempura de légumes 7 pièces");
        Product prod02 = new Product("Ebi", 33f, "images/default-product.jpg", "Tempura de gambas 6 pièces");
        Product prod03 = new Product("Moriawase 6", 25f, "images/default-product.jpg", "Tempura de légumes et de gambas 6 pièces");
        Product prod04 = new Product("Ise Ebi", 50f, "images/default-product.jpg", "Tempura de langoustes");
        Product prod05 = new Product("Moriawase 10", 35f, "images/default-product.jpg", "Tempura de légumes et de gambas 10 pièces");
        Product prod06 = new Product("Kagoshima", 90f, "images/default-product.jpg", "Entrecôte de boeuf Wagyu de Kagoshima");
        Product prod07 = new Product("Torikatsu Toji", 29f, "images/default-product.jpg", "Torikatsu Toji");
        Product prod08 = new Product("Shake-Terriyaki", 30f, "images/default-product.jpg", "Saumon Royal de l'Océan Pacifique");
        Product prod09 = new Product("Gohan", 7f, "images/default-product.jpg", "Riz nature");
        Product prod10 = new Product("Miso-Shiro", 7f, "images/default-product.jpg", "Soupe Miso");
        Product prod11 = new Product("Yasai Mushi", 11f, "images/default-product.jpg", "Légumes de saison à la vapeur, coulis de sésame");
        Product prod12 = new Product("Agedashi", 11f, "images/default-product.jpg", "Tofu frit");
        Product prod13 = new Product("Edamame 6", 19f, "images/default-product.jpg", "Soja vert");
        Product prod14 = new Product("Kyuri Wakame", 8f, "images/default-product.jpg", "Salade japonaise");
        Product prod15 = new Product("Nasu", 9f, "images/default-product.jpg", "Aubergines confites");
        Product prod16 = new Product("Kara Age", 9f, "images/default-product.jpg", "Beignet de poulet");

        Product prod55 = new Product("Coca cola", 5f, "images/default-product.jpg", "servi avec rondelle de citron");
        Product prod56 = new Product("Fanta", 5f, "images/default-product.jpg", "saveur exotique");
        

        // ajout des options au pif pour test
        prod07.getOptions().add(opt05);
        prod07.getOptions().add(opt04);
        prod07.getOptions().add(opt03);
        prod07.getOptions().add(opt02);
        prod07.getOptions().add(opt01);
        prod02.getOptions().add(opt02);
        prod02.getOptions().add(opt01);
        prod03.getOptions().add(opt02);
        prod03.getOptions().add(opt01);
        prod04.getOptions().add(opt02);
        prod01.getOptions().add(opt01);
        // ajout ingredients au pif pour le test
        prod07.getIngredients().add(ing05);
        prod07.getIngredients().add(ing04);
        prod07.getIngredients().add(ing03);
        // ajout accompagnements au pif pour le test
        prod07.getSides().add(prod12);
        prod07.getSides().add(prod13);

        //cat01 à cat05
        Category cat01 = new Category("Entrées");
        Category cat02 = new Category("Tempura");
        Category cat03 = new Category("Viandes grillées");
        Category cat04 = new Category("Poissons grillés");
        Category cat05 = new Category("Accompagnements");
        Category cat06 = new Category("Boissons");

        // attribution de catégories aux produits
        prod01.setCategory(cat02);
        prod02.setCategory(cat02);
        prod03.setCategory(cat02);
        prod04.setCategory(cat02);
        prod05.setCategory(cat02);
        prod06.setCategory(cat03);
        prod07.setCategory(cat02);
        prod08.setCategory(cat04);
        prod09.setCategory(cat05);
        prod10.setCategory(cat01);
        prod11.setCategory(cat05);
        prod12.setCategory(cat01);
        prod13.setCategory(cat01);
        prod14.setCategory(cat01);
        prod15.setCategory(cat01);
        prod16.setCategory(cat01);
        prod55.setCategory(cat06);
        prod56.setCategory(cat06);
        
        

        List<MyGrant> listMyGrant01 = new ArrayList();
        List<MyGrant> listMyGrant02 = new ArrayList();
        List<MyGrant> listMyGrant03 = new ArrayList();
        List<MyGrant> listMyGrant04 = new ArrayList();
        List<MyGrant> listMyGrant05 = new ArrayList();

        List<MyTable> listMyTable01 = new ArrayList();
        List<MyTable> listMyTable02 = new ArrayList();
        List<MyTable> listMyTable03 = new ArrayList();
        List<MyTable> listMyTable04 = new ArrayList();
        List<MyTable> listMyTable05 = new ArrayList();
        List<MyTable> listMyTable06 = new ArrayList();
        List<MyTable> listMyTable07 = new ArrayList();
        List<MyTable> listMyTable08 = new ArrayList();

        listMyGrant01.add(myGrant01); //serveur
        listMyGrant02.add(myGrant02);
        listMyGrant03.add(myGrant03);

        //serveur et caissier
        listMyGrant04.add(myGrant01);
        listMyGrant04.add(myGrant03);

        listMyGrant05.add(myGrant04);

        listMyTable01.add(myTable01);
        listMyTable02.add(myTable02);
        listMyTable03.add(myTable03);
        listMyTable04.add(myTable04);
        listMyTable05.add(myTable05);
        listMyTable06.add(myTable06);
        listMyTable07.add(myTable07);
        listMyTable08.add(myTable08);

        account01.setMyGrants(listMyGrant01);
        account02.setMyGrants(listMyGrant01);
        account03.setMyGrants(listMyGrant01);
        account04.setMyGrants(listMyGrant02);
        account05.setMyGrants(listMyGrant02);
        account06.setMyGrants(listMyGrant02);
        account07.setMyGrants(listMyGrant03);
        account08.setMyGrants(listMyGrant03);
        account09.setMyGrants(listMyGrant04);
        account10.setMyGrants(listMyGrant04);

        account11.setMyGrants(listMyGrant05);
        account12.setMyGrants(listMyGrant05);
        account13.setMyGrants(listMyGrant05);
        account14.setMyGrants(listMyGrant05);
        account15.setMyGrants(listMyGrant05);
        account16.setMyGrants(listMyGrant05);
        account17.setMyGrants(listMyGrant05);
        account18.setMyGrants(listMyGrant05);
        account19.setMyGrants(listMyGrant05);
        account20.setMyGrants(listMyGrant05);

        myOrder01.setAccount(account13);
        myOrder02.setAccount(account18);
        myOrder03.setAccount(account03);
        myOrder04.setAccount(account09);
        myOrder05.setAccount(account10);
        myOrder06.setAccount(account10);
        myOrder07.setAccount(account10);
        myOrder08.setAccount(account10);

        myOrder01.setMyTables(listMyTable03);
        myOrder02.setMyTables(listMyTable08);
        myOrder03.setMyTables(listMyTable03);
        myOrder04.setMyTables(listMyTable04);
        myOrder05.setMyTables(listMyTable05);
        myOrder06.setMyTables(listMyTable06);
        myOrder07.setMyTables(listMyTable07);
        myOrder08.setMyTables(listMyTable08);

        //plat simple
        OrderItem orderItem01 = new OrderItem(20f, 4f);
        orderItem01.setProduct(prod16);
        orderItem01.setStatus(s01);
        orderItem01.getOptions().add(opt02);
        orderItem01.getOptions().add(opt03);

        //menu
        OrderItem orderItem02 = new OrderItem(20f, 4f);
        orderItem02.setProduct(prod11);
        orderItem02.setStatus(s01);
        orderItem02.getIngredients().add(ing02);
        
        OrderItem orderItem03 = new OrderItem(20f, 4f);
        OrderItem orderItem04 = new OrderItem(20f, 4f);
        OrderItem orderItem05 = new OrderItem(20f, 4f);
        OrderItem orderItem06 = new OrderItem(20f, 4f);
        orderItem06.setProduct(prod12);
        orderItem06.setStatus(s02);

        OrderItem comboOrderItem01 = new OrderItem(100f, 20f);

        //     orderItem01.setMyOrder(myOrder01);
//        orderItem02.setMyOrder(myOrder02);
//        orderItem03.setMyOrder(myOrder02);
//        orderItem04.setMyOrder(myOrder02);
//        orderItem05.setMyOrder(myOrder02);
//        orderItem06.setMyOrder(myOrder02);
        orderItem02.setComboOrderItem(comboOrderItem01);
        orderItem03.setComboOrderItem(comboOrderItem01);
        orderItem04.setComboOrderItem(comboOrderItem01);
        orderItem05.setComboOrderItem(comboOrderItem01);
        orderItem06.setComboOrderItem(comboOrderItem01);

//        comboOrderItem01.setMyOrder(myOrder02);
        //plat simple
        OrderItem orderItem07 = new OrderItem(20f, 4f);
        OrderItem orderItem08 = new OrderItem(20f, 4f);
        OrderItem orderItem09 = new OrderItem(20f, 4f);
        OrderItem orderItem10 = new OrderItem(20f, 4f);
        OrderItem orderItem11 = new OrderItem(20f, 4f);
        OrderItem orderItem12 = new OrderItem(20f, 4f);

        orderItem07.setMyOrder(myOrder03);
        orderItem08.setMyOrder(myOrder04);
//        orderItem09.setMyOrder(myOrder05);
//        orderItem10.setMyOrder(myOrder06);
        orderItem11.setMyOrder(myOrder07);
        orderItem12.setMyOrder(myOrder08);

        // mich
        Product prod30 = new Product("Châteauneuf du Pape", 120f, "http", "Millésime 2007 grand cru");

        VAT vat01 = new VAT(10f, "consommation immédiate");
        VAT vat02 = new VAT(5.5f, "consommation différée");
        VAT vat03 = new VAT(20f, "alcool");
        
        prod55.setVat(vat01);
        prod56.setVat(vat01);
        
        prod01.setVat(vat01);
        prod02.setVat(vat01);
        prod03.setVat(vat01);
        prod04.setVat(vat01);
        prod05.setVat(vat01);
        prod06.setVat(vat01);
        prod07.setVat(vat01);
        prod08.setVat(vat01);
        prod09.setVat(vat01);
        prod10.setVat(vat01);
        prod11.setVat(vat01);
        prod12.setVat(vat01);
        prod13.setVat(vat01);
        prod14.setVat(vat01);
        prod15.setVat(vat01);
        prod16.setVat(vat01);
        

        Property prop01 = new Property("Origine", "Japon");
        Property prop02 = new Property("Origine", "UE");
        Property prop03 = new Property("Origine", "Nouvelle Zélande");
        Property prop04 = new Property("Contenance", "75");
        Property prop05 = new Property("Alcoolémie", "12.5");

        Unit u01 = new Unit("centilitre", "cl");
        Unit u02 = new Unit("degré", "°");
        Unit u03 = new Unit("gramme", "g");

        prop04.setUnit(u01);
        prop05.setUnit(u02);

        prod30.getProperties().add(prop04);

        // propriétés au pif
        prod01.getProperties().add(prop04);
        prod02.getProperties().add(prop04);
        prod09.getProperties().add(prop05);
        prod09.getProperties().add(prop02);
        prod02.getProperties().add(prop05);
        prod03.getProperties().add(prop02);
        prod04.getProperties().add(prop02);
        prod05.getProperties().add(prop02);
        prod06.getProperties().add(prop05);
        prod07.getProperties().add(prop02);
        prod08.getProperties().add(prop02);

        Combo combo02 = new Combo("Zen", 45f, "menu abordable", "images/default-product.jpg");
//      Zen  
        Product prod23 = new Product("King starter", 10f, "images/default-product.jpg", "Petits hors-d'oeuvres avec sa soupe Miso");
        Product prod24 = new Product("Sushi rary", 30f, "images/default-product.jpg", "Assortiment variés de 7 sushi et 6 maki");
        Product prod25 = new Product("Tempura gaya", 30f, "images/default-product.jpg", "8 exotiques tempura de gambas avec ses légumes ou plats royal de légumes tempura (plat végétarien)");
        Product prod26 = new Product("Sushi Tempura", 30f, "images/default-product.jpg", "Délicieux assortiment de 4 sushi et de 4 tempura mixtes");
        Product prod27 = new Product("King Sashimi", 30f, "images/default-product.jpg", "Succulent assortiment de 15 sashimi");
        Product prod28 = new Product("Gyu Royal", 30f, "images/default-product.jpg", "Excellent plat avec notre spécial Boeuf Black Angus persillé et sa sauce Teriyaki");
        Product prod29 = new Product("Fruit love", 5f, "images/default-product.jpg", "Assortiments de fruits frais");

        ComboCategory comboCat04 = new ComboCategory(1, "1 - Entree");
        ComboCategory comboCat05 = new ComboCategory(2, "2 - Plat au choix");
//        ComboCategory comboCat11 = new ComboCategory (3,"Accompagnement");
        ComboCategory comboCat06 = new ComboCategory(4, "3 - Dessert");

        comboCat04.setCombo(combo02);
        comboCat05.setCombo(combo02);
        comboCat06.setCombo(combo02);

        List<ComboCategory> listComboCat05 = new ArrayList();
        List<ComboCategory> listComboCat06 = new ArrayList();
        List<ComboCategory> listComboCat07 = new ArrayList();
        //           List<ComboCategory> listComboCat08 = new ArrayList();              
        listComboCat05.add(comboCat04);
        listComboCat06.add(comboCat05);
        listComboCat07.add(comboCat06);
        //      listComboCat08.add(comboCat11);
        prod23.setComboCategories(listComboCat05);
        prod24.setComboCategories(listComboCat06);
        prod25.setComboCategories(listComboCat06);
        prod26.setComboCategories(listComboCat06);
        prod27.setComboCategories(listComboCat06);
        prod28.setComboCategories(listComboCat06);
        prod29.setComboCategories(listComboCat07);

        //combo 1       
        Combo combo01 = new Combo("Bento ShoKado", 62f, "menu 3 étoiles", "images/default-product.jpg");

        ComboCategory comboCat01 = new ComboCategory(1, "1 - Entree au choix");
        ComboCategory comboCat02 = new ComboCategory(2, "2 - Plat au choix");
        ComboCategory comboCat10 = new ComboCategory(3, "3 - Accompagnement");
        ComboCategory comboCat03 = new ComboCategory(4, "4 - Dessert");

        Product prod117 = new Product("Gosaismas starter", 12f, "images/default-product.jpg", "Sélection de délicieux petits hors-d'oeuvres");
        Product prod18 = new Product("Gosaismos starter", 12f, "images/default-product.jpg", "Assortiments de légumes salés et Mijotés");
        Product prod19 = new Product("Bento Shokado speedo", 40f, "images/default-product.jpg", "Plat de Sashimi avec du Saumon Label Rouge grillé");
        Product prod20 = new Product("Bento Shokado algo", 40f, "images/default-product.jpg", "Tempura de gambas avec ses légumes");
        Product prod21 = new Product("Accompagnement loquent", 5f, "images/default-product.jpg", "Riz gluant avec graine de sésames à volontés");
        Product prod22 = new Product("Litchi love", 5f, "images/default-product.jpg", "Assortiments de litchi frais");

        comboCat01.setCombo(combo01);
        comboCat02.setCombo(combo01);
        comboCat10.setCombo(combo01);
        comboCat03.setCombo(combo01);

        List<ComboCategory> listComboCat01 = new ArrayList();
        List<ComboCategory> listComboCat02 = new ArrayList();
        List<ComboCategory> listComboCat03 = new ArrayList();
        List<ComboCategory> listComboCat04 = new ArrayList();

        listComboCat01.add(comboCat01);
        listComboCat02.add(comboCat02);
        listComboCat03.add(comboCat03);
        listComboCat04.add(comboCat10);

        prod117.setComboCategories(listComboCat01);
        prod18.setComboCategories(listComboCat01);
        prod19.setComboCategories(listComboCat02);
        prod20.setComboCategories(listComboCat02);
        prod21.setComboCategories(listComboCat04);
        prod22.setComboCategories(listComboCat03);

        prod117.setVat(vat01);
        prod18.setVat(vat01);
        prod19.setVat(vat01);
        prod20.setVat(vat01);
        prod22.setVat(vat01);
        prod21.setVat(vat01);

        prod23.setVat(vat01);
        prod24.setVat(vat01);
        prod25.setVat(vat01);
        prod26.setVat(vat01);
        prod27.setVat(vat01);
        prod28.setVat(vat01);
        prod29.setVat(vat01);

        prod56.setVat(vat01);
        prod55.setVat(vat01);

        prod30.setVat(vat03);

        OrderItem orderItem100 = new OrderItem(0f, 0f);
//**************menu
        orderItem100.setCombo(combo02);
        OrderItem orderItem101 = new OrderItem(10, 10f);
        orderItem101.setProduct(prod23);
        OrderItem orderItem102 = new OrderItem(30f, 10f);
        orderItem102.setProduct(prod25);
        OrderItem orderItem103 = new OrderItem(5f, 10f);
        orderItem103.setProduct(prod29);

        orderItem101.setComboOrderItem(orderItem100);
        orderItem102.setComboOrderItem(orderItem100);
        orderItem103.setComboOrderItem(orderItem100);

        OrderItem orderItem104 = new OrderItem(0f, 0f);
//**************menu
        orderItem104.setCombo(combo01);
        OrderItem orderItem105 = new OrderItem(12f, 10f);
        orderItem105.setProduct(prod117);//µ***********
        OrderItem orderItem106 = new OrderItem(40f, 10f);
        orderItem106.setProduct(prod19);
        OrderItem orderItem107 = new OrderItem(5f, 10f);
        orderItem107.setProduct(prod21);

        OrderItem orderItem108 = new OrderItem(5f, 10f);
        orderItem108.setProduct(prod22);

        orderItem105.setComboOrderItem(orderItem105);
        orderItem106.setComboOrderItem(orderItem105);
        orderItem107.setComboOrderItem(orderItem105);
        orderItem108.setComboOrderItem(orderItem105);

        OrderItem orderItem109 = new OrderItem(5f, 10f);
        orderItem109.setProduct(prod55);
        OrderItem orderItem110 = new OrderItem(5f, 10f);
        orderItem110.setProduct(prod56);

        OrderItem orderItem111 = new OrderItem(0f, 0f);
        orderItem111.setCombo(combo01);
//**************menu
        OrderItem orderItem112 = new OrderItem(12f, 10f);
        orderItem112.setProduct(prod117);
        OrderItem orderItem113 = new OrderItem(40f, 10f);
        orderItem113.setProduct(prod19);
        OrderItem orderItem114 = new OrderItem(5f, 10f);
        orderItem114.setProduct(prod21);
        OrderItem orderItem115 = new OrderItem(5f, 10f);
        orderItem115.setProduct(prod22);

        orderItem112.setComboOrderItem(orderItem111);
        orderItem113.setComboOrderItem(orderItem111);
        orderItem114.setComboOrderItem(orderItem111);
        orderItem115.setComboOrderItem(orderItem111);

        OrderItem orderItem116 = new OrderItem(30f, 10f);
        orderItem116.setProduct(prod08);
        OrderItem orderItem117 = new OrderItem(5f, 10f);
        orderItem117.setProduct(prod55);
        OrderItem orderItem118 = new OrderItem(5f, 10f);
        orderItem118.setProduct(prod55);
        OrderItem orderItem119 = new OrderItem(35f, 10f);
        orderItem119.setProduct(prod05);
        OrderItem orderItem120 = new OrderItem(30f, 10f);
        orderItem120.setProduct(prod08);

        OrderItem orderItem121 = new OrderItem(5f, 10f);
        orderItem121.setProduct(prod55);
        OrderItem orderItem122 = new OrderItem(5f, 10f);
        orderItem122.setProduct(prod55);

        OrderItem orderItem123 = new OrderItem(30f, 10f);
        orderItem123.setProduct(prod08);
        OrderItem orderItem124 = new OrderItem(90f, 10f);
        orderItem124.setProduct(prod06);
        OrderItem orderItem125 = new OrderItem(120f, 20f);
        orderItem125.setProduct(prod30);

//List<OrderItem> listOrderItem101 = new ArrayList();        
//List<OrderItem> listOrderItem102 = new ArrayList(); 
//List<OrderItem> listOrderItem105 = new ArrayList(); 
//List<OrderItem> listOrderItem106 = new ArrayList(); 
//listOrderItem101.add(orderItem100);
//
//listOrderItem101.add(orderItem101);
//listOrderItem101.add(orderItem102);
//listOrderItem101.add(orderItem103);
        //commande myOrder01  
        orderItem100.setMyOrder(myOrder01);
        orderItem101.setMyOrder(myOrder01);
        orderItem102.setMyOrder(myOrder01);
        orderItem103.setMyOrder(myOrder01);
        orderItem104.setMyOrder(myOrder01);
        orderItem105.setMyOrder(myOrder01);
        orderItem106.setMyOrder(myOrder01);
        orderItem107.setMyOrder(myOrder01);
        orderItem108.setMyOrder(myOrder01);
        orderItem109.setMyOrder(myOrder01);
        orderItem110.setMyOrder(myOrder01);

//listOrderItem101.add(orderItem104);
//listOrderItem101.add(orderItem105);
//listOrderItem101.add(orderItem106);
//listOrderItem101.add(orderItem107);
//listOrderItem101.add(orderItem108);
//listOrderItem101.add(orderItem109);
//listOrderItem101.add(orderItem110);
//
//myOrder01.setOrderItems(listOrderItem101);
//listOrderItem102.add(orderItem111);
//listOrderItem102.add(orderItem112);
//listOrderItem102.add(orderItem113);
//listOrderItem102.add(orderItem114);
//listOrderItem102.add(orderItem115);
//listOrderItem102.add(orderItem116);
//listOrderItem102.add(orderItem117);
//listOrderItem102.add(orderItem118);
//commande myOrder02 
        orderItem111.setMyOrder(myOrder02);
        orderItem112.setMyOrder(myOrder02);
        orderItem113.setMyOrder(myOrder02);
        orderItem114.setMyOrder(myOrder02);
        orderItem115.setMyOrder(myOrder02);
        orderItem116.setMyOrder(myOrder02);
        orderItem117.setMyOrder(myOrder02);
        orderItem118.setMyOrder(myOrder02);

//myOrder02.setOrderItems(listOrderItem102);
//listOrderItem105.add(orderItem119);
//listOrderItem105.add(orderItem120);
//listOrderItem105.add(orderItem121);
//listOrderItem105.add(orderItem122);
//commande myOrder05
        orderItem119.setMyOrder(myOrder05);
        orderItem120.setMyOrder(myOrder05);
        orderItem121.setMyOrder(myOrder05);
        orderItem122.setMyOrder(myOrder05);

//        
//myOrder05.setOrderItems(listOrderItem105);
//
//listOrderItem106.add(orderItem123);
//listOrderItem106.add(orderItem124);
//listOrderItem106.add(orderItem125);
//commande myOrder06
        orderItem123.setMyOrder(myOrder06);
        orderItem124.setMyOrder(myOrder06);
        orderItem125.setMyOrder(myOrder06);

//myOrder06.setOrderItems(listOrderItem106);
        em.persist(combo02);

        em.persist(prod23);
        em.persist(prod24);
        em.persist(prod25);
        em.persist(prod26);
        em.persist(prod27);
        em.persist(prod28);
        em.persist(prod29);
        em.persist(prod30);

        em.persist(comboCat04);
        em.persist(comboCat05);
        em.persist(comboCat06);

        em.persist(prod30);
        em.persist(combo01);
        em.persist(prod117);
        em.persist(prod18);
        em.persist(prod19);
        em.persist(prod20);
        em.persist(prod21);
        em.persist(prod22);
        em.persist(comboCat01);
        em.persist(comboCat02);
        em.persist(comboCat03);
        em.persist(comboCat10);

        em.persist(s01);
        em.persist(s02);
        em.persist(s03);
        em.persist(s04);
        em.persist(s05);
        em.persist(s06);
        em.persist(s07);
        em.persist(s08);
        em.persist(s09);
        em.persist(s10);
        em.persist(s11);
        em.persist(s12);
        em.persist(s13);
        em.persist(s14);
        em.persist(s15);
        em.persist(s16);
        em.persist(s17);
        em.persist(s18);
        em.persist(s19);
        em.persist(s20);
        em.persist(s21);
        em.persist(s00);

        em.persist(po01);
        em.persist(po02);
        em.persist(po03);

        em.persist(pay01);
        em.persist(pay02);
        em.persist(pay03);
        em.persist(pay04);
        em.persist(pay05);
        em.persist(pay06);
        em.persist(pay07);
        em.persist(pay08);

        em.persist(opt01);
        em.persist(opt02);
        em.persist(opt03);
        em.persist(opt04);
        em.persist(opt05);

        em.persist(prod01);
        em.persist(prod02);
        em.persist(prod03);
        em.persist(prod04);
        em.persist(prod05);
        em.persist(prod06);
        em.persist(prod07);
        em.persist(prod08);
        em.persist(prod09);
        em.persist(prod10);
        em.persist(prod11);
        em.persist(prod12);
        em.persist(prod13);
        em.persist(prod14);
        em.persist(prod15);
        em.persist(prod16);

        em.persist(cat01);
        em.persist(cat02);
        em.persist(cat03);
        em.persist(cat04);
        em.persist(cat05);
        em.persist(cat06);

        em.persist(myGrant01);
        em.persist(myGrant02);
        em.persist(myGrant03);
        em.persist(myGrant04);

        em.persist(account01);
        em.persist(account02);
        em.persist(account03);
        em.persist(account04);
        em.persist(account05);
        em.persist(account06);
        em.persist(account07);
        em.persist(account08);
        em.persist(account09);
        em.persist(account10);
        em.persist(account11);
        em.persist(account12);
        em.persist(account13);
        em.persist(account14);
        em.persist(account15);
        em.persist(account16);
        em.persist(account17);
        em.persist(account18);
        em.persist(account19);
        em.persist(account20);

        em.persist(myOrder01);
        em.persist(myOrder02);
        em.persist(myOrder03);
        em.persist(myOrder04);
        em.persist(myOrder05);
        em.persist(myOrder06);
        em.persist(myOrder07);
        em.persist(myOrder08);

        em.persist(orderItem01);
        em.persist(orderItem02);
        em.persist(orderItem03);
        em.persist(orderItem04);
        em.persist(orderItem05);
        em.persist(orderItem06);
        em.persist(orderItem07);
        em.persist(orderItem08);
        em.persist(orderItem09);
        em.persist(orderItem10);
        em.persist(orderItem11);
        em.persist(orderItem12);

        em.persist(myTable01);
        em.persist(myTable02);
        em.persist(myTable03);
        em.persist(myTable04);
        em.persist(myTable05);
        em.persist(myTable06);
        em.persist(myTable07);
        em.persist(myTable08);
        em.persist(myTable09);
        em.persist(myTable10);

        em.persist(comboOrderItem01);

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

        em.persist(ing01);
        em.persist(ing02);
        em.persist(ing03);
        em.persist(ing04);
        em.persist(ing05);

        em.persist(prod55);
        em.persist(prod56);

        em.persist(orderItem100);
        em.persist(orderItem101);
        em.persist(orderItem102);
        em.persist(orderItem103);
        em.persist(orderItem104);

        em.persist(orderItem105);
        em.persist(orderItem106);
        em.persist(orderItem107);
        em.persist(orderItem108);
        em.persist(orderItem109);

        em.persist(orderItem110);
        em.persist(orderItem111);
        em.persist(orderItem112);
        em.persist(orderItem113);
        em.persist(orderItem114);

        em.persist(orderItem115);
        em.persist(orderItem116);
        em.persist(orderItem117);
        em.persist(orderItem118);
        em.persist(orderItem119);

        em.persist(orderItem120);
        em.persist(orderItem121);
        em.persist(orderItem122);
        em.persist(orderItem123);
        em.persist(orderItem124);

        em.persist(orderItem125);

        //      em.persist(comboCat11);
        em.flush();

    }

}