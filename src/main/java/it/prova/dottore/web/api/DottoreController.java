package it.prova.dottore.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottore.dto.DottoreDTO;
import it.prova.dottore.model.Dottore;
import it.prova.dottore.service.DottoreService;
import it.prova.dottore.web.api.exceptions.IdNotNullForInsertException;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private DottoreService dottoreService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO input) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Dottore newEntry = input.buildDottoreModel();
		DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreService.inserisciNuovo(newEntry));
		return result;
	}
}
