import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatServer {

    final static int PORT = 7000;

    static Map<String, ClientInfo> clients = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        var serverSocket = new ServerSocket(PORT);
        System.out.println("ChatServer listening on port " + PORT + "...");
        while (true) {
            System.out.println("Waiting for client to connect...");
            final Socket client = serverSocket.accept();
            System.out.println("Received incoming connection from client: " + client.getInetAddress());
            Thread.sleep(2000); // simulate delay
            var thread = new Thread(() -> handleClient(client));
            thread.start();
        }
    }

    static void handleClient(Socket client) {
        String handle = null;
        try {
            var reader = new Scanner(client.getInputStream());
            var writer = new PrintWriter(client.getOutputStream(), true);

            // Now, communicate with client
            writer.println("Welcome to the Chat Server. What is your handle?");
            handle = reader.nextLine();

            sendToAll(handle + " has entered the chat.");

            synchronized (clients) {
                clients.put(handle, new ClientInfo(reader, writer));
            }

            String line = "";
            while (!line.equals("quit") && reader.hasNext()) {
                line = reader.nextLine();
                System.out.println("Received from " + handle + ": " + line);
                Thread.sleep(1000); // simulate delay
                sendToAll(handle + ": " + line);
            }

        } catch (Exception e) {
            System.out.println("Exception occurred for " + handle + ": " + e.getMessage());
        } finally {

            try {
                System.out.println(handle + " disconnected.");
                synchronized (clients) {
                    clients.remove(handle);
                }
                client.close();
                sendToAll(handle + " left the chat.");
            } catch (Exception e) { }
        }

    }

    static void sendToAll(String msg) {
        synchronized (clients) {
            for (var clientInfo : clients.values()) {
                clientInfo.writer.println(msg);
            }
        }

    }
    
}

class ClientInfo {
    Scanner reader;
    PrintWriter writer;

    public ClientInfo(Scanner reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    
}