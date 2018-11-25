import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
            this.histogram[action.date.getHours()] +=1;
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("Histogram : ");
        string.append(Arrays.toString(histogram));
        return string.toString();
    }

}
