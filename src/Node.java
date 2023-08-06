import java.io.*;
import java.net.*;
import java.util.*;

public class Node {
    private int nodeId;
    private List<Node> peers;
    private ServerSocket serverSocket;

    public Node(int nodeId, int port) {
        this.nodeId = nodeId;
        peers = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            new Thread(this::acceptConnections).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinNetwork(Node peer) {
        peers.add(peer);
    }

    public void broadcastMessage(String message) {
        for (Node peer : peers) {
            sendMessage(peer, message);
        }
    }

    private void acceptConnections() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleConnection(clientSocket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleConnection(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = in.readLine();
            System.out.println("Received message from peer " + clientSocket.getInetAddress() + ": " + message);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Node peer, String message) {
        try {
            Socket socket = new Socket(peer.getAddress(), peer.getPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return "localhost";
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
