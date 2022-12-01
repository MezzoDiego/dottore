package it.prova.dottore.service;

import java.util.List;

import it.prova.dottore.model.Dottore;

public interface DottoreService {
	public List<Dottore> listAllDottori();

	public Dottore caricaSingoloDottore(Long id);

	public Dottore aggiorna(Dottore dottoreInstance);

	public Dottore inserisciNuovo(Dottore dottoreInstance);

	public void rimuovi(Long idToRemove);
	
	public Dottore caricaPerCF(String CF);
}
