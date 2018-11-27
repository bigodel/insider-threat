import java.util.ArrayList;
import java.util.Arrays;

class CommonActivity extends Node
{

    ArrayList<Action> actions;

     
    public CommonActivity() {
        actions = new ArrayList<Action> ();
    }

    public boolean addAction(Action action)
    {
        if(!actions.contains(action)){
            actions.add(action);
            this.incrementHistogram(action.getDate());
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Histogram : ");
        string.append(Arrays.toString(getHistogram()));
        return string.toString();
    }

}
