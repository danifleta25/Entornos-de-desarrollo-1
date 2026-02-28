package ud4.methods;

public class DotLine {

    public static String dotLine(int number){
        String dot = ".";
        for(int i = 1; i < number; i++){
            dot += ".";
        }
        return dot;
    }
}
