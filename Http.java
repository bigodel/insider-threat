public class Http extends Activity {

    ArrayList<String> urls;

    public Http (Date date)
    {
       super(date); 
    }

    public void addUrl(String url)
    {
        urls.add(url);
    }
}
