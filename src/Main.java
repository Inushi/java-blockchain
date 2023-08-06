public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("192.168.0.101", 5000);
        Node node2 = new Node("192.168.0.102", 5001);

        node1.joinNetwork(node2.getIpAddress());
        node2.joinNetwork(node1.getIpAddress());

        node1.broadcastMessage("Hello from node 1!");
    }
}