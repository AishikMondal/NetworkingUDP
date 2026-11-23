import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {

        // Client uses port 2001 and sends messages to server port 2000.
        DatagramSocket socket = new DatagramSocket(2001);
        InetAddress ip = InetAddress.getByName("localhost");

        // This thread keeps receiving messages while we type.
        Thread receiver = new Thread(() -> {
            try {
                while (true) {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet =
                            new DatagramPacket(buffer, buffer.length);

                    socket.receive(packet);

                    String message = new String(
                            packet.getData(), 0, packet.getLength()
                    );

                    System.out.println("\nServer: " + message);

                    if (message.equals("/exit")) {
                        System.out.println("Server left the chat.");
                        socket.close();
                        break;
                    }

                    System.out.print("You: ");
                }
            } catch (Exception e) {
                // The socket closes when the chat ends.
            }
        });

        receiver.setDaemon(true);
        receiver.start();

        // Main thread sends messages typed by the user.
        Scanner sc = new Scanner(System.in);
        System.out.println("Client started. Type /exit to quit.");
        System.out.print("You: ");

        while (!socket.isClosed()) {
            String message = sc.nextLine();
            byte[] data = message.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    data, data.length, ip, 2000
            );

            socket.send(packet);

            if (message.equals("/exit")) {
                socket.close();
                break;
            }

            System.out.print("You: ");
        }

        sc.close();
    }
}
