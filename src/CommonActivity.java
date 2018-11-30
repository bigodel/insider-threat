import java.util.LinkedList;
import java.util.Arrays;

class CommonActivity extends Node
{

    LinkedList<String> actions;

     
    public CommonActivity() {
        actions = new LinkedList<> ();
    }

    public boolean addAction(Action action)
    {
        if(!actions.contains(action.getAction())){
            actions.add(action.getAction());
            this.incrementHistogram(action.getDate());
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Histogram : ");
        string.append(Arrays.toString(getHistogram())).append("\n");

        for(String action : actions){
            string.append(action).append(" ");
        }
        return string.toString();
    }

}
