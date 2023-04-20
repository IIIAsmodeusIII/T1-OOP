public class Recta {
    private double m;
    private double b;
    private boolean flag;
    
    public Recta(boolean flag, float x, float y, int direction_angle, int sensing_angle){
        this.flag = flag;
        if (flag) {
            this.m = Math.toRadians(direction_angle+(sensing_angle/2));    
        } else {
            this.m = Math.toRadians(direction_angle-(sensing_angle/2)); 
        }
        this.m = Math.tan(m);
        this.b = y - (m * x);
    }

    public boolean Eval(float x, float y){
        if (m * x + b < y) {
            
        } else {
            
        }
    }
}
