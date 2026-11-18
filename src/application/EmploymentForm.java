package application;

import java.time.LocalDate;

public class EmploymentForm {

	private int id;
	private String fullName;
	private String address;
	private String contactNumber;
	private String email;
	private String education;
	private String gender;
	private LocalDate dateAvailable;
	private String desiredPosition;
	private String desiredSalary;
	private boolean authorizedToWork;
	private boolean hasRelatives;
	private String relativesExplanation;
	private String signature;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(LocalDate dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public String getDesiredPosition() {
		return desiredPosition;
	}

	public void setDesiredPosition(String desiredPosition) {
		this.desiredPosition = desiredPosition;
	}

	public String getDesiredSalary() {
		return desiredSalary;
	}

	public void setDesiredSalary(String desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public boolean isAuthorizedToWork() {
		return authorizedToWork;
	}

	public void setAuthorizedToWork(boolean authorizedToWork) {
		this.authorizedToWork = authorizedToWork;
	}

	public boolean isHasRelatives() {
		return hasRelatives;
	}

	public void setHasRelatives(boolean hasRelatives) {
		this.hasRelatives = hasRelatives;
	}

	public String getRelativesExplanation() {
		return relativesExplanation;
	}

	public void setRelativesExplanation(String relativesExplanation) {
		this.relativesExplanation = relativesExplanation;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}