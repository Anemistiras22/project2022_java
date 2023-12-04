package backend;

import java.io.IOException;
import java.util.ArrayList;

import backend.dom.Task;
import backend.*;
import backend.dom.TaskFunctions;
import backend.reporter.CreateReport;
import dom2app.SimpleTableModel;

public class MainController implements IMainController{
    
	TaskFunctions tasks = new TaskFunctions();
	private String fileName;
    private ArrayList<Task> tasksloader = new ArrayList<Task>();
    private ArrayList<Task> tasksPrefix = new ArrayList<Task>();
    private ArrayList<Task> tasksId = new ArrayList<Task>();
    private ArrayList<Task> toplevel = new ArrayList<Task>();
    
    public SimpleTableModel load(String fileName, String delimiter){
       this.fileName = fileName;
        try {
			tasks.load(fileName, delimiter);
		} catch (IOException NullPointerException) {
			System.out.println("Wrong file input");
			
		}
        for(Task i:tasks.getTasks()) {           
            tasksloader.add(i);
        }
   
        return tasks.convertTasks(fileName,tasks.getTasks());
    }

    public SimpleTableModel getTasksByPrefix(String prefix) {
    	

    	for(int i = 0; i < tasks.getTasksByPrefix(prefix).size(); i++) {
    		 tasksPrefix.add(tasks.getTasksByPrefix(prefix).get(i));
    	}

        return tasks.convertTasks(fileName,tasks.getTasksByPrefix(prefix));
   
    }

    public SimpleTableModel getTaskById(int id) {
    	for(int i = 0; i < tasks.getTaskById(100).size(); i++) {
    		tasksId.add(tasks.getTaskById(100).get(i));
   	}
        
        return tasks.convertTasks(fileName,tasks.getTaskById(id));
    }

    public SimpleTableModel getTopLevelTasks() {
    	for(int i = 0; i < tasks.getTopLevelTasks().size(); i++) {
    		toplevel.add(tasks.getTopLevelTasks().get(i));
      	}
       
        return tasks.convertTasks(fileName,tasks.getTopLevelTasks());
    }


    public int createReport(String path, ReportType type) {
        CreateReport out = new CreateReport();
        
        if(type == ReportType.TEXT){
            out.textConvert(path,tasksloader);
        }
       
        if(type == ReportType.MD){
            out.MdConvert(path,tasksloader);
        }
        
        if(type == ReportType.HTML){
            out.htmlConvert(path,tasksloader);
        }
        return 0;
    }

    public ArrayList<Task> getLoadedTasks(){
        return tasksloader;
    }
    public ArrayList<Task> gettasksPrefix(){
        return tasksPrefix;
    }
    public ArrayList<Task> gettasksId(){
        return tasksId;
    }
    public ArrayList<Task> gettoplevel(){
        return toplevel;
    }
    
}
