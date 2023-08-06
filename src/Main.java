public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("172.20.10.6", 5000);
        Node node2 = new Node("172.20.10.8", 5001);
        Node node3 = new Node("172.20.10.14", 5002);

        node1.joinNetwork(node2.getIpAddress());
        node1.joinNetwork(node3.getIpAddress());

        // Start broadcasting messages
        node1.broadcastMessage("Hello from node 1!");
    }

}