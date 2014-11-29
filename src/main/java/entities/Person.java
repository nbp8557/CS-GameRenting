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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String RITUsername;

	private String eboardPosition;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private String phoneNumber;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="person1")
	private List<Rental> rentals1;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="person2")
	private List<Rental> rentals2;

	public Person() {
	}

	public String getRITUsername() {
		return this.RITUsername;
	}

	public void setRITUsername(String RITUsername) {
		this.RITUsername = RITUsername;
	}

	public String getEboardPosition() {
		return this.eboardPosition;
	}

	public void setEboardPosition(String eboardPosition) {
		this.eboardPosition = eboardPosition;
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

	public List<Rental> getRentals1() {
		return this.rentals1;
	}

	public void setRentals1(List<Rental> rentals1) {
		this.rentals1 = rentals1;
	}

	public Rental addRentals1(Rental rentals1) {
		getRentals1().add(rentals1);
		rentals1.setPerson1(this);

		return rentals1;
	}

	public Rental removeRentals1(Rental rentals1) {
		getRentals1().remove(rentals1);
		rentals1.setPerson1(null);

		return rentals1;
	}

	public List<Rental> getRentals2() {
		return this.rentals2;
	}

	public void setRentals2(List<Rental> rentals2) {
		this.rentals2 = rentals2;
	}

	public Rental addRentals2(Rental rentals2) {
		getRentals2().add(rentals2);
		rentals2.setPerson2(this);

		return rentals2;
	}

	public Rental removeRentals2(Rental rentals2) {
		getRentals2().remove(rentals2);
		rentals2.setPerson2(null);

		return rentals2;
	}

}