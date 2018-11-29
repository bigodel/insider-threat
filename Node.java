import java.util.ArrayList;
import java.util.Date;

public class Node
{
    private ArrayList<Node> children;
    private int[] histogram = new int[24];

    public ArrayList<Node> getChildren()
    {
        return children;
    }

    public int[] getHistogram()
    {
        return histogram;
    }

    public Node()
    {
        children = new ArrayList<>();
    }

    final public void addChildren(Node node)
    {
        children.add(node);
    }

    public void incrementHistogram(Date date)
    {
        histogram[date.getHours()] += 1;
    }
}
