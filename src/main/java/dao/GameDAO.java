package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Game;

public class GameDAO extends CRUDManager<Game, Integer> {

	@Override
	public Game insert(Game g) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Transaction t = s.beginTransaction();
		s.save(g);
		t.commit();
		s.flush();
		Game resultGame = (Game) s.get(Game.class, g.getGameID());
		s.close();
		return resultGame;
	}

	@Override
	public Game select(Integer pk) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Game u = (Game) s.get(Game.class, pk);
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> selectAll() {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		List<Game> u = new ArrayList<Game>();
		u = s.createCriteria(Game.class).list();
		s.close();
		return u;
	}

	@Override
	public Game delete(Game game) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		Session s = factory.getSession();
		Transaction transaction = s.beginTransaction();
		s.delete(game);
		transaction.commit();
		s.flush();
		game = (Game) s.get(Game.class, game.getGameID());
		s.close();
		return game;
	}

	@Override
	public Game update(Game game) {
		SessionFactory factory = SessionFactory.getSessionFactory();
		
		Session s = factory.getSession();
		Transaction transaction = s.beginTransaction();
		s.update(game);
		transaction.commit();
		s.flush();
		Game resultGame = (Game) s.get(Game.class, game.getGameID());
		s.close();
		return resultGame;
	}
	

}
