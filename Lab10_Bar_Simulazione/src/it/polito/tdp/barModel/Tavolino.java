package it.polito.tdp.barModel;

public class Tavolino {
	public enum disponibilita{
		LIBERO,OCCUPATO
	}
	private int capienza;
	private disponibilita stato;
	
	
	
	public Tavolino(int capienza, disponibilita stato) {
		this.capienza = capienza;
		this.stato = stato;
	}
	
	public disponibilita getStato() {
		return stato;
	}
	public void setStato(disponibilita stato) {
		this.stato = stato;
	}
	public int getCapienza() {
		return capienza;
	}
	
	
}
