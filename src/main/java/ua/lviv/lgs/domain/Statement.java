package ua.lviv.lgs.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "statement")
public class Statement implements Comparable<Statement> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "faculty_id")
	private Integer facultyId;

	@ElementCollection
	@Column(name = "statement_marks")
	private List<Double> statementMarks;

	@Transient
	private boolean isAccepted;

	public Statement() {
	}

	public Statement(Integer userId, Integer facultyId, List<Double> statementMarks) {
		super();
		this.userId = userId;
		this.facultyId = facultyId;
		this.statementMarks = statementMarks;
	}

	public Statement(Integer id, Integer userId, Integer facultyId, List<Double> statementMarks) {
		super();
		this.id = id;
		this.userId = userId;
		this.facultyId = facultyId;
		this.statementMarks = statementMarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public List<Double> getStatementMarks() {
		return statementMarks;
	}

	public void setStatementMarks(List<Double> statementMarks) {
		this.statementMarks = statementMarks;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isAccepted ? 1231 : 1237);
		result = prime * result + ((statementMarks == null) ? 0 : statementMarks.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statement other = (Statement) obj;
		if (facultyId == null) {
			if (other.facultyId != null)
				return false;
		} else if (!facultyId.equals(other.facultyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isAccepted != other.isAccepted)
			return false;
		if (statementMarks == null) {
			if (other.statementMarks != null)
				return false;
		} else if (!statementMarks.equals(other.statementMarks))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Statement [id=" + id + ", userId=" + userId + ", facultyId=" + facultyId + ", statementMarks="
				+ statementMarks + ", isAccepted=" + isAccepted + "]";
	}

	@Override
	public int compareTo(Statement statement) {
		if ((statement.statementMarks.stream().reduce((x1, x2) -> x1 + x2).get()
				- this.statementMarks.stream().reduce((x1, x2) -> x1 + x2).get()) > 0) {
			return 1;
		} else if ((statement.statementMarks.stream().reduce((x1, x2) -> x1 + x2).get()
				- this.statementMarks.stream().reduce((x1, x2) -> x1 + x2).get()) > 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
