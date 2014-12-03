package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Person;

public class PersonDAO extends CRUDManager<Person, Integer> {

	@Override
	public Person insert(Person p) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Transaction t = s.beginTransaction();
		s.save(p);
		t.commit();
		s.flush();
		Person resultPerson = (Person) s.get(Person.class, p.getRITUsername());
		s.close();
		return resultPerson;
	}

	@Override
	public Person select(Integer pk) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Person u = (Person) s.get(Person.class, pk);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> selectAll() {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		List<Person> u = new ArrayList<Person>();
		u = s.createCriteria(Person.class).list();
		s.close();
		return u;
	}

	@Override
	public Person delete(Person person) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Transaction transaction = s.beginTransaction();
		s.delete(person);
		transaction.commit();
		s.flush();
		person = (Person) s.get(Person.class, person.getRITUsername());
		s.close();
		return person;
	}

	@Override
	public Person update(Person person) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		
		Session s = factory.getSession();
		Transaction transaction = s.beginTransaction();
		s.update(person);
		transaction.commit();
		s.flush();
		Person resultPerson = (Person) s.get(Person.class, person.getRITUsername());
		s.close();
		return resultPerson;
	}
	
}
