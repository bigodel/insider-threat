import java.util.LinkedList;
import java.util.Date;

public class Node
{
    private LinkedList<Node> children;
    private int[] histogram = new int[24];

    public LinkedList<Node> getChildren()
    {
        return children;
    }

    public int[] getHistogram()
    {
        return histogram;
    }

    public Node()
    {
        children = new LinkedList<>();
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
