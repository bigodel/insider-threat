import java.util.ArrayList;
import java.util.Date;

public class Tree
{
    private enum LogType
    {
        Http, Device, Login, LDAP;
    }

    LogType currentLogType;
    Node root;

    public Tree()
    {
        root = new Node ();
    }

    public void addLogEntry(LogEntry log)
    {
        if (log instanceof HttpLog) {
            currentLogType = LogType.Http;
        }
        else if (log instanceof LoginLog) {
            currentLogType = LogType.Login;
        }
        else if (log instanceof DeviceLog) {
            currentLogType = LogType.Device;
        }
        else if (log instanceof LDAPLog) {
            currentLogType = LogType.LDAP;
        }

        switch (currentLogType) {
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

        if (!root.getChildren().isEmpty()) {
            ArrayList<Node> children = root.getChildren();
            for (Node node : children) {
                UserField userNode = (UserField) node;

                if (userNode.getUser_id() != null &&
                    userNode.getUser_id().contains(log.getUser_id())) {

                    user = userNode;
                }
            }
        }

        if (user == null) {
            user = new UserField(log);
            root.addChildren(user);
        }
        else {
            user.updateFields(log.getEmployee_name(), log.getDomain(),
                              log.getEmail(), log.getRole());
        }
    }

    private void addCommonLog(CommonLog log)
    {
        UserField user = null;
        ArrayList<Node> children = root.getChildren();
        
        if (!children.isEmpty()) {
            for (Node node : children) {
                UserField userNode = (UserField) node;
                
                if (userNode.getUser_id().equals(log.getUser())) {
                    user = userNode;
                }
            }
        }
        
        if (user == null) {
            user = new UserField(log.getUser());
            root.addChildren(user);
        }
        
        // this is a stub version, using a undefined timewindow
        TimeWindow timeWindow = null;
        if (user.getChildren().isEmpty()){
            timeWindow = new TimeWindow();
            user.addChildren(timeWindow);
        }
        else {
            timeWindow = (TimeWindow) user.getChildren().get(0);
        }
        
        ArrayList<Node> TimeChildren = timeWindow.getChildren();

        Computer pc = null;
        for (Node node : TimeChildren) {
            Computer computerNode = (Computer) node;
            if (computerNode.getUser_pc().equals(log.getPc())) {
                pc = computerNode;
            }
        }
        
        if (pc == null) {
            pc = new Computer(log.getPc());
            timeWindow.addChildren(pc);
        }

        ArrayList<Node> PcChildren = pc.getChildren();

        Activity activity = null;
        if (PcChildren.isEmpty()) {
            activity = new Activity();
            PcChildren.add(activity);
        }
        else {
            activity = (Activity) PcChildren.get(0);
        }
        
        Action action;
        boolean succesfullInsertion = false;
        
        switch (currentLogType) {
            case Http:
                action = new Url(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
            case Device:
                action = new Usb(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
            case Login:
                action = new Login(log.getActivity(), log.getDate());
                
                if (activity.addAction(action)) {
                    succesfullInsertion = true;
                }
                
                break;
        }
        if (succesfullInsertion) {
            Date dateOfAction = log.getDate();
            user.incrementHistogram(dateOfAction);
            pc.incrementHistogram(dateOfAction);
            activity.incrementHistogram(dateOfAction);
        }
    }
        
        @Override
        public String toString()
        {
            StringBuilder string = new StringBuilder("Tree \n");
           
            ArrayList<Node> children = root.getChildren();
            for (Node node : children) {
                string.append(node).append("\n");
            }
            
            return string.toString();
        }
    }
