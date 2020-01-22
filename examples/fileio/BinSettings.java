import java.io.*;
import java.nio.file.*;

public class BinSettings {
    static final String fileName = "AppSettings.dat";

    public static void main(String[] args) throws IOException {
        writeDefaultValues();
        readValues();
        readValuesAlt();
    }

    public static void writeDefaultValues() throws IOException {
        int secondsToSelfDestruct = 567;
        String color = "Blue";
        boolean totalDestruction = true;
        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(fileName))) {
            writer.writeInt(secondsToSelfDestruct);
            writer.writeUTF(color);
            writer.writeBoolean(totalDestruction);
        }
    }

    public static void readValues() throws IOException {
        int secondsToSelfDestruct;
        String color;
        boolean totalDestruction;

        if (Files.exists(Paths.get(fileName))) {
            try (var reader = new DataInputStream(new FileInputStream(fileName))) {
                secondsToSelfDestruct = reader.readInt();
                color = reader.readUTF();
                totalDestruction = reader.readBoolean();
            }

            System.out.println("Seconds to self destruct: " + secondsToSelfDestruct);
            System.out.println("Color: " + color);
            System.out.println("Total destruction: " + totalDestruction);
        }
    }

    public static void readValuesAlt() throws IOException {
        int secondsToSelfDestruct;
        String color;
        boolean totalDestruction;

        if (Files.exists(Paths.get(fileName))) {
            try (var reader = new FileInputStream(fileName)) {
                // secondsToSelfDestruct = reader.ReadInt32();
                byte[] data = new byte[4];
                reader.read(data);
                secondsToSelfDestruct = java.nio.ByteBuffer.wrap(data).order(java.nio.ByteOrder.BIG_ENDIAN).getInt();
                // secondsToSelfDestruct = ((data[0] && 0xff) << 24) + (data[1] << 16) + (data[2] << 8) + data[3];

                // Read string
                byte[] lenData = new byte[2];
                reader.read(lenData);
                int len = java.nio.ByteBuffer.wrap(lenData).order(java.nio.ByteOrder.BIG_ENDIAN).getShort();
                byte[] colorData = new byte[len];
                reader.read(colorData);
                color = "";
                for (byte b : colorData) {
                    color += (char) b;
                }

                // Read boolean
                int destruction = reader.read();
                totalDestruction = (destruction == 1);
            }

            System.out.println("Seconds to self destruct: " + secondsToSelfDestruct);
            System.out.println("Color: " + color);
            System.out.println("Total destruction: " + totalDestruction);
        }
    }

}