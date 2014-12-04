package entities;
import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	private String RITUsername;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private String phoneNumber;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="person")
	private List<Rental> rentals;

	public Person() {
	}

	public Person(String email, String firstName, String lastName,
			String middleName, String phoneNumber) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getRITUsername() {
		return this.RITUsername;
	}

	public void setRITUsername(String RITUsername) {
		this.RITUsername = RITUsername;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRentals(Rental rentals) {
		getRentals().add(rentals);
		rentals.setPerson(this);

		return rentals;
	}

	public Rental removeRentals(Rental rentals) {
		getRentals().remove(rentals);
		rentals.setPerson(null);

		return rentals;
	}

}