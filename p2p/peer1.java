import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Peer1 {

    public static void main(String[] args) throws IOException {
        int portlocal = Integer.parseInt(args[0]);
        int portremote= Integer.parseInt(args[1]);
        String ipRemote = args[2];
        // Start server thread to receive messages
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(portlocal)) {
                System.out.println("Peer1 listening on port " + portlocal);
                while (true) {
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg = in.readLine();
                    if (msg != null) {
                        System.out.println("Peer2: " + msg);
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

            try (Socket socket = new Socket(ipRemote, portremote)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                System.out.println("Could not send message to Peer2.");
            }
        }

        System.out.println("Chat ended.");
    }
}