package it.prova.dottore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottore.dto.DottoreDTO;
import it.prova.dottore.model.Dottore;
import it.prova.dottore.service.DottoreService;
import it.prova.dottore.web.api.exceptions.IdNotNullForInsertException;
import it.prova.dottore.web.api.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private DottoreService dottoreService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@Valid @RequestBody DottoreDTO input) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Dottore newEntry = input.buildDottoreModel();
		DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreService.inserisciNuovo(newEntry));
		return result;
	}

	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAllDottori());
	}

	@GetMapping("/codicefiscale/{cf}")
	public DottoreDTO findByCF(@PathVariable(value = "cf", required = true) String cfInput) {
		Dottore result = dottoreService.caricaPerCF(cfInput);
		
		if(result == null)
			throw new NotFoundException("Non trovato.");
		
		return DottoreDTO.buildDottoreDTOFromModel(result);
	}
	
	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(value = "id", required = true) Long id) {
		Dottore result = dottoreService.caricaSingoloDottore(id);
		
		if(result == null)
			throw new NotFoundException("Dottore non trovato con id: " + id);
		
		return DottoreDTO.buildDottoreDTOFromModel(result);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		dottoreService.rimuovi(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public DottoreDTO update(@Valid @RequestBody DottoreDTO input, @PathVariable(required = true)  Long id) {

		Dottore dottore = dottoreService.caricaSingoloDottore(id);
		if (dottore.getId() == null)
			throw new RuntimeException("Dottore not found con id: " + id);

		input.setId(id);
		Dottore dottoreAggiornato = dottoreService.aggiorna(input.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreAggiornato);
	}

}
