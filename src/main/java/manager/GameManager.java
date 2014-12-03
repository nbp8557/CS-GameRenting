package manager;

import java.util.List;

import dao.GameDAO;
import dao.SessionFactory;
import entities.Console;
import entities.Game;
import entities.Person;

public class GameManager {

	private GameDAO gameDAO = new GameDAO(SessionFactory.getSessionFactory()
			.getSession());

	public Game createGame(String gameName, boolean rentable, Integer consoleID) {
		
		ConsoleManager cm = new ConsoleManager();
		
		Console c = cm.selectConsole(consoleID);
		
		Game g = new Game(gameName, rentable, c);

		g = gameDAO.insert(g);

		return g;
	}

	public Game updateGame(Integer gameID, String gameName, boolean rentable, Integer consoleID) {
		
		Game g = gameDAO.select(gameID);
		g.setName(gameName);
		g.setRentable(rentable);
		Console c = new ConsoleManager().selectConsole(consoleID);
		g.setConsole(c);
		
		return gameDAO.update(g);	
	}
	
	public Game updateGame(Game game){
		return gameDAO.update(game);
	}
	
	
	public void deleteGame(int gameID)
	{
		Game game = gameDAO.select(gameID);
		
		gameDAO.delete(game);
	}
	
	public Game selectGame(Integer gameID){
		return gameDAO.select(gameID);				
	}
	
	public List<Game> listGames(){
		List<Game> games = gameDAO.selectAll();
		
		return games;
	}
	
	

}
