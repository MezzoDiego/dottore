package it.prova.dottore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.dottore.model.Dottore;
import it.prova.dottore.repository.DottoreRepository;
import it.prova.dottore.web.api.exceptions.IdNotNullForInsertException;
import it.prova.dottore.web.api.exceptions.NotFoundException;
import it.prova.dottore.web.api.exceptions.NotRemovableException;
import it.prova.dottore.web.api.exceptions.NullException;

@Service
@Transactional(readOnly = true)
public class DottoreServiceImpl implements DottoreService {

	@Autowired
	private DottoreRepository repository;

	@Override
	public List<Dottore> listAllDottori() {
		return (List<Dottore>) repository.findAll();
	}

	@Override
	public Dottore caricaSingoloDottore(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Dottore aggiorna(Dottore dottoreInstance) {
		return repository.save(dottoreInstance);
	}

	@Override
	@Transactional
	public Dottore inserisciNuovo(Dottore dottoreInstance) {
		if (dottoreInstance.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		return repository.save(dottoreInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		if (idToRemove == null)
			throw new NullException("id -> NULL");

		Dottore dottoreReloaded = repository.findById(idToRemove).orElse(null);

		if (dottoreReloaded == null)
			throw new NotFoundException("Dottore non trovato con id: " + idToRemove);

		if (dottoreReloaded.getInVisita() != null && dottoreReloaded.getInServizio() != null
				&& !dottoreReloaded.getInVisita() && !dottoreReloaded.getInServizio()) {
			repository.deleteById(idToRemove);
		} else if (dottoreReloaded.getInServizio() == null && dottoreReloaded.getInVisita() == null) {
			repository.deleteById(idToRemove);
		} else {
			throw new NotRemovableException("Impossibile rimuovere un dottore se e'in servizio o in visita.");
		}

	}

	@Override
	public Dottore caricaPerCF(String CF) {
		return repository.findByCodiceFiscalePazienteAttualmenteInVisita(CF);
	}

}
