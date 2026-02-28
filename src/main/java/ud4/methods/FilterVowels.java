package ud4.methods;

public class FilterVowels {

    public static String filterVowels(String text){
        return text.replaceAll("[aeiouAEIOU]", "");
        }
    }


