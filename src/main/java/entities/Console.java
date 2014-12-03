package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Console database table.
 * 
 */
@Entity
@NamedQuery(name="Console.findAll", query="SELECT c FROM Console c")
public class Console implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int consoleID;
	
	private String consoleName;

	//bi-directional many-to-one association to Game
	@OneToMany(mappedBy="console")
	private List<Game> games;

	public Console() {
	}
	
	public Console(String name){
		this.consoleName = name;
	}

	public String getConsoleName() {
		return this.consoleName;
	}

	public void setConsoleName(String consoleName) {
		this.consoleName = consoleName;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Game addGame(Game game) {
		getGames().add(game);
		game.setConsole(this);

		return game;
	}

	public Game removeGame(Game game) {
		getGames().remove(game);
		game.setConsole(null);

		return game;
	}

}