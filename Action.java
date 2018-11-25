import java.util.Date;

/**
 *
 * @author cyborg
 */
abstract public class Action extends Node{
   
   //String id;
   String action;
   Date date;

    /**
     *
     * @param id
     * @param action
     * @param date
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
}
