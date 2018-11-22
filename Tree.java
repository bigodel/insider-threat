public class Tree {
    
    
    private enum LogType {
        Http, Device, Login, LDAPLog;
    }
    LogType currentLogType;
    Node root;

    public Tree(){
        root = new Node ();
    }

    public void addLogEntry(LogEntry log){

        if (log instanceof HttpLog){
           currentLogType = LogType.Http;
           log = (HttpLog) log;

        }
        else if (log instanceof LoginLog){

           currentLogType = LogType.Login;
        }
        else if (log instanceof DeviceLog){

           currentLogType = LogType.Device;
        }
        else if (log instanceof LDAPLog){

           currentLogType = LogType.LDAPLog;
        }
        // stub
    }

    
    private final class TreeLevels{
    
        public static final int root = 0;
        public static final int user = 1;
        public static final int timeWindow = 2;
        public static final int pc = 3;
        public static final int activity = 4;

    }

    private final class ActivityIndexes{
        public static final int Http = 0;
        public static final int USB = 1;
        public static final int LOGIN = 2;
    }
}
