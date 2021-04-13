package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Corso corso;
	Studente studente ; 
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button cercaIscritti;

    @FXML
    private TextField matricola;

    @FXML
    private Button ok;

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button reset;

    @FXML
    void doCerca(ActionEvent event) {

    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	List<Corso> elenco = model.elenoCorsi(studente);
    	
    	for(Corso c : elenco) {
    		txtArea.appendText(c.toString() + "\n");
    	}
    	
    	
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {

    	List<Studente> elenco = new LinkedList<Studente>();
    	
    	try {
    		
    		if(corso.getCodins().equals("")) {
    			elenco = model.cercaTuttiIscritti();
    			for(Studente s : elenco) {
    				txtArea.appendText(s.toString()+"\n");
    			}
    			
    		}else {
    			elenco = model.cercaIscrittiCorso(corso);
    			for(Studente s : elenco) {
    				txtArea.appendText(s.toString()+"\n");
    			}
    			
    		}
    		
    		
    	}catch(NullPointerException e) {
    		txtArea.setText("ERRORE nella ricerca del corso");
    	}
    	
    	
    	
    	
    }

    @FXML
    void doOK(ActionEvent event) {

    	int codice;
    	
    	try {
    		String s = matricola.getText();
    		codice = Integer.parseInt(s);
    		
    	}catch(NumberFormatException e) {
    		txtArea.setText("ERRORE! Inserire una matricola valida");
    		return;
    	}
    	
    	studente = this.model.getStudente(codice);
    	nome.setText(studente.getNome());
    	cognome.setText(studente.getCognome());
    	
    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void handleCombo(ActionEvent event) {
    	
    	corso = comboBox.getValue();

    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cercaIscritti != null : "fx:id=\"cercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert matricola != null : "fx:id=\"matricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ok != null : "fx:id=\"ok\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cognome != null : "fx:id=\"cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert reset != null : "fx:id=\"reset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	comboBox.getItems().add(new Corso("", null, null, null));
    	comboBox.getItems().addAll(this.model.getTuttiCorsi());
    	
    }
}
