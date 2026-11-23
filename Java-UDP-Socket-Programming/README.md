# Java UDP Socket Programming

Two Java networking practicals using UDP (`DatagramSocket` and `DatagramPacket`).

## Repository structure

```text
Java-UDP-Socket-Programming/
├── README.md
├── .gitignore
├── Experiment-8.1-Simple-UDP/
│   ├── UDPClient.java
│   └── UDPServer.java
└── Experiment-8.2-Bidirectional-UDP-Chat/
    ├── UDPClient.java
    └── UDPServer.java
```

## Requirements

- JDK 8 or later
- Two terminal windows for each experiment

Check Java installation:

```bash
java -version
javac -version
```

## Experiment 8.1 — Simple UDP

The client sends `This is UDP Client` to the server on UDP port `2000`.

### Compile

From the repository root:

```bash
javac Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP/UDPServer.java
javac Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP/UDPClient.java
```

### Run

Terminal 1 (start the server first):

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP UDPServer
```

Terminal 2:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP UDPClient
```

Expected server output:

```text
Received ---> This is UDP Client
```

## Experiment 8.2 — Bi-directional UDP chat

Both applications bind to a local UDP port and run a receiving thread alongside the sending loop.

- Server local port: `2000`
- Client local port: `2001`
- Client sends to server port `2000`
- Server sends to client port `2001`

### Compile

```bash
javac Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat/UDPServer.java
javac Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat/UDPClient.java
```

### Run

Terminal 1:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat UDPServer
```

Terminal 2:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat UDPClient
```

Type messages in either terminal. Type `/exit` to send a disconnect message and close that side.

## Concepts demonstrated

- `DatagramSocket`: UDP communication endpoint
- `DatagramPacket`: datagram payload and addressing
- `InetAddress`: IP address resolution
- `send()` and `receive()`: UDP datagram transmission
- `Thread`: independent sending and receiving execution
- `getData()` and `getLength()`: extracting the received payload

## Important UDP note

UDP is connectionless and does not guarantee delivery, ordering, or retransmission. These examples are educational localhost demonstrations, not production chat software.

## Troubleshooting

- Start the server before the client.
- Ensure ports `2000` and `2001` are not already in use.
- Run each program in a separate terminal.
- If using two different computers, replace `localhost` with the server computer's reachable IP address and configure the peer addresses and ports accordingly.
