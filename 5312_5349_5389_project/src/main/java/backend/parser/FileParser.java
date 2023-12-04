package backend.parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import backend.dom.Task;

public class FileParser {
	
	public ArrayList<Task> parser(String filePath ,String delimiter) throws IOException  {
        
		Task task;
		String st;
		File file = new File(filePath);		
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<Task> Tasks = new ArrayList<Task>();
		ArrayList<Task> toplevelTasks = new ArrayList<Task>();
		
		while((st=br.readLine()) != null){
			task = new Task();
	        String[] arry = {null,null,null,null,null,null};
	        arry = st.split(delimiter);

	        String TaskId = arry[0];
	        task.setId(Integer.parseInt(TaskId));
	            
	        String TaskText = arry[1];
	        task.setTaskText(TaskText);
	            
	        String MamaId = arry[2];
	        task.setMamaId(Integer.parseInt(MamaId));

	        if(MamaId.equals("0")) {
	            task.isTopLevel();
	        }
	        String Start = null;	            
	        String End = null;
	        String Cost = null;
	        try {
	            Start = arry[3];
	            task.setStart(Integer.parseInt(Start));
	                
	        }
	        catch(Exception ArrayIndexOutOfBoundsException) {
	            	
	        }
	        try {
	            End = arry[4];
	            task.setEnd(Integer.parseInt(End));
	        }
	        catch(Exception ArrayIndexOutOfBoundsException) {
	                
	        }
	        try {
	            Cost = arry[5];
	            task.setCost(Integer.parseInt(Cost));
	        }
	        catch(Exception ArrayIndexOutOfBoundsException) {
	                
	        }
	        Tasks.add(task);      
	    }
		 
		br.close();
		 
		for(Task thistask:Tasks) {
			int cost = 0;
			if(thistask.getIfmum() == true) {
				toplevelTasks.add(thistask);

				for(Task thistask2:Tasks) {

					if(thistask.getId() == thistask2.getMamaId()){
						thistask2.setMama(thistask);
						thistask.setSubTasks(thistask2);
						cost += thistask2.getCost();
						thistask.setCost(cost);
						
						if(thistask.getEnd() < thistask2.getEnd()) {
							thistask.setEnd(thistask2.getEnd());
						}				 
					}
				}
			}
		}
		for(Task thistask:toplevelTasks) {
			int superStart = 100000000;
			for(Task subtasks:thistask.getSubTasks()) {
				int start = subtasks.getStart();
				if(superStart > start) {
					superStart = start;
					thistask.setStart(superStart);
					 
				}
			}
			 
		}        
        return Tasks;

	}
}
