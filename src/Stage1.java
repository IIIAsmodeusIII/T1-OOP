import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

public class Stage1 {
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;

    // Pattern Regex
    Pattern pattern;
    Matcher matcher;
    public Stage1() {
        doors = new ArrayList<>();
        windows = new ArrayList<>();

        pattern = Pattern.compile("([kdwcp])(\\d?)\\s(.*)");
        // pirs = new ArrayList<PIR>();
    }
    public void readConfiguration(Scanner in){

        // Get first line
        int nDoors   = in.nextInt();
        int nWindows = in.nextInt();

        for (int i = 0; i < nDoors; i++){
            doors.add(new Door());
        }
        for (int i = 0; i < nWindows; i++){
            windows.add(new Window());
        }
        in.close();
    }
    public void getAndSubmitUserInteraction (Scanner in){
        // Menu
        int step = 0;
        try {
            File file = new File("src/output.csv");
            FileWriter out = new FileWriter(file);
            makeHeaderInCsvFile(out);

            while (true) {
                // Print state to csv lol
                printStateToCsv(step, out);

                // Get user input
                System.out.print("Ingrese comando ('x' para salir): ");
                String input = in.nextLine();

                // End condition
                if (input.equals("x")) {
                    System.out.println("[*] Fin del programa.");
                    out.close();
                    break;
                }

                // Regex user input
                matcher = pattern.matcher(input);
                executeUserCommand(matcher);

                // Next step
                step++;
            }
        } catch (IOException e) {
                System.out.println("[*] Error durante el ingreso de comandos.");
                e.printStackTrace();
        }
        in.close();
    }

    public void executeUserCommand(Matcher matcher){
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

            switch (matcher.group(1)) {
                case "d" -> {
                    if (matcher.group(3).equals("c")) {
                        doors.get(Integer.parseInt((matcher.group(2)))).close();
                    } else {
                        doors.get(Integer.parseInt((matcher.group(2)))).open();
                    }
                }
                case "w" -> {
                    if (matcher.group(3).equals("c")) {
                        windows.get(Integer.parseInt((matcher.group(2)))).close();
                    } else {
                        windows.get(Integer.parseInt((matcher.group(2)))).open();
                    }
                }
            }
        }else{
            System.out.println("[*] Comando desconocido...");
        }
    }

    public void printStateToCsv(int step, FileWriter out){
        try {
            out.write(String.valueOf(step));
            for (Door item : doors){
                out.write(",");
                out.write(String.valueOf(item.getState()));
            }
            for (Window item : windows){
                out.write(",");
                out.write(String.valueOf(item.getState()));
            }
            out.write(",0,0\n");

        } catch (IOException e){
            System.out.println("[*] Error durante escritura.");
            e.printStackTrace();
        }
    }

    public void makeHeaderInCsvFile(FileWriter out){
        try {
            out.write("Step");
            for (int i = 0; i < doors.size(); i++){
                out.write(",d"+i);
            }
            for (int i = 0; i < windows.size(); i++){
                out.write(",w"+i);
            }
            out.write(",Sirena,Central\n");
        } catch(IOException e) {
            System.out.println("[*] Error durante escritura.");
            e.printStackTrace();
        }
    }
    public static void main(String [] args) throws IOException {

        Scanner in = new Scanner(new File("src/config.txt"));
        //System.out.println("File: " + args[0]);

        Stage1 stage = new Stage1();
        stage.readConfiguration(in);
        stage.getAndSubmitUserInteraction(new Scanner(System.in));
    }
}
