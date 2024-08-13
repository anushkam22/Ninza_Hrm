package POJO;

public class Projectt {

	String createdBy;

	String projectName;

	String status;

	int teamSize;
	
	

	public Projectt() {
			}

	public Projectt(String createdBy, String projectName, String status, int teamSize) {
		super();
		this.createdBy = createdBy;
		this.projectName = projectName;
		this.status = status;
		this.teamSize = teamSize;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public int getTeamSize() {
		return teamSize;
	}

}