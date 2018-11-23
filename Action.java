import java.util.Date;

public class Action {
   
   String id;
   String action;
   Date date;

   public Action (String id, String action ,Date date)
   {
    this.id = id;
    this.action = action;
    this.date = date;
   } 

   @Override
   public String toString()
   {
       return this.id + this.action; 
   }
}
