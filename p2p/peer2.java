import java.io.*;
import java.net.*;
import java.util.Scanner;

public class peer2 {
    private static final int LISTEN_PORT = 6000;
    private static final String PEER_IP = "172.20.42.60"; // Replace with Peer1 IP if on different machine
    private static final int PEER_PORT = 5000;

    public static void main(String[] args) throws IOException {
        // Start server thread to receive messages
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
                System.out.println("Peer2 listening on port " + LISTEN_PORT);
                while (true) {
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg = in.readLine();
                    if (msg != null) {
                        System.out.println("Peer1: " + msg);
                    }
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        // Sending messages
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            System.out.print("You: ");
            message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) break;

            try (Socket socket = new Socket(PEER_IP, PEER_PORT)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                System.out.println("Could not send message to Peer1.");
            }
        }

        System.out.println("Chat ended.");
    }
}