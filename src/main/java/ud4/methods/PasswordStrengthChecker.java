package ud4.methods;




public class PasswordStrengthChecker {

    public enum PasswordStrength{
        INVALID,
        WEAK,
        MEDIUM,
        STRONG
    }


    public static PasswordStrength isPasswordStrong(String password){
        PasswordStrength fortaleza = null;
        int contadorFortaleza = 0;

        if(password == null || password.length()<8){
            fortaleza = PasswordStrength.INVALID;
            return fortaleza;
        }

        if(password.matches(".*[a-z].*")){
            contadorFortaleza += 1;
        }
        if(password.matches(".*[A-Z].*")){
            contadorFortaleza += 1;
        }
        if(password.matches(".*[0-9].*")){
            contadorFortaleza += 1;
        }


        switch (contadorFortaleza){
            case 1 -> fortaleza = PasswordStrength.WEAK;
            case 2 -> fortaleza = PasswordStrength.MEDIUM;
            case 3 -> fortaleza = PasswordStrength.STRONG;
        }

        return fortaleza;
    }
}
