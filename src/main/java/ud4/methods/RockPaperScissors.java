package ud4.methods;

import java.util.Locale;
import java.util.Scanner;

public class RockPaperScissors {

    /**
     * Determina quin jugador ha guanyat la partida de pedra paper i tisores.
     * <p>
     * Aquest mètode retorna 0 si hi ha hagut un empat, 1 si el jugador1 ha guanyat la partida,
     * i 2 si el jugador2 ha guanyat.
     *
     * @param jugador1 Jugada del jugador1 (pedra, paper o tisores)
     * @param jugador2 Jugada del jugador1 (pedra, paper o tisores)
     * @return 0 si hi ha un empat; 1 si guanya jugador1; 2 si guanya jugador 2.
     */
    public static int guanya(String jugador1, String jugador2){
        if(jugador1.equals(jugador2))
            return 0;
        else if (jugador1.equals("paper")) {
            return jugador2.equals("pedra") ? 1 : 2;
        } else if (jugador1.equals("pedra")) {
            return jugador2.equals("paper") ? 2 : 1;
        } else {
            return jugador2.equals("paper") ? 1 : 2;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Introdueix l'elecció del jugador 2 (pedra/paper/tisores): ");
        String p1 = in.nextLine();
        System.out.print("Introdueix l'elecció del jugador 2 (pedra/paper/tisores): ");
        String p2 = in.nextLine();

        int guanyador = guanya(p1, p2);
        if (guanyador == 0)
            System.out.println("Empat");
        else
            System.out.printf("Guanya el jugador %d\n", guanyador);
    }
}