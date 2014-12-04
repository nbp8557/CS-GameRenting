package entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Game database table.
 * 
 */
@Entity
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gameID;

	private String name;

	private boolean rentable;
	
	private int consoleID;

	//bi-directional many-to-one association to Console
	@ManyToOne
	@JoinColumn(name="ConsoleID")
	private Console console;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="game")
	private List<Rental> rentals;

	public Game() {
	}

	public Game(String gameName, boolean rentable, Console console) {
		this.name = gameName;
		this.rentable = rentable;
		this.console = console;
	}

	public int getGameID() {
		return this.gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getRentable() {
		return this.rentable;
	}

	public void setRentable(boolean rentable) {
		this.rentable = rentable;
	}

	public Console getConsole() {
		return this.console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setGame(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setGame(null);

		return rental;
	}

	public int getConsoleID() {
		return consoleID;
	}

	public void setConsoleID(int consoleID) {
		this.consoleID = consoleID;
	}

}