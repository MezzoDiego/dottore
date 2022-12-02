package it.prova.dottore.dto;

import javax.validation.constraints.NotBlank;

import it.prova.dottore.model.Dottore;

public class DottorePazienteDTO {
	@NotBlank(message = "{codicedottore.notblank}")
	private String codiceDottore;
	@NotBlank(message = "{codicefiscale.notblank}")
	private String codiceFiscalePazienteAttualmenteInVisita;

	public DottorePazienteDTO() {
		super();
	}

	public DottorePazienteDTO(String codiceDottore, String codiceFiscalePazienteAttualmenteInVisita) {
		super();
		this.codiceDottore = codiceDottore;
		this.codiceFiscalePazienteAttualmenteInVisita = codiceFiscalePazienteAttualmenteInVisita;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}

	public String getCodiceFiscalePazienteAttualmenteInVisita() {
		return codiceFiscalePazienteAttualmenteInVisita;
	}

	public void setCodiceFiscalePazienteAttualmenteInVisita(String codiceFiscalePazienteAttualmenteInVisita) {
		this.codiceFiscalePazienteAttualmenteInVisita = codiceFiscalePazienteAttualmenteInVisita;
	}

	public Dottore buildDottoreModel() {
		return new Dottore(this.codiceDottore, this.codiceFiscalePazienteAttualmenteInVisita);
	}

}
