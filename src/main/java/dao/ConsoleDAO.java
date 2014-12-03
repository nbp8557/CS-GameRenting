package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Console;

public class ConsoleDAO extends CRUDManager<Console, Integer> {

	public Session s;
	
	public ConsoleDAO(Session session){
		this.s = session;
	}
	
	
	@Override
	public Console insert(Console u) {
		Transaction t = s.beginTransaction();
		s.save(u);
		t.commit();
		s.flush();
		Console resultConsole = (Console) s.get(Console.class, u.getConsoleName());
		s.close();
		return resultConsole;
	}

	@Override
	public Console select(Integer pk) {
		Console u = (Console) s.get(Console.class, pk);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Console> selectAll() {
		List<Console> u = new ArrayList<Console>();
		u = s.createCriteria(Console.class).list();
		s.close();
		return u;
	}

	@Override
	public Console delete(Console console) {
		Transaction transaction = s.beginTransaction();
		s.delete(console);
		transaction.commit();
		s.flush();
		console = (Console) s.get(Console.class, console.getConsoleName());
		s.close();
		return console;
	}

	@Override
	public Console update(Console console) {
		Transaction transaction = s.beginTransaction();
		s.update(console);
		transaction.commit();
		s.flush();
		Console resultConsole = (Console) s.get(Console.class, console.getConsoleName());
		s.close();
		return resultConsole;
	}
	

}
