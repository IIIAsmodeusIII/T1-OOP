public class Circulo {
    public Circulo(float r){
        this.r = r;
    }
    public boolean Eval(float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) < this.r;
    }
    public float r;
}
