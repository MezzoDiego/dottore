package it.prova.dottore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dottore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	private String codiceFiscalePazienteAttualmenteInVisita;
	private Boolean InServizio;
	private Boolean inVisita;

	public Dottore() {
		super();
	}

	public Dottore(Long id, String nome, String cognome, String codiceDottore,
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

}
