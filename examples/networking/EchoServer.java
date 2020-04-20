import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    final static int PORT = 6000;

    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(PORT);
        System.out.println("EchoServer listening on port " + PORT + "...");
        while (true) {
            System.out.println("Waiting for client to connect...");
            try (Socket client = serverSocket.accept()) {
                System.out.println("Received incoming connection from client: " + client.getInetAddress());
                handleClient(client);

            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }
    }

    private static void handleClient(Socket client) throws IOException {
        var reader = new Scanner(client.getInputStream());
        var writer = new PrintWriter(client.getOutputStream(), true);

        // Now, communicate with client
        writer.println("Welcome to the Echo Server.");
        while (reader.hasNext()) {
            String line = reader.nextLine();
            System.out.println("Received: " + line);
            writer.println(line);
        }
}
}