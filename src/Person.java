public class Person {
    private float x;
    private float y;
    public Person(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void move(String direction){
        switch (direction){
            case "l" -> {
                x -= 0.5;
            }
            case "r" -> {
                x += 0.5;
            }
            case "u" -> {
                y += 0.5;
            }
            case "d" -> {
                y -= 0.5;
            }
        }
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
