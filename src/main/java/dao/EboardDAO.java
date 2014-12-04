package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Eboard;

public class EboardDAO extends CRUDManager<Eboard, Integer> {

	public Session s;
	
	public EboardDAO(Session session){
		this.s = session;
	}
	
	
	@Override
	public Eboard insert(Eboard u) {
		Transaction t = s.beginTransaction();
		s.save(u);
		t.commit();
		s.flush();
		Eboard resultEboard = (Eboard) s.get(Eboard.class, u.getEboardID());
		s.close();
		return resultEboard;
	}

	@Override
	public Eboard select(Integer EboardId) {
		Eboard u = (Eboard) s.get(Eboard.class, EboardId);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eboard> selectAll() {
		List<Eboard> u = new ArrayList<Eboard>();
		u = s.createCriteria(Eboard.class).list();
		s.close();
		return u;
	}

	@Override
	public Eboard delete(Eboard Eboard) {
		Transaction transaction = s.beginTransaction();
		s.delete(Eboard);
		transaction.commit();
		s.flush();
		Eboard = (Eboard) s.get(Eboard.class, Eboard.getEboardID());
		s.close();
		return Eboard;
	}

	@Override
	public Eboard update(Eboard Eboard) {
		Transaction transaction = s.beginTransaction();
		s.update(Eboard);
		transaction.commit();
		s.flush();
		Eboard resultEboard = (Eboard) s.get(Eboard.class, Eboard.getEboardID());
		s.close();
		return resultEboard;
	}
	

}
