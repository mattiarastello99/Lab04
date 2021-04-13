package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso ORDER BY nome asc";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("ERRORE nella lettura dei corsi", e);
		}
	}
	
	public Studente getStudente(Integer matricola) {
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		Studente s = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
		
			rs.next();
			Integer m = rs.getInt("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String cs = rs.getString("CDS");
			
			s = new Studente(m, n, c, cs);
			
			rs.close();
			st.close();
			conn.close();
		
			return s;
		
		}catch(SQLException e) {
			throw new RuntimeException("ERRORE nella lettura dello studente", e);
		}
	}

	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

	public List<Studente> cercaIscrittiCorso(Corso corso) {
		
		final String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE i.matricola=s.matricola AND i.codins=?";
		
		Studente s = null;
		List<Studente> elenco = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
		
			while(rs.next()){
			Integer m = rs.getInt("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String cs = rs.getString("CDS");
			
			s = new Studente(m, n, c, cs);
			elenco.add(s);
			}
			
			rs.close();
			st.close();
			conn.close();
		
			return elenco;
		}catch(SQLException e) {
			throw new RuntimeException("ERRORE nella lettura degli studenti", e);
		}
	
	}
	
	public List<Studente> cercaIscritti() {

		
		final String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE i.matricola=s.matricola";
		
		Studente s = null;
		List<Studente> elenco = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
		
			while(rs.next()){
			Integer m = rs.getInt("matricola");
			String n = rs.getString("nome");
			String c = rs.getString("cognome");
			String cs = rs.getString("CDS");
			
			s = new Studente(m, n, c, cs);
			elenco.add(s);
			}
			
			rs.close();
			st.close();
			conn.close();
		
			return elenco;
		}catch(SQLException e) {
			throw new RuntimeException("ERRORE nella lettura degli studenti", e);
		}
	
	}

	public List<Corso> cercaCorsi(Studente s){
		
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins=i.codins AND i.matricola=?";
		
		
		List<Corso> elenco = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
		
			while(rs.next()){
			String codins = rs.getString("codins");
			Integer crediti = rs.getInt("crediti");
			String nome = rs.getString("nome");
			Integer pd = rs.getInt("pd");
			
			Corso corso = new Corso(codins, crediti, nome, pd);
			elenco.add(corso);
			}
			
			rs.close();
			st.close();
			conn.close();
		
			return elenco;
		}catch(SQLException e) {
			throw new RuntimeException("ERRORE nella lettura dei corsi dello studente", e);
		}
	}
}
