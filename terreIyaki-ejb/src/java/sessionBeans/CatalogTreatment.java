package sessionBeans;

import entityBeans.Category;
import entityBeans.Ingredient;
import entityBeans.Option;
import entityBeans.Product;
import entityBeans.Property;
import entityBeans.VAT;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class CatalogTreatment implements CatalogTreatmentLocal {

    @PersistenceContext(unitName = "terreIyaki-ejbPU")
    private EntityManager em;

    @Override
    public void createProduct(String name, String price, String picture, String description, String categoryName,
            String vatId, String[] pproperties, String[] poptions, String[] pingredients) {

        Product prod = new Product(name, Float.valueOf(price), picture, description);

        Category cat = em.find(Category.class, categoryName);
        prod.setCategory(cat);
        VAT vat = em.find(VAT.class, Long.valueOf(vatId));
        prod.setVat(vat);
        if (pproperties != null) {
            for (String propId : pproperties) {
                Property prop = em.find(Property.class, Long.valueOf(propId));
                prod.getProperties().add(prop);
            }
        }
        if (poptions != null) {
            for (String optId : poptions) {
                Option opt = em.find(Option.class, Long.valueOf(optId));
                prod.getOptions().add(opt);
            }
        }
        if (pingredients != null) {
            for (String ingId : pingredients) {
                Ingredient ing = em.find(Ingredient.class, Long.valueOf(ingId));
                prod.getIngredients().add(ing);
            }
        }

        em.persist(prod);
    }

    @Override
    public List<Category> getAllCategories() {
        Query qr = em.createNamedQuery("entityBeans.Category.selectAll");
        List<Category> lc = qr.getResultList();
        return lc;
    }

    @Override
    public List<VAT> getAllVATs() {
        Query qr = em.createNamedQuery("entityBeans.VAT.selectAll");
        List<VAT> lv = qr.getResultList();
        return lv;
    }
    
    @Override
    public List<Property> getAllProperties() {
        Query qr = em.createNamedQuery("entityBeans.Property.selectAll");
        List<Property> lp = qr.getResultList();
        return lp;
    }
    
    @Override
    public List<Option> getAllOptions() {
        Query qr = em.createNamedQuery("entityBeans.Option.selectAll");
        List<Option> lo = qr.getResultList();
        return lo;
    }
    
    @Override
    public List<Ingredient> getAllIngredients() {
        Query qr = em.createNamedQuery("entityBeans.Ingredient.selectAll");
        List<Ingredient> li = qr.getResultList();
        return li;
    }
    
    @Override
    public Product getProductById(String productId) {
        Product pro;
        TypedQuery<Product> qr = em.createNamedQuery("entityBeans.Product.selectProductById", Product.class);
        qr.setParameter("paramId", Long.valueOf(productId));
        try {
            pro = qr.getSingleResult();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return pro;
    }
    
    @Override
    public Category getCategoryById(String categoryId) {
        Category cat;
        TypedQuery<Category> qr = em.createNamedQuery("entityBeans.Category.selectCategoryById", Category.class);
        qr.setParameter("paramId", Long.valueOf(categoryId));
        try {
            cat = qr.getSingleResult();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return cat;
    }
    
    @Override
    public List<Product> getProductsByCategory(Category cat) {
        List<Product> catList = new ArrayList();
        TypedQuery<Product> qr = em.createNamedQuery("entityBeans.Product.selectProductsByCategory", Product.class);
        qr.setParameter("paramCat", cat);
        try {
            catList = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return catList;
    }
    
    @Override
    public List<Property> getPropertiesByProduct(Product prod) {
        List<Property> propList = new ArrayList();
        TypedQuery<Property> qr = em.createNamedQuery("entityBeans.Product.selectProductProperties", Property.class);
        qr.setParameter("paramProd", prod);
        try {
            propList = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return propList;
    }
    
    @Override
    public List<Option> getOptionsByProduct(Product prod) {
        List<Option> optList = new ArrayList();
        TypedQuery<Option> qr = em.createNamedQuery("entityBeans.Product.selectProductOptions", Option.class);
        qr.setParameter("paramProd", prod);
        try {
            optList = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return optList;
    }
    
    @Override
    public List<Ingredient> getIngredientsByProduct(Product prod) {
        List<Ingredient> ingList = new ArrayList();
        TypedQuery<Ingredient> qr = em.createNamedQuery("entityBeans.Product.selectProductIngredients", Ingredient.class);
        qr.setParameter("paramProd", prod);
        try {
            ingList = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return ingList;
    }
    
    @Override
    public List<Product> getSidesByProduct(Product prod) {
        List<Product> sidList = new ArrayList();
        TypedQuery<Product> qr = em.createNamedQuery("entityBeans.Product.selectProductSides", Product.class);
        qr.setParameter("paramProd", prod);
        try {
            sidList = qr.getResultList();
        } catch(NoResultException ex){
            // todo
            return null;
        }
        return sidList;
    }

}
