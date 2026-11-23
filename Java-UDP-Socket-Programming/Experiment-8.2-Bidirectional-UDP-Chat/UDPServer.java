import java.net.*;
import java.util.Scanner;

public class UDPServer {

    public static void main(String[] args) throws Exception {

        // Server uses port 2000 and sends messages to client port 2001.
        DatagramSocket socket = new DatagramSocket(2000);
        InetAddress ip = InetAddress.getByName("localhost");

        // Receiver thread: keeps listening while the main thread is typing.
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

                    System.out.println("\nClient: " + message);

                    if (message.equals("/exit")) {
                        System.out.println("Client left the chat.");
                        socket.close();
                        System.exit(0);
                    }

                    System.out.print("You: ");
                }
            } catch (Exception e) {
                // Socket closes when the chat ends.
            }
        });

        receiver.setDaemon(true);
        receiver.start();

        // Main thread: reads and sends messages.
        Scanner sc = new Scanner(System.in);
        System.out.println("Server started. Type /exit to quit.");
        System.out.print("You: ");

        while (!socket.isClosed()) {
            String message = sc.nextLine();
            byte[] data = message.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    data, data.length, ip, 2001
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
