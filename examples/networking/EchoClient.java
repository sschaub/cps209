import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    final static int PORT = 6000;

    public static void main(String[] args) throws IOException {

        var scanner = new Scanner(System.in);

        // Connect to server
        var addr = InetAddress.getByName("localhost");
        System.out.println("EchoServer connecting to server...");
        var socket = new Socket(addr, PORT);

        // Prepare to communicate with server
        var reader = new Scanner(socket.getInputStream());
        var writer = new PrintWriter(socket.getOutputStream(), true);

        String line = reader.nextLine();
        System.out.println(line);
        try {
            while (!line.equals("quit")) {
                System.out.print("Enter a line to send to server (quit to end):");
                line = scanner.nextLine();
                if (!line.equals("quit")) {
                    writer.println(line);
                    line = reader.nextLine();
                    System.out.println("Received from server: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

    }
}