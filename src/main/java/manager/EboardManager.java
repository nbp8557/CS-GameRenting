package manager;

import java.util.List;

import dao.ConsoleDAO;
import dao.EboardDAO;
import dao.SessionFactory;
import entities.Console;
import entities.Eboard;

public class EboardManager {
	private EboardDAO eboardDAO = new EboardDAO(SessionFactory.getSessionFactory().getSession());

	public Eboard createEboard(String name, String position){
		Eboard e = new Eboard();
		e.setName(name);
		e.setPosition(position);
		
		e = eboardDAO.insert(e);
		
		return e;
	}
	
	public void updateEboard(Integer eboardID, String name, String position){
		Eboard e = eboardDAO.select(eboardID);
		e.setName(name);
		e.setPosition(position);
		
		eboardDAO.update(e);
	}	
	
	public void updateEboard(Eboard eboard){
		eboardDAO.update(eboard);
	}
	
	public Eboard selectEboard(Integer eboardID){
		return eboardDAO.select(eboardID);
	}
	
	public void deleteEboard(Integer eboardID){
		Eboard eboard = eboardDAO.select(eboardID);
		
		eboardDAO.delete(eboard);
	}
	
	public List<Eboard> listEboard(){
		List<Eboard> eboard = eboardDAO.selectAll();
	
		return eboard;
	}
}
