import java.io.*;
import java.net.*;
import java.util.Scanner;

public class peer {
    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        // Start server thread to receive messages
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Peer is listening on port " + PORT);

        Thread serverThread = new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message = in.readLine();
                    if (message != null) {
                        System.out.println("Received: " + message);
                    }
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Sending messages continuously until "exit"
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            System.out.print("Enter message (type 'exit' to quit): ");
            message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) {
                break;
            }

            // Try to connect to another instance of Peer (e.g., same program running in another terminal)
            try (Socket socket = new Socket("localhost", PORT)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                System.out.println("Could not send message. Make sure another peer is listening on port " + PORT);
            }
        }

        System.out.println("Exiting chat...");
        serverSocket.close(); // Optionally close the server if you want to stop receiving too
    }
}