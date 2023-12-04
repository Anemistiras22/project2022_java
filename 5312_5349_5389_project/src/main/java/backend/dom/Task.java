package backend.dom;

import java.util.ArrayList;

public class Task {
    
    private int Id;
    private Task mother;
    private String taskText;
    private int mamaId;
    private int startTime;
    private int endTime;
    private int cost;
    private ArrayList<Task> subTasks = new ArrayList<Task>();
    private boolean ifmum;

    //setters, getters, subtasks, IsTopLevel, print for testing

    public int getId(){
        return Id;
    }

    public void setId(int newId){
        this.Id = newId;
    }

    public String getTaskText(){
        return taskText;
    }

    public void setTaskText(String text){
        this.taskText = text;
    }

    public Task getMama(){
        return mother;
    }

    public boolean getIfmum(){
        return ifmum;
    }

    public void setMama(Task taskmother){
        this.mother = taskmother;
    }

    public ArrayList<Task> getSubTasks(){
        return subTasks;
    }

    public void setSubTasks(Task task){
        subTasks.add(task);
    }

    public int getStart(){
        return startTime;
    }

    public int getEnd(){
        return endTime;
    }

    public void setStart(int startTime){
        this.startTime = startTime;
    }

    public void setEnd(int endTime){
        this.endTime = endTime;
    }

    public void isTopLevel(){
        ifmum = true;
    }

    public int getMamaId() {
        return mamaId;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

	public void setMamaId(int mamaId) {
		this.mamaId = mamaId;
	}

	public void printTask() {
		System.out.println(Id+" " + taskText + " "+ mamaId + " "+startTime +" "+ endTime + " "+cost );
	}
    
    public String getTask() {
        String s = Id+"\t" + taskText + "\t"+ mamaId + "\t"+startTime +"\t"+ endTime + "\t"+cost;
        return s;  
    }
}   
