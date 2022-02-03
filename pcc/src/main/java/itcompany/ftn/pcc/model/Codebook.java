package itcompany.ftn.pcc.model;

import javax.persistence.*;


@Entity
@Table(name = "codebook")
public class Codebook {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "identification_number", nullable = false, unique = true)
	private String identificationNumber;
	
	@Column(nullable = false)
	private String url;
	
	public Codebook() {
		super();
	}

	public Codebook(Long id, String identificationNumber, String url) {
		super();
		this.id = id;
		this.identificationNumber = identificationNumber;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
