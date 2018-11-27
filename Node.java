import java.util.ArrayList;
import java.util.Date ;

public class Node {

    public ArrayList<Node> children;
    public int[] histogram = new int [24];

    public Node()
    {
        children = new ArrayList<> ();
    }

    public void addChildren(Node node)
    {
        children.add(node);
    }

    public void incrementHistogram(Date date)
    {
        histogram[date.getHours()] += 1;
    }
}
