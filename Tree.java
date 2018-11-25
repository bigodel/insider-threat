public class Tree {
    
    
    private enum LogType {
        Http, Device, Login, LDAP;
    }
    LogType currentLogType;
    Node root;

    public Tree(){
        root = new Node ();
    }

    public void addLogEntry(LogEntry log){

        if (log instanceof HttpLog){
           currentLogType = LogType.Http;
        }
        else if (log instanceof LoginLog){
           currentLogType = LogType.Login;
        }
        else if (log instanceof DeviceLog){

           currentLogType = LogType.Device;
        }
        else if (log instanceof LDAPLog){

           currentLogType = LogType.LDAP;
        }

        switch(currentLogType) {
            case LDAP:
            addLDAPLog((LDAPLog) log);
            break;
            
            default:
            addCommonLog((CommonLog) log);
        }
    }

    private void addLDAPLog(LDAPLog log)
    {
        UserField user = null;

        if (!root.children.isEmpty()){
            for(Node node : root.children){
                UserField userNode = (UserField) node;
                if(userNode.user_id != null &&
                    userNode.user_id.equals("DTAA/" + log.user_id)){
                   
                    user = userNode;
                }
            }
        }
    
        if(user == null){
            user = new UserField(log);
            root.children.add(user);
        }

        else{
            user.updateFields(log.employee_name, log.domain, log.email, log.role);
        }
    }

    private void addCommonLog(CommonLog log)
    {
        UserField user = null;
        
        if (!root.children.isEmpty()){
            for(Node node : root.children){
                UserField userNode = (UserField) node;
                if(userNode.user_id.equals(log.user)){
                    user = userNode;
                }
            }
        }
        
        if(user == null){
            user = new UserField(log.user);
            root.children.add(user);
        }
        
        // this is a stub version, using a undefined timewindow
        TimeWindow timeWindow = null;
        if (user.children.size() == 0){
            timeWindow = new TimeWindow();
            user.children.add(timeWindow);
        }
        else {
            timeWindow = (TimeWindow) user.children.get(0);
        }
        
        Computer pc = null;
        for(Node node : timeWindow.children){
            Computer computerNode = (Computer) node;
            if(computerNode.user_pc.equals(log.pc)){
                pc = computerNode;
            }
        }
        
        if(pc == null)
        {
            pc = new Computer(log.pc);
            timeWindow.children.add(pc);
        }
        
        Activity activity = null;
        if(pc.children.size() == 0){
            activity = new Activity();
            pc.children.add(activity);
        }
        
        else{
            activity = (Activity) pc.children.get(0);
        }
        
        Action action;
        boolean succesfullInsertion = false;
        
        switch(currentLogType){
            
            case Http:
                action = new Url(/*log.id, */log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
            case Device:
                
                action = new Usb(/*log.id, */ log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
            case Login:
                action = new Login(/*log.id, */ log.getActivity(), log.getDate());
                if(activity.addAction(action)){
                    succesfullInsertion = true;
                }
                break;
        }
        if(succesfullInsertion){
            int hourOfAction = log.date.getHours();
            user.histogram[hourOfAction] += 1;
            pc.histogram[hourOfAction] += 1;
            activity.histogram[hourOfAction] += 1;
        }
    }

    public String toString()
    {
        /* teste */
      /*  if(currentLogType == LogType.Http){

            StringBuilder string = new StringBuilder("Tree \n");
            Node node = root.children.get(0);
            string.append(node).append("\n");
            return string.toString();
        }*/

        StringBuilder string = new StringBuilder("Tree \n");
        for(Node node : root.children ){
            string.append(node).append("\n");
        }
        return string.toString();

        /*Version below with histograms only*/
    }
   /* 
    private final class ActivityIndexes{
        public static final int Http = 0;
        public static final int USB = 1;
        public static final int LOGIN = 2;
    }*/
}
