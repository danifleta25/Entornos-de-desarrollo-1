package ud4.objects;

public class Car {

    private String plate;
    private int speed;
    private double kilometers;


    // Constructors

    public Car(String plate) {
        this.plate = plate;
        this.kilometers = 0;
        this.speed = 0;
    }

    public Car(String plate, double kilometers) {
        this.plate = plate;
        this.kilometers = kilometers;
        this.speed = 0;
    }


    // Methods

    public void accelerate(){
        this.speed += 5;
    }


    public void accelerate(int speed){
        this.speed += speed;
    }


    public void drive(){

        double kilometrosSegundo = (double) this.speed / 60 / 60;

        this.kilometers += kilometrosSegundo;
    }


    public void drive(int seconds){

        double kilometrosSegundo = ((double) this.speed / 60 / 60) *  seconds;

        this.kilometers += kilometrosSegundo;
    }

    public void decelerate(){
        this.speed -= 5;
    }




    // Getters

    public String getPlate() {
        return plate;
    }

    public int getSpeed() {
        return speed;
    }

    public double getKilometers() {
        return kilometers;
    }

    @Override
    public String toString() {
        return String.format("Car %s: {speed=%d km/h, kilometers=%.2f}", plate, speed, kilometers);
    }

}
