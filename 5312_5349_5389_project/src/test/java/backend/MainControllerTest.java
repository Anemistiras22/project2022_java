package backend;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import backend.dom.Task;
import backend.dom.TaskFunctions;
import dom2app.SimpleTableModel;

public class MainControllerTest {
	//if MainController works everything else works
	
	public boolean arraysequal(ArrayList<String[]> array,ArrayList<Task> array2) {
		//checking if arraylists with tasks are the same
		int task1;
		String text;	
		int k = 0;
		boolean p;		
		for(int i = 0; i < array.size()-1; i++) {
			if(!array.get(i)[0].equals(String.valueOf(task1 = array2.get(i).getId()))){
				break;
			}
			if(!array.get(i)[1].equals(text = array2.get(i).getTaskText())){
				break;
			}
			if(!array.get(i)[2].equals(String.valueOf(task1 = array2.get(i).getMamaId()))){
				break;
			}if(!array.get(i)[3].equals(String.valueOf(task1 = array2.get(i).getStart()))) {
				break;
			}
			if(!array.get(i)[4].equals(String.valueOf(task1 = array2.get(i).getEnd()))) {
				break;
			}
			if(!array.get(i)[5].equals(String.valueOf(task1 = array2.get(i).getCost()))) {
				break;
			}	
			k++;
		}
		
		if(k == array.size()-1) {
			p = true;
			return p;
		}
		else {
			p = false;
			return p;
		}
		
	}
	
	

	@Test
	public void loadtestHappyday() {
		//happydayscenario
	MainController test = new MainController();			
		String[] columnNames = {"TaskId", "TaskText", "MamaId" , "Start","End","Cost"};
		    ArrayList<String[]> rows = new ArrayList<String[]>();
			String[] row = {"100", "Prepare Fry", "0", "1", "12", "60"};
			rows.add(row);
			String[] row1 = {"101", "Turn on burner (low)", "100", "1", "1", "10"};
			rows.add(row1);
			String[] row2 = {"102", "Break eggs and pour into fry", "100", "2", "4", "10"};
			rows.add(row2);
			String[] row3 = {"103", "Steer mixture to avoid sticking", "100", "5", "10", "10"};
			rows.add(row3);
			String[] row4 = {"105", "Salt, pepper", "100", "5", "5", "10"};
		    rows.add(row4);
			String[] row5 = {"104", "Throw yellow cheese into fry", "100", "6", "12", "10"};
			rows.add(row5);
			String[] row6 = {"106", "Turn burner off", "100", "12", "12", "10"};
			rows.add(row6);
			String[] row7 = {"200", "Prepare the bread", "0", "10", "12", "20"};
			rows.add(row7);
		    String[] row8 = {"201", "Heat bread in toaster", "200", "10", "12", "10"};
		    rows.add(row8);
			String[] row9 = {"202", "Little bit of salt, galric spice to bread", "200", "12", "12", "10"};
			rows.add(row9);
			String[] row10 = {"300", "Serve eggs","0", "13", "20", "30"};
			rows.add(row10);
			String[] row11 = {"301", "Put bread in plate", "300", "13", "13", "10"};
			rows.add(row11);
			String[] row12 = {"302", "Put eggs on bread", "300", "14", "14", "10"};
			rows.add(row12);
			String[] row13 = {"303", "Wash fry", "300", "15", "20", "10"};
			rows.add(row13);		    
		//put your file location
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		//testing with the Eggs.tsv file
		ArrayList<Task> tasks = test.getLoadedTasks();
		
		//if p == true everything works fine
		assertEquals(true,arraysequal(rows,tasks));
	}
	
	
	@Test(expected = NullPointerException.class)
	public void loadtestRainyDay() {
		//loadtestrainyday wrong file input
		MainController test = new MainController();
		test.load("lmao","\t");
	}
	
	@Test
	public void getTasksByPrefixtestHappyDay() {
		//happydayforgtbp		
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.getTasksByPrefix("Pr");
		ArrayList<Task> tasks = test.gettasksPrefix();		
		String[] columnNames = {"TaskId", "TaskText", "MamaId" , "Start","End","Cost"};
		ArrayList<String[]> rows = new ArrayList<String[]>();
		String[] row = {"100", "Prepare Fry", "0", "1", "12", "60"};
		rows.add(row);
		String[] row7 = {"200", "Prepare the bread", "0", "10", "12", "20"};
		rows.add(row7);				
		assertEquals(true,arraysequal(rows,tasks));
		
	}
	@Test(expected = NullPointerException.class)
	public void getTasksByPrefixtestRainyDay() {		
		//getTasksByPrefixtestRainyDay doesnt work without load first
		MainController test = new MainController();		
		test.getTasksByPrefix("lmao");
	}
	@Test
	public void  getTaskByIdHappyDay() {
		//happydayforgtbid		
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.getTaskById(100);
		ArrayList<Task> tasks = test.gettasksId();		
		String[] columnNames = {"TaskId", "TaskText", "MamaId" , "Start","End","Cost"};
		ArrayList<String[]> rows = new ArrayList<String[]>();
		String[] row = {"100", "Prepare Fry", "0", "1", "12", "60"};
		rows.add(row);						
		assertEquals(true,arraysequal(rows,tasks));
	

}
	@Test(expected = NullPointerException.class)
	public void getTasksByIdRainyDay() {		
		//getTasksByIdRainyDay doesnt work without load first
		MainController test = new MainController();		
		test.getTaskById(100);
	}
	
	@Test
	public void getTopLevelTasksHappyDay() {		
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.getTopLevelTasks();
		ArrayList<Task> tasks = test.gettoplevel();		
		String[] columnNames = {"TaskId", "TaskText", "MamaId" , "Start","End","Cost"};
		ArrayList<String[]> rows = new ArrayList<String[]>();
		String[] row = {"100", "Prepare Fry", "0", "1", "12", "60"};
		rows.add(row);
		String[] row7 = {"200", "Prepare the bread", "0", "10", "12", "20"};
		rows.add(row7);	
		String[] row10 = {"300", "Serve eggs","0", "13", "20", "30"};
		rows.add(row10);
		
		assertEquals(true,arraysequal(rows,tasks));
	
}
	
	@Test(expected = NullPointerException.class)
	public void getTopLevelTasksRainyDay() {		
		//getTopLevelTasksRainyDay doesnt work without load first
		MainController test = new MainController();		
		test.getTopLevelTasks();
	}
	
	@Test
	public void CreatereportTXThappyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.createReport("./src/test/resources/output/Eggstest.txt", ReportType.TEXT);
		//check test folder

	}
	@Test
	public void CreatereportHTMLhappyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.createReport("./src/test/resources/output/Eggstest2.html", ReportType.HTML);
		//check test folder

	}
	@Test
	public void CreatereportMDhappyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");
		test.createReport("./src/test/resources/output/Eggstest3.md", ReportType.MD);
		//check test folder

	}
	@Test
	public void CreatereportTXTRainyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");		
		assertEquals(0,test.createReport("./src/test/resources/output/Eggstest.vagelis5", ReportType.TEXT));		

	}
	@Test
	public void CreatereportMDRainyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");		
		assertEquals(0,test.createReport("./src/test/resources/output/Eggstest.vagelis6", ReportType.MD));		

	}
	@Test
	public void CreatereportHTMLRainyDay() {
		MainController test = new MainController();	
		test.load("./src/main/resources/input/Eggs.tsv","\t");		
		assertEquals(0,test.createReport("./src/test/resources/output/Eggstest.vagelis6", ReportType.HTML));		

	}
	
	
	
}
