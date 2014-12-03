package manager;

import java.sql.Date;

import dao.PersonDAO;
import dao.RentalDAO;
import dao.SessionFactory;
import entities.Eboard;
import entities.Game;
import entities.Person;
import entities.Rental;

public class RentalManager {
	private RentalDAO rentalDAO = new RentalDAO(SessionFactory.getSessionFactory()
			.getSession());

	public Rental createRental(Date RentalTimestamp, Game game, Eboard eboard, Person person){
		Rental r = new Rental();
		r.setEboard(eboard);
		r.setRentalTimestamp(RentalTimestamp);
		r.setPerson(person);
		r.setGame(game);
		
		r = rentalDAO.insert(r);
		
		return r;
	}
	
	public Rental updatePerson(Rental rental){
		return rentalDAO.update(rental);
	}
	
	public Rental selectConsole(int RentalID){
		return rentalDAO.select(RentalID);
	}
	
}
