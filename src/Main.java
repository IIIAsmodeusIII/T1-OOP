public class Main {
    public static void main(String[] args) {
        Window door = new Window();
        System.out.println(door.getState());
        door.open();
        System.out.println(door.getState());
        System.out.println("pal window");
    }
}