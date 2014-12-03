package manager;

import java.util.List;

import dao.ConsoleDAO;
import dao.SessionFactory;
import entities.Console;

public class ConsoleManager {
	private ConsoleDAO consoleDAO = new ConsoleDAO(SessionFactory.getSessionFactory().getSession());

	public Console createConsole(String consoleName){
		Console c = new Console(consoleName);
		
		c = consoleDAO.insert(c);
		
		return c;
	}
	
	public void updateConsole(Integer consoleID, String consoleName){
		Console c = consoleDAO.select(consoleID);
		c.setConsoleName(consoleName);
		
		consoleDAO.update(c);
	}	
	
	public void updateConsole(Console console){
		consoleDAO.update(console);
	}
	
	public Console selectConsole(Integer consoleID){
		return consoleDAO.select(consoleID);
	}
	
	public void deleteConsole(Integer consoleID){
		Console console = consoleDAO.select(consoleID);
		
		consoleDAO.delete(console);
	}
	
	public List<Console> listConsoles(){
		List<Console> consoles = consoleDAO.selectAll();
		
		return consoles;		
	}
}
