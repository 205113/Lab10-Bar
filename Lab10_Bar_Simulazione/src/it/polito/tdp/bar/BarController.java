package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.barModel.SimulatoreBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	private SimulatoreBar simulatore;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button simula;

    @FXML
    private TextArea risultato;

    @FXML
    void Simula(ActionEvent event) {
    	simulatore.generaEventi();
    	simulatore.simula();
    	String s= "Clienti soddisfatti: "+simulatore.getSoddisfatti()+"\n"+"Clienti insoddisfatti: "+simulatore.getInsoddisfatti();
    	risultato.setText(s);
    }

    
    public void setSimulatore(SimulatoreBar simulatore) {
		this.simulatore = simulatore;
	}


	@FXML
    void initialize() {
        assert simula != null : "fx:id=\"simula\" was not injected: check your FXML file 'Bar.fxml'.";
        assert risultato != null : "fx:id=\"risultato\" was not injected: check your FXML file 'Bar.fxml'.";

    }
}
