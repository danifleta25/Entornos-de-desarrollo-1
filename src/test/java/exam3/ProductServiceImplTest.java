package exam3;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {



    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private final Category categoria = new Category("ropa");
    private final Product producto = new Product("Camiseta", categoria);

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




}