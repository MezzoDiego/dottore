package it.prova.dottore.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.dottore.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	private String codiceFiscalePazienteAttualmenteInVisita;
	private Boolean InServizio;
	private Boolean inVisita;

	public DottoreDTO() {
		super();
	}

	public DottoreDTO(Long id, String nome, String cognome, String codiceDottore,
			String codiceFiscalePazienteAttualmenteInVisita, Boolean inServizio, Boolean inVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
		this.codiceFiscalePazienteAttualmenteInVisita = codiceFiscalePazienteAttualmenteInVisita;
		InServizio = inServizio;
		this.inVisita = inVisita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public Boolean getInServizio() {
		return InServizio;
	}

	public void setInServizio(Boolean inServizio) {
		InServizio = inServizio;
	}

	public Boolean getInVisita() {
		return inVisita;
	}

	public void setInVisita(Boolean inVisita) {
		this.inVisita = inVisita;
	}

	public Dottore buildDottoreModel() {
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDottore,
				this.codiceFiscalePazienteAttualmenteInVisita, this.InServizio, this.inVisita);
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore input) {
		return new DottoreDTO(input.getId(), input.getNome(), input.getCognome(), input.getCodiceDottore(),
				input.getCodiceFiscalePazienteAttualmenteInVisita(), input.getInServizio(), input.getInVisita());
	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(inputEntity -> {
			return DottoreDTO.buildDottoreDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}

}
