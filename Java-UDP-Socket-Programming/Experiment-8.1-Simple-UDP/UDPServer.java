import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        // Bind the server socket to UDP port 2000.
        try (DatagramSocket socket = new DatagramSocket(2000)) {
            System.out.println("UDP server waiting on port 2000...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // receive() blocks until a datagram arrives.
            socket.receive(packet);

            String message = new String(
                    packet.getData(),
                    packet.getOffset(),
                    packet.getLength(),
                    StandardCharsets.UTF_8
            );

            System.out.println("Received ---> " + message);
        }
    }
}
