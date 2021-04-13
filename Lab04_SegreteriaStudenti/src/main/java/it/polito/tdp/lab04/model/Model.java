package it.polito.tdp.lab04.model;


import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {

	CorsoDAO dao = new CorsoDAO();
	
	
	public List<Corso> getTuttiCorsi(){
		return dao.getTuttiICorsi();
	}
	
	public Studente getStudente(Integer matricola) {
		return dao.getStudente(matricola);
	}

	public List<Studente> cercaIscrittiCorso(Corso corso) {
		return dao.cercaIscrittiCorso(corso);
	}

	public List<Studente> cercaTuttiIscritti() {
	
		return dao.cercaIscritti();
	}
	
	public List<Corso> elenoCorsi(Studente s){
		return dao.cercaCorsi(s);
	}
	
	

}
