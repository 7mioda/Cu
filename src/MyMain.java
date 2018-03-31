import CackeProject.Entities.Category;
import CackeProject.Entities.Product;
import CackeProject.Services.CRUDCategory;
import CackeProject.Services.CRUDProduct;

import java.util.List;

public class MyMain {
    public static void main(String[] args) {
        Category category = new Category();
        CRUDCategory crudCategory = new CRUDCategory();
        List<Category> list = crudCategory.showCategory();
        category = list.stream().findFirst().get();
        Product product = new Product();
        product.setPrice(1500);
        product.setQuantity(1500);
        product.setCategory(category);
        CRUDProduct crudProduct = new CRUDProduct();
        crudProduct.addProduct(product);
    }
}
