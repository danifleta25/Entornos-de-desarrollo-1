package ud4.objects;

public class Robot {

    private double x;
    private double y;
    private double speed;




    // constructor

    public Robot(){
        this.x = 0.0;
        this.y = 0.0;
        this.speed = 1.0;
    }

    public Robot(double x, double y) {
            this.x = x;
            this.y = y;
            this.speed = 1.0;
    }

    public Robot(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        if(speed < 0){
            this.speed = 0;
        } else if(speed > 10){
            this.speed = 10;
        } else{
            this.speed = speed;
        }
    }


    // metodos

    public void accelerate(){
        this.speed += 0.5;
        if(this.speed > 10){
            this.speed = 10;
        }
    }

    public void decelerate(){
        this.speed -= 0.5;
        if(this.speed < 0){
            this.speed = 0;
        }
    }

    public void up(){
        this.y += speed;
    }

    public void down(){
        this.y -= speed;
    }

    public void  left(){
        this.x -= speed;
    }
    public void right(){
        this.x += speed;
    }

    @Override
    public String toString(){
        return "Robot (x=" + x + ", y=" + y + ", speed=" + speed + ")";
    }







    // getters


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }
}
