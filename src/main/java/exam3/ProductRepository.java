package exam3;

import java.util.NoSuchElementException;

public interface ProductRepository {
    Product findByName(String name) throws NoSuchElementException;

    void save(Product product);
}
