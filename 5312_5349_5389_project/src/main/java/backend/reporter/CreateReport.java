package backend.reporter;

import backend.dom.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;




public class CreateReport {
    ArrayList<Task> alltasks = new ArrayList<Task>();
    String s = "TaskId\tTask Text\tMamaId\tStart\tEnd\tCost\t\n";
    String d = "TaskId Task Text MamaId Start End Cost";
    
    public boolean textConvert(String path,ArrayList<Task> tasks) {
    	if(!path.endsWith(".txt")) {
    		System.out.println("cant happen");
    		return false;
    	}
        for(Task i:tasks) {        
            alltasks.add(i);
            s += i.getTask()+"\n";          
        }
        
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("report didnt happen");
        }
        return true;
    }

    public boolean MdConvert(String path,ArrayList<Task> tasks){
    	if(!path.endsWith(".md")) {
    		System.out.println("cant happen");   		
    		return false;
    	}
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            
            writer.write("_"); 
            writer.write(d);
            writer.write("_"); 
            writer.write("\n");
            for(Task i:tasks) {
                String w ="";
                
                if(i.getIfmum()){
                    writer.write("\n");
                    writer.write("**");
                    alltasks.add(i);
                    w = i.getTask();
                    writer.write(w);
                    writer.write("**");
                    writer.write("\n");

                }
                else{
                    writer.write("\n"); 
                

                    alltasks.add(i);
                    w = i.getTask();
                    writer.write(w);
                    
                    writer.write("\n");
                }
                 
            }

            writer.close();
        } catch (IOException e) {
        	System.out.println("report didnt happen");
            e.printStackTrace();
        }
        return true;

    }

    public boolean htmlConvert(String path,ArrayList<Task> tasks){
        
        if(!path.endsWith(".html")) {
        	System.out.println("cant happen");
        	return false;
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("<html>");
            writer.write("\n");
            writer.write("<body>");
            writer.write("\n");
        
            writer.write(s);
            for(Task i:tasks) {
                String w ="";
                writer.write("\n"); 
                writer.write("<p>");

                alltasks.add(i);
                w = i.getTask();
                writer.write(w);
                writer.write("</p>");
                writer.write("\n"); 
            }
            
            writer.write("</body>");
            writer.write("\n");
            writer.write("</html>");

            writer.close();
        } catch (IOException e) {
        	System.out.println("report didnt happen");
            e.printStackTrace();
        }
        return true;
    }
}
