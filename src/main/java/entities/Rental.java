package entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the Rental database table.
 * 
 */
@Entity
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int RentalID;
	
	@Temporal(TemporalType.DATE)
	private Date rentalTimestamp;

	@Temporal(TemporalType.DATE)
	private Date returnTimestamp;
	
	private int EboardID;
	
	private int RentorID;
	
	private int GameID;
	

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="EboardID")
	private Eboard eboard;

	//bi-directional many-to-one association to Game
	@ManyToOne
	@JoinColumn(name="GameID")
	private Game game;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="RentorID")
	private Person person;

	public Rental() {
	}

	public Rental(Date rentalTimestamp, Date returnTimestamp, Eboard eboard,
			Game game, Person person) {
		super();
		this.rentalTimestamp = rentalTimestamp;
		this.returnTimestamp = returnTimestamp;
		this.eboard = eboard;
		this.game = game;
		this.person = person;
	}
	
	public int getRentalID() {
		return this.RentalID;
	}

	public void setRentalID(int rentalID) {
		this.RentalID = rentalID;
	}
	
	
	public Date getRentalTimestamp() {
		return this.rentalTimestamp;
	}

	public void setRentalTimestamp(Date rentalTimestamp) {
		this.rentalTimestamp = rentalTimestamp;
	}

	public Date getReturnTimestamp() {
		return this.returnTimestamp;
	}

	public void setReturnTimestamp(Date returnTimestamp) {
		this.returnTimestamp = returnTimestamp;
	}

	public Eboard getEboard() {
		return this.eboard;
	}

	public void setEboard(Eboard eboard) {
		this.eboard = eboard;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getEboardID() {
		return EboardID;
	}

	public void setEboardID(int eboardID) {
		EboardID = eboardID;
	}

	public int getGameID() {
		return GameID;
	}

	public void setGameID(int gameID) {
		GameID = gameID;
	}

	public int getRentorID() {
		return RentorID;
	}

	public void setRentorID(int rentorID) {
		RentorID = rentorID;
	}

}