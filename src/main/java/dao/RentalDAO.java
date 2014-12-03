package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Rental;

public class RentalDAO extends CRUDManager<Rental, Integer> {

	public Session s;
	
	public RentalDAO(Session session){
		this.s = session;
	}
	
	
	@Override
	public Rental insert(Rental g) {
		Transaction t = s.beginTransaction();
		s.save(g);
		t.commit();
		s.flush();
		Rental resultRental = (Rental) s.get(Rental.class, g.getRentalID());
		s.close();
		return resultRental;
	}

	@Override
	public Rental select(Integer pk) {
		Rental u = (Rental) s.get(Rental.class, pk);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rental> selectAll() {
		List<Rental> u = new ArrayList<Rental>();
		u = s.createCriteria(Rental.class).list();
		s.close();
		return u;
	}

	@Override
	public Rental delete(Rental rental) {
		Transaction transaction = s.beginTransaction();
		s.delete(rental);
		transaction.commit();
		s.flush();
		rental = (Rental) s.get(Rental.class, rental.getRentalID());
		s.close();
		return rental;
	}

	@Override
	public Rental update(Rental rental) {
		Transaction transaction = s.beginTransaction();
		s.update(rental);
		transaction.commit();
		s.flush();
		Rental resultRental = (Rental) s.get(Rental.class, rental.getRentalID());
		s.close();
		return resultRental;
	}
	

}
