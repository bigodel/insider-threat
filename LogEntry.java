
/**
 * Abstract class for a Log file representation
 * @author cyborg
 */
abstract public class LogEntry {
    
    String id;

    /**
     *
     * @param id action Id
     */
    public LogEntry(String id){
        this.id = id;
    }

    public String toString(){ return this.id;}
}
