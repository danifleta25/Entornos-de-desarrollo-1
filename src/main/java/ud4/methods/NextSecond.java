package ud4.methods;

public class NextSecond {

    public static String nextSecond(int hour, int minute, int second){
        second +=1;

        if(second >= 60){
            minute += second/60;
            second %= 60;
        }
        if(minute >= 60){
            hour += minute/60;
            minute %= 60;
        }
        hour %= 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
