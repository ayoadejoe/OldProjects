package baseGui;

import javax.swing.JOptionPane;


public class TestMemory {
     
    public static void main(String [] args) {
         
        int mb = 1024*1024;
         
        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
         
        //("##### Heap utilization statistics [MB] #####");
         
        //Print used memory
        //("Used Memory:"
           // + (runtime.totalMemory() - runtime.freeMemory()) / mb);
 
        //Print free memory
        //("Free Memory:"
           // + runtime.freeMemory() / mb);
         
        //Print total available memory
        //("Total Memory:" + runtime.totalMemory() / mb);
 
        //Print Maximum available memory
        //("Max Memory:" + runtime.maxMemory() / mb);
        
        JOptionPane.showMessageDialog(null, "Used Memory:"
            + (runtime.totalMemory() - runtime.freeMemory()) / mb+"  \n"+
            "Free Memory:"+ runtime.freeMemory() / mb+"  \n"+
            "Total Memory:" + runtime.totalMemory() / mb+"  \n"+
            "Max Memory:" + runtime.maxMemory() / mb);
        System.exit(0);
    }
}