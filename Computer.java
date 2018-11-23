public class Computer extends Node {
    
    String user_pc;

    public Computer (String user_pc)
    {
        this.user_pc = user_pc;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder("used_pc : " + this.user_pc + "\n");

        for(Node node : this.children){
           string.append("\n").append(node);
        }
        return string.toString();
    }
}
