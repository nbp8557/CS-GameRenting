package manager;

import dao.ConsoleDAO;
import dao.SessionFactory;
import entities.Console;

public class ConsoleManager {
	private ConsoleDAO consoleDAO = new ConsoleDAO(SessionFactory.getSessionFactory().getSession());

	//TODO implement this
	public Console createConsole(){
		throw new UnsupportedOperationException(); 
	}
	
	//TODO implement this
	public void updateConsole(Integer consoleID, String consoleName){
		Console c = consoleDAO.select(consoleID);
		c.setConsoleName(consoleName);
		
		consoleDAO.update(c);
	}	
	
	public void updateConsole(Console console){
		consoleDAO.update(console);
	}
}
