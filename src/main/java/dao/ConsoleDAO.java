package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Console;

public class ConsoleDAO extends CRUDManager<Console, Integer> {

	@Override
	public Console insert(Console u) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
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
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Console u = (Console) s.get(Console.class, pk);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Console> selectAll() {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		List<Console> u = new ArrayList<Console>();
		u = s.createCriteria(Console.class).list();
		s.close();
		return u;
	}

	@Override
	public Console delete(Console console) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
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
		SessionFactory factory = SessionFactory.getSessionFactory();
		
		Session s = factory.getSession();
		Transaction transaction = s.beginTransaction();
		s.update(console);
		transaction.commit();
		s.flush();
		Console resultConsole = (Console) s.get(Console.class, console.getConsoleName());
		s.close();
		return resultConsole;
	}
	

}
