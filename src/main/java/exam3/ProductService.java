package exam3;


public interface ProductService {
    Product findByName(String name);
    Category setCategory(Product product, Category category) throws SameCategoryException;
}
