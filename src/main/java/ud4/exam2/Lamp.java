package ud4.exam2;

public class Lamp {

    private double consumption;
    private boolean on;


    // constructors

    public Lamp(double consumption){
        this.consumption = consumption;
        this.on = false;
    }

    public Lamp(double consumption, boolean on){
        this.consumption = consumption;
        this.on = on;
    }




    // getters

    public double getConsumption() {
        return consumption;
    }

    public boolean isOn() {
        return on;
    }


    // methods

    public void turnOn(){
        this.on = true;
    }

    public void turnOff(){
        this.on = false;
    }

    public void toggle(){
        if(this.on) {
            this.on = false;
        }
        if(this.on == false) {
            this.on = true;
        }
    }

    public double consume(double seconds){
        double consumoHora = getConsumption();
        double consumo = 0;
        if(!isOn()) consumo = 0;
        if(isOn()){
            consumo = consumoHora * (seconds / 3600);
        }
        return consumo;
    }

}
