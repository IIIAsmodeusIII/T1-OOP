import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

public class Stage3 {
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private ArrayList<PIR_Detector> PIRs;
    private Central central;
    private Siren siren;
    // Pattern Regex
    Pattern pattern;
    Matcher matcher;
    public Stage3() {
        doors = new ArrayList<>();
        windows = new ArrayList<>();
        PIRs = new ArrayList<>();

        pattern = Pattern.compile("([kdwcp])(\\d?)\\s(.*)");
    }
    public void readConfiguration(Scanner in){

        // Get first line
        int nDoors   = in.nextInt();
        int nWindows = in.nextInt();
        int nPirs    = in.nextInt();

        // PIRs
        float x;
        float y;
        int angle;
        int sensingAngle;
        int sensingDistance;

        for(int i=0; i < nPirs; i++){
            x = in.nextFloat();
            y = in.nextFloat();

            angle           = in.nextInt();
            sensingAngle    = in.nextInt();
            sensingDistance = in.nextInt();
            
            PIRs.add(new PIR_Detector(x, y, angle, sensingAngle, sensingDistance));
        }

        // Sound file
        String sound_file = in.next();

        for (int i = 0; i < nDoors; i++){
            doors.add(new Door());
        }
        for (int i = 0; i < nWindows; i++){
            windows.add(new Window());
        }

        in.close();

        central = new Central(doors, windows);
        siren   = new Siren("../src/" + sound_file);

        central.setSiren((siren));
    }
    public void getAndSubmitUserInteraction (Scanner in){
        // Menu
        int step = 0;
        boolean sirenShouldSound = false;

        try {
            File file = new File("output.csv");
            FileWriter out = new FileWriter(file);
            makeHeaderInCsvFile(out);

            while (true) {
                // Print state to csv lol
                printStateToCsv(step, sirenShouldSound, out);

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

                // Next step and check zone
                if(central.getState()){
                    boolean status = central.checkZone();
                    if(!status){
                        sirenShouldSound = true;
                    }else{
                        sirenShouldSound = false;
                    }
                }else{
                    sirenShouldSound = false;
                }

                if(sirenShouldSound){
                    siren.play();
                }else{
                    try {
                        siren.stop();
                    } catch (Exception e) {
                        System.out.print("");
                    }
                }
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
                case "k" -> {
                    central.arm(matcher.group(3));
                }
            }
        }else{
            System.out.println("[*] Comando desconocido...");
        }
    }

    public void printStateToCsv(int step, boolean sirenSound, FileWriter out){
        try {
            // Write Doors and Windows status
            out.write(String.valueOf(step));
            for (Door item : doors){
                out.write(",");
                out.write(String.valueOf(item.getState()));
            }
            for (Window item : windows){
                out.write(",");
                out.write(String.valueOf(item.getState()));
            }

            // Write siren status
            if(sirenSound){
                out.write(",1");
            }else{
                out.write(",0");
            }

            // Write central status
            if(central.getState()){
                out.write(",1");
            }else{
                out.write(",0");
            }

            // next line
            out.write("\n");

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

        Scanner in = new Scanner(new File("config/config.txt"));
        //System.out.println("File: " + args[0]);

        Stage3 stage = new Stage3();
        stage.readConfiguration(in);
        stage.getAndSubmitUserInteraction(new Scanner(System.in));
    }
}
