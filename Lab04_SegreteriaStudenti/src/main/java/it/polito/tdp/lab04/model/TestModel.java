package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		CorsoDAO c = new CorsoDAO();
		System.out.println(c.getTuttiICorsi());
		
		/*
		 * 	Write here your test model
		 */

	}

}
