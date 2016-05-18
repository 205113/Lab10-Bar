package it.polito.tdp.barModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SimulatoreBar {
	protected Queue<EventoBar> eventi;
	protected List<Tavolino>tavolini;
	protected Map<EventoBar,Tavolino> occupati;
	private int soddisfatti;
	private int insoddisfatti;
	
	
	public SimulatoreBar() {
		soddisfatti=0;
		insoddisfatti=0;
		occupati= new HashMap<EventoBar,Tavolino>();
		eventi= new PriorityQueue<EventoBar>();
		tavolini= new ArrayList<Tavolino>();
		//creo bar vero e proprio
		tavolini.add(new Tavolino(10,Tavolino.disponibilita.LIBERO));
		tavolini.add(new Tavolino(10,Tavolino.disponibilita.LIBERO));
		for(int i=0;i<4;i++)
			tavolini.add(new Tavolino(8,Tavolino.disponibilita.LIBERO));
		for(int i=0;i<4;i++)
			tavolini.add(new Tavolino(6,Tavolino.disponibilita.LIBERO));
		for(int i=0;i<5;i++)
			tavolini.add(new Tavolino(4,Tavolino.disponibilita.LIBERO));
	}

	public void generaEventi(){
	//genero eventi del bar
	int tempoEventi=0;	
	for(int i=0;i<2000;i++){
		float tolleranza=(float) Math.random();
		int tempo=(int)(Math.random()*10);
		tempoEventi+=tempo;
		int durata=60+(int)(Math.random()*60);
		int numero=(int)(Math.random()*10)+1;
		EventoBar e=new EventoBar(tempoEventi,numero,durata,tolleranza,EventoBar.tipoEvento.CLIENTI_ARRIVANO);
		this.aggiungiEvento(e);
		System.out.println(e.toString());
	}
	}
	
	public void passo(){
		//tratto il singolo evento
		EventoBar e=eventi.remove();
		switch(e.getEvento()){
		
		case CLIENTI_ARRIVANO:
		{
			if(this.soddisfaAlTavolo(e))
			{	EventoBar ev=new EventoBar(e.getDurata()+e.getTempo(),e.getNumero(),0,e.getTolleranza(),EventoBar.tipoEvento.CLIENTI_ESCONO_SODDISFATTI);
				this.aggiungiEvento(ev);
			}
			else if(this.soddisfaAlBancone(e)){
				EventoBar ev=new EventoBar(e.getDurata()+e.getTempo(),e.getNumero(),0,e.getTolleranza(),EventoBar.tipoEvento.CLIENTI_ESCONO_SODDISFATTI);
				this.aggiungiEvento(ev);
			}
			else{
				EventoBar ev=new EventoBar(e.getTempo(),e.getNumero(),0,e.getTolleranza(),EventoBar.tipoEvento.CLIENTI_ESCONO_INSODDISFATTI);
				this.aggiungiEvento(ev);
			}
				
				
		}
			break;
		case CLIENTI_ESCONO_INSODDISFATTI:
			insoddisfatti++;
			break;
		case CLIENTI_ESCONO_SODDISFATTI:
			if(occupati.containsKey(e))
				occupati.remove(e);
			soddisfatti++;
			break;
		default:
			System.err.println("Evento inesistente!!!");
			break;
		
		}
	}
	
	private boolean soddisfaAlBancone(EventoBar e) {
		//soddisfatto se gruppo ha tolleranza>0,5,non so se corretto
	if(e.getTolleranza()>0.5)
		return true;
	else
		return false;
	}

	private boolean soddisfaAlTavolo(EventoBar e) {
		// do tavolino piu piccolo libero che possa ospitare tutto il gruppo ma che sia occupato almeno della metà
		for(Tavolino t:tavolini){
			int meta=t.getCapienza()/2;
			if(t.getCapienza()>e.getNumero()&&t.getStato()==Tavolino.disponibilita.LIBERO&&meta<=e.getNumero())
			{	
				t.setStato(Tavolino.disponibilita.OCCUPATO);
				occupati.put(e, t);
				return true;
			}
		}
		return false;
	}

	public void simula(){
		//simulo eventi generati
		while(!eventi.isEmpty())
			passo();
	}
	
	private void aggiungiEvento(EventoBar e){
		eventi.add(e);
	}

	public int getSoddisfatti() {
		return soddisfatti;
	}

	public int getInsoddisfatti() {
		return insoddisfatti;
	}
	
}
