import java.util.Date;

public class HttpLog extends CommonLogs {
   
    String url;

    public HttpLog(String id, Date date,String user , String pc, String url)
    {
        super(id,date,user,pc);
        this.url = url;
    }
}
