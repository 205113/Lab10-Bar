package it.polito.tdp.barModel;

public class EventoBar implements Comparable<EventoBar> {
	public enum tipoEvento{
		CLIENTI_ARRIVANO,CLIENTI_ESCONO_SODDISFATTI,CLIENTI_ESCONO_INSODDISFATTI
	}
	protected int tempo;
	protected int numero;
	protected int durata;
	protected float tolleranza;
	protected tipoEvento evento;
	
	
	public EventoBar(int tempo, int numero, int durata, float tolleranza, tipoEvento evento) {
		this.tempo = tempo;
		this.numero = numero;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.evento = evento;
	}
	@Override
	public String toString() {
		return evento+" al tempo "+tempo+" in gruppo di "+numero+" per "+durata+" minuti con tolleranza di "+tolleranza;
	}
	@Override
	public int compareTo(EventoBar arg0) {
		return Integer.compare(this.tempo, arg0.tempo);
	}
	public int getTempo() {
		return tempo;
	}
	public int getNumero() {
		return numero;
	}
	public int getDurata() {
		return durata;
	}
	public float getTolleranza() {
		return tolleranza;
	}
	public tipoEvento getEvento() {
		return evento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + durata;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + numero;
		result = prime * result + tempo;
		result = prime * result + Float.floatToIntBits(tolleranza);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoBar other = (EventoBar) obj;
		if (durata != other.durata)
			return false;
		if (evento != other.evento)
			return false;
		if (numero != other.numero)
			return false;
		if (tempo != other.tempo)
			return false;
		if (Float.floatToIntBits(tolleranza) != Float.floatToIntBits(other.tolleranza))
			return false;
		return true;
	}
	
	
}
