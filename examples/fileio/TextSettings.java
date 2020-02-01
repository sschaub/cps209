import java.io.*;

public class TextSettings {
    static final String SETTINGS_FILENAME = "AppSettings.txt";

    public static void main(String[] args)  {
        try {
            WriteSettings();
            ReadSettings();
        } catch (IOException e) {
            System.out.println("Oops! Something bad happened: " + e);
            
        }
    }

    static void WriteSettings() throws IOException  {
        int secondsToSelfDestruct = 567;
        String color = "Blue";
        boolean totalDestruction = false;
        try (var writer = new PrintWriter(new FileWriter(SETTINGS_FILENAME))) {
            writer.println(secondsToSelfDestruct);
            writer.println(color);
            writer.println(totalDestruction);
        }

    }

    static void ReadSettings() throws IOException {
        int secondsToSelfDestruct;
        String color;
        boolean totalDestruction;
        try (var rd = new BufferedReader(new FileReader(SETTINGS_FILENAME))) {
            String line = rd.readLine();
            secondsToSelfDestruct = Integer.parseInt(line);
            color = rd.readLine();
            totalDestruction = Boolean.parseBoolean(rd.readLine());
        }
        System.out.println("Seconds to self destruct: " + secondsToSelfDestruct);
        System.out.println("Color: " + color);
        System.out.println("Total destruction: " + totalDestruction);        
    }
}
