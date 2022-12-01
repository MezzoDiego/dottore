package it.prova.dottore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.dottore.model.Dottore;
import it.prova.dottore.repository.DottoreRepository;
import it.prova.dottore.web.api.exceptions.IdNotNullForInsertException;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Dottore inserisciNuovo(Dottore dottoreInstance) {
		if(dottoreInstance.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		return repository.save(dottoreInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub

	}

}
