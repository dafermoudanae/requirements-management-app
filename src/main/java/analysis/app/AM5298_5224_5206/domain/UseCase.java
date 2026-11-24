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
@Table(name = "use_cases")
public class UseCase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String preconditions;
	
	@Column(columnDefinition = "TEXT")
	private String mainFlow;
	
	@Column(columnDefinition = "TEXT")
	private String postconditions;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinTable(
            name = "use_case_actors",
            joinColumns = @JoinColumn(
                    name = "use_case_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "actor_id", referencedColumnName = "id"
            )
    )
	private List<Actor> actors;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(String preconditions) {
		this.preconditions = preconditions;
	}

	public String getMainFlow() {
		return mainFlow;
	}

	public void setMainFlow(String mainFlow) {
		this.mainFlow = mainFlow;
	}

	public String getPostconditions() {
		return postconditions;
	}

	public void setPostconditions(String postconditions) {
		this.postconditions = postconditions;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "UseCase [id=" + id + ", title=" + title + ", preconditions=" + preconditions + ", mainFlow=" + mainFlow
				+ ", postconditions=" + postconditions + ", project=" + project + ", actors=" + actors + "]";
	}
}
