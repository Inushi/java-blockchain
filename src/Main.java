public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1, 5000);
        Node node2 = new Node(2, 5001);
        Node node3 = new Node(3, 5002);
        Node node4 = new Node(4, 5003);

        node1.joinNetwork(node2);
        node1.joinNetwork(node3);
        node1.joinNetwork(node4);

        node1.broadcastMessage("Hello from node 1!");
    }
}