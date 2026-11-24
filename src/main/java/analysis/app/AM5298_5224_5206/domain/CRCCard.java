package analysis.app.AM5298_5224_5206.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crc_cards")
public class CRCCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String className;
	
	@Column(columnDefinition = "TEXT")
	private String responsibilities;
	
	@Column(columnDefinition = "TEXT")
	private String collaborations;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinTable(
			name = "crc_card_use_cases",
			joinColumns = @JoinColumn(name = "crc_card_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "use_case_id", referencedColumnName = "id")
			)
	private List<UseCase> useCases;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public String getCollaborations() {
		return collaborations;
	}

	public void setCollaborations(String collaborations) {
		this.collaborations = collaborations;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<UseCase> getUseCases() {
		return useCases;
	}

	public void setUseCases(List<UseCase> useCases) {
		this.useCases = useCases;
	}

	@Override
	public String toString() {
		return "CRCCard [id=" + id + ", className=" + className + ", responsibilities=" + responsibilities
				+ ", collaborations=" + collaborations + ", project=" + project + ", useCases=" + useCases + "]";
	}
}
