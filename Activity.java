import java.util.ArrayList;
import java.util.Date;

public class Activity extends Node{

    /*private enum Indexes{
        Http = 0, USB = 1, LOGIN = 2;
    }*/

    //public ArrayList < ArrayList<Action> > actions; 
    //public ArrayList <Node> actions;

    public Activity () 
    {
       // actions = new ArrayList< ArrayList < Action >> ();
        children.add(new CommonActivity ());
        children.add(new CommonActivity ());
        children.add(new CommonActivity ());
    }

    public boolean addAction(Action action){
        CommonActivity act = null;

        if(action instanceof Url){
            act = (CommonActivity) children.get(0);
        }
        else if(action instanceof Usb){
            act = (CommonActivity) children.get(1);
        }
        else if(action instanceof Login){
            act = (CommonActivity) children.get(2);
        }
        if(act != null && act.addAction(action)){
            histogram[action.date.getHours()]+=1;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append(children.get(0).toString()).append("\n");
        string.append(children.get(1).toString()).append("\n");
        string.append(children.get(2).toString()).append("\n");
        return string.toString();
    }
}
