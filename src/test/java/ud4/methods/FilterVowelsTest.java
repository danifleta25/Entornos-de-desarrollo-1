package ud4.methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FilterVowelsTest {

    @Test
    void filterA() {
        String text = "hola";
        String expected = "hl";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);
    }

    @Test
    void filterE() {
        String text = "hola fernando";
        String expected = "hl frnnd";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);

    }

    @Test
    void filterI() {
        String text = "hola joseluis";
        String expected = "hl jsls";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);
    }

    @Test
    void filterO() {
        String text = "hola joseluis";
        String expected = "hl jsls";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);
    }

    @Test
    void filterU() {
        String text = "hola joseluis";
        String expected = "hl jsls";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);
    }

    @Test
    @DisplayName("Test con todas las vocales")
    void filTerVocales() {
        String text = "hola joseluis";
        String expected = "hl jsls";
        String textoTransformado = FilterVowels.filterVowels(text);

        assertEquals(expected, textoTransformado);
    }

}