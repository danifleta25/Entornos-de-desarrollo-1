package ud4.methods;




public class PasswordStrengthChecker {

    public enum PasswordStrength{
        INVALID,
        WEAK,
        MEDIUM,
        STRONG
    }


    public static PasswordStrength isPasswordStrong(String password){
        int contadorFortaleza = 0;

        if(password == null || password.length()<8){
            return PasswordStrength.INVALID;
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


        return switch (contadorFortaleza){
            case 1 -> PasswordStrength.WEAK;
            case 2 -> PasswordStrength.MEDIUM;
            case 3 -> PasswordStrength.STRONG;
            default -> PasswordStrength.INVALID;
        };

    }
}
