import java.util.ArrayList;

public class Node {

    ArrayList<Node> children;
    int [] histogram = new int [24];

    public Node() { }

    public void addChildren(Node node){
        children.add(node);
    }
}
