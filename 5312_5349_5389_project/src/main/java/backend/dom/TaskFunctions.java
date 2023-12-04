package backend.dom;

import dom2app.SimpleTableModel;

import java.util.Collections;
import java.util.Comparator;

import backend.parser.*;

import java.io.IOException;
import java.util.ArrayList;

public class TaskFunctions {

	private FileParser fileParser;

	public TaskFunctions() {
		fileParser = new FileParser();
	}
	
	private ArrayList<Task> tasks;
	
	public SimpleTableModel convertTasks(String Name, ArrayList<Task> tasks){
		
		String[] columnNames = {"TaskId", "TaskText", "MamaId" , "Start","End","Cost"};

		// convert tasks to table data
		ArrayList<String[]> rows = new ArrayList<String[]>();

		for(Task thisTask: tasks){
			String Taskid = String.valueOf(thisTask.getId());
			String TaskText = thisTask.getTaskText();
			String mamaId = String.valueOf(thisTask.getMamaId());	            
			String start = String.valueOf(thisTask.getStart());
			String end = String.valueOf(thisTask.getEnd());
			String cost = String.valueOf(thisTask.getCost());
			String[] row = {Taskid, TaskText, mamaId, start, end, cost};
			rows.add(row);
		}
		
		return new SimpleTableModel(Name, "Gantt Diagram", columnNames, rows);
	}
	
	public ArrayList<Task> sort(ArrayList<Task> tasks) {
		ArrayList<Task> newArray = new ArrayList<Task>();

		//sort all top level tasks and then their subtasks
		for(int i = 0; i < tasks.size(); i++) {
			
			if(tasks.get(i).getIfmum()) {
				newArray.add(tasks.get(i));
				Collections.sort(tasks.get(i).getSubTasks(), Comparator.comparingLong(Task::getStart));
				ArrayList<Task> sortedTasks = tasks.get(i).getSubTasks();
				
				for (Task alltasks : sortedTasks) {
					newArray.add(alltasks);
				}	
			}        
		}
		return newArray;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	
	private void setTasks(ArrayList<Task> tasks) {				
		this.tasks = sort(tasks);
	}
	
	public ArrayList<Task> load(String fileName, String delimiter) throws IOException {
		ArrayList<Task> tasks = fileParser.parser(fileName, delimiter);
		setTasks(tasks);
		return getTasks();
	}

	public ArrayList<Task> getTasksByPrefix(String prefix){		
		ArrayList<Task> prefixTasks = new ArrayList<Task>();
		
		for(Task thistask: getTasks()){
			if(thistask.getTaskText().startsWith(prefix)){
				prefixTasks.add(thistask);
			}
		}
		return prefixTasks;
	}


	public ArrayList<Task> getTaskById(int id) {
		ArrayList<Task> idtask = new ArrayList<Task>();
		for(Task thistask: getTasks()){
			if(thistask.getId() == id){
					idtask.add(thistask);
					return idtask;
			}
		}
		return null;
	}

	public ArrayList<Task> getTopLevelTasks() {
		ArrayList<Task> topTasks = new ArrayList<Task>();
		for(Task thistask: getTasks()){
			if(thistask.getIfmum()){
				topTasks.add(thistask);
			}
		}
		return topTasks;
	}  
}
