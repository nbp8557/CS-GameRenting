package manager;

import java.sql.Date;
import java.util.List;

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
	
	public Rental updateRental(Rental rental){
		return rentalDAO.update(rental);
	}
	
	public Rental selectRental(int rentalID){
		return rentalDAO.select(rentalID);
	}
	
	public void deleteRental(int rentalID){
		Rental r = rentalDAO.select(rentalID);
		
		rentalDAO.delete(r);
	}
	
	public List<Rental> listRentals(){
		List<Rental> rentals = rentalDAO.selectAll();
		
		return rentals;
	}
	
}
