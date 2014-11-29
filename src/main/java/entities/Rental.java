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

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="EboardID")
	private Person person1;

	//bi-directional many-to-one association to Game
	@ManyToOne
	@JoinColumn(name="GameID")
	private Game game;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="RentorID")
	private Person person2;

	public Rental() {
	}

	public int getRentalID() {
		return this.RentalID;
	}

	public void setGameID(int rentalID) {
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

	public Person getPerson1() {
		return this.person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Person getPerson2() {
		return this.person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

}