import java.util.Date;

/**
 *
 * @author cyborg
 */
abstract public class Action extends Node{
   
   //String id;
   private String action;
   private Date date;

    /**
     *
     * @param action The action that was performed 
     * @param date The date when the action was performed
     */
    public Action (/*String id*/ String action ,Date date)
   {
//    this.id = id;
    this.action = action;
    this.date = date;
   } 

   @Override
   public String toString()
   {
       /*return this.id + " " + */ return this.action; 
   }

    /**
     *
     * @return Returns the performed action
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @return Returns the date when the action was performed
     */
    public Date getDate() {
        return date;
    }
}
