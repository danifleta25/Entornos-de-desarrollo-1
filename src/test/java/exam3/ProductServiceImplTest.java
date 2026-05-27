package exam3;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ud8.common.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {



    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private final Category categoria = new Category("ropa");
    private final Product producto = new Product("Camiseta", categoria);

    private final Category nuevaCategoria = new Category("Prenda");

    @Nested
    class FindByNameTests{

        @Test
        void givenExistingName_shouldReturnProduct(){
            when(productRepository.findByName("Camiseta")).thenReturn(producto);

            Product prodResultado = productService.findByName(producto.getName());

            assertEquals(producto, prodResultado);

            verify(productRepository).findByName(producto.getName());
        }

        @Test
        void givenNonExistingName_shouldReturnNull(){
            String nombreNoExistente = "Vaqueros";

            when(productRepository.findByName(nombreNoExistente)).thenReturn(null);

            Product prodResultado = productService.findByName(nombreNoExistente);

            assertNull(prodResultado);

            verify(productRepository).findByName(nombreNoExistente);
        }
    }


    @Nested
    class SetCategoryTests{

        @Test
        void givenNewCategory_shouldReturnOldCategory() throws SameCategoryException {

            Category antigua = productService.setCategory(producto, nuevaCategoria);

            assertEquals(categoria, antigua);
        }

        @Test
        void givenNewCategory_shouldSaveProduct() throws SameCategoryException {

            productService.setCategory(producto, nuevaCategoria);

            verify(productRepository).save(producto);

        }


        @Test
        void givenExistingCategory_shouldTrowExceptionAndNotSaveProduct() {

            assertThrows(SameCategoryException.class,
                    () -> productService.setCategory(producto, categoria));

            verify(productRepository, never()).save(producto);
        }
    }


}