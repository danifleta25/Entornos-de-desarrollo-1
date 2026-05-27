package exam3;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    /**
     * Retorna un producte a partir del seu nom. Si no existeix, retorna null
     * @param name Nom del product
     * @return Producte amb el nom introduit si existeix, null si no existeix.
     */
    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Aquest mètode estableix la categoria d'un producte
     * @param product Producte al qual se li estableix una nova categoria
     * @param newCategory Categoria assignada al producte
     * @return Categoria anterior del producte
     */
    @Override
    public Category setCategory(Product product, Category newCategory) throws SameCategoryException {
        Category oldCategory = product.getCategory();
        if(oldCategory.equals(newCategory))
            throw new SameCategoryException();

        product.setCategory(newCategory);
        productRepository.save(product);
        return oldCategory;
    }
}
