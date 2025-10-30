package ud1.exercises;

import java.util.Locale;
import java.util.Scanner;

/*
 * El resultat de l'operació de collatz
 * per a N = 27 després de 12 iteracions
 * és ????
 */

class DebugCollatz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Introdueix un número: ");
        int inicial = in.nextInt();
        int n = inicial;
        int counter = 0;

        while (n != 0) {
            if(n % 2 == 0)
                n = n * 2 + 1;
            else
                n = (n + 1) / 2;
            counter++;
        }

        System.out.printf("S'ha arribat del %d al 1 després de %d passos.\n", inicial, counter);
    }
}