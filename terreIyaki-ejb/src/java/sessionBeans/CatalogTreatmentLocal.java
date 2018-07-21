
package sessionBeans;

import entityBeans.Category;
import entityBeans.Ingredient;
import entityBeans.Option;
import entityBeans.Product;
import entityBeans.Property;
import entityBeans.VAT;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CatalogTreatmentLocal {

    public void createProduct(String name, String price, String picture, String description, String categoryName, String vatId, String[] pproperties, String[] poptions, String[] pingredients);

    public List<Category> getAllCategories();

    public List<VAT> getAllVATs();

    public List<Property> getAllProperties();

    public List<Option> getAllOptions();

    public List<Ingredient> getAllIngredients();

    public Product getProductById(String productId);

    public Category getCategoryById(String categoryId);

    public List<Product> getProductsByCategory(Category cat);

    public List<Property> getPropertiesByProduct(Product prod);

    public List<Option> getOptionsByProduct(Product prod);

    public List<Ingredient> getIngredientsByProduct(Product prod);

    public List<Product> getSidesByProduct(Product prod);
    
}
