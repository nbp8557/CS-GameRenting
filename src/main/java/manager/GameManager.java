package manager;

import dao.GameDAO;
import dao.SessionFactory;
import entities.Game;

public class GameManager {
	
	private GameDAO gameDAO = new GameDAO(SessionFactory.getSessionFactory().getSession());

	//TODO implement this
	public Game createGame(){
		throw new UnsupportedOperationException(); 
	}
	
	//TODO implement this
	public Game updateGame(){
		throw new UnsupportedOperationException(); 
	}
	
	
}
