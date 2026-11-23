# Java UDP Socket Programming — Detailed Lab Guide

This folder contains two Java UDP practicals. The full illustrated explanation, UDP theory, packet layout, Mermaid architecture/sequence diagrams, code walkthroughs, execution commands, and troubleshooting guide are maintained in the repository's main [README.md](../../README.md).

## Experiments

- **Experiment 8.1 — Simple UDP:** one client datagram sent to a server listening on port 2000.
- **Experiment 8.2 — Bidirectional UDP Chat:** server on port 2000 and client on port 2001, each with a sending loop and a dedicated receiving thread.

## Source files

| Experiment | File |
|---|---|
| Simple UDP | [UDPClient.java](Experiment-8.1-Simple-UDP/UDPClient.java) |
| Simple UDP | [UDPServer.java](Experiment-8.1-Simple-UDP/UDPServer.java) |
| Bidirectional chat | [UDPClient.java](Experiment-8.2-Bidirectional-UDP-Chat/UDPClient.java) |
| Bidirectional chat | [UDPServer.java](Experiment-8.2-Bidirectional-UDP-Chat/UDPServer.java) |

## Quick start

Requires JDK 8 or later. Run commands from the repository root.

### Simple UDP

```bash
javac Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP/UDPServer.java
javac Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP/UDPClient.java
```

Start the server in terminal 1:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP UDPServer
```

Then run the client in terminal 2:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.1-Simple-UDP UDPClient
```

### Bidirectional UDP chat

```bash
javac Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat/UDPServer.java
javac Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat/UDPClient.java
```

Start server:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat UDPServer
```

Start client in a second terminal:

```bash
java -cp Java-UDP-Socket-Programming/Experiment-8.2-Bidirectional-UDP-Chat UDPClient
```

Type `/exit` to send the application's exit command.

## Port configuration

| Program | Local UDP port | Destination |
|---|---:|---:|
| Server | 2000 | Client port 2001 |
| Client | 2001 | Server port 2000 |

**Note:** UDP is connectionless and best-effort. It does not guarantee delivery, ordering, or retransmission. The chat's `/exit` command is an application-level message, not a UDP protocol feature.
