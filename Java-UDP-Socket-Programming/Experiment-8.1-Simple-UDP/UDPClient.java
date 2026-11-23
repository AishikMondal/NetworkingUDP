import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        // Create a UDP socket. The OS assigns an available local port.
        try (DatagramSocket socket = new DatagramSocket()) {
            String message = "This is UDP Client";
            byte[] data = message.getBytes(StandardCharsets.UTF_8);

            // Resolve the server address (the same computer for this demo).
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Build a datagram with payload, destination IP, and destination port.
            DatagramPacket packet = new DatagramPacket(
                    data, data.length, serverAddress, 2000
            );

            socket.send(packet);
            System.out.println("Message sent from client");
        }
    }
}
