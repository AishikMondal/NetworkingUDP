import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    private static final int LOCAL_PORT = 2000;
    private static final int CLIENT_PORT = 2001;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {
        InetAddress clientAddress = InetAddress.getByName(HOST);

        try (DatagramSocket socket = new DatagramSocket(LOCAL_PORT);
             BufferedReader keyboard = new BufferedReader(
                     new InputStreamReader(System.in))) {

            System.out.println("Server started on port " + LOCAL_PORT
                    + ". Type /exit to quit.");

            // A dedicated thread receives messages while the main thread sends.
            Thread receiver = new Thread(() -> receiveMessages(socket),
                    "udp-server-receiver");
            receiver.setDaemon(true);
            receiver.start();

            String line;
            while (!socket.isClosed() && (line = keyboard.readLine()) != null) {
                byte[] data = line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(
                        data, data.length, clientAddress, CLIENT_PORT
                );
                socket.send(packet);

                if ("/exit".equals(line)) {
                    break;
                }
                System.out.print("You: ");
            }
        }
    }

    private static void receiveMessages(DatagramSocket socket) {
        try {
            while (!socket.isClosed()) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(
                        packet.getData(),
                        packet.getOffset(),
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println("\nClient: " + message);
                if ("/exit".equals(message)) {
                    System.out.println("Client sent the exit command.");
                    socket.close();
                    return;
                }
                System.out.print("You: ");
            }
        } catch (SocketException e) {
            // Expected when the local socket is closed during shutdown.
        } catch (Exception e) {
            System.err.println("Receive error: " + e.getMessage());
        }
    }
}
