package entities;
import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Eboard database table.
 * 
 */
@Entity
@NamedQuery(name="Eboard.findAll", query="SELECT e FROM Eboard e")
public class Eboard implements Serializable {
	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int EboardID;

	private String Name;

	private String Position;

	public Eboard() {
	}
	
	public Eboard(String name, String position) {
		super();
		Name = name;
		Position = position;
	}

	public int getEboardID() {
		return EboardID;
	}

	public void setEboardID(int eboardID) {
		EboardID = eboardID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	


}