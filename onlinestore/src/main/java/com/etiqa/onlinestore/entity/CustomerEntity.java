package com.etiqa.onlinestore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailOffice() {
		return emailOffice;
	}

	public void setEmailOffice(String emailOffice) {
		this.emailOffice = emailOffice;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public Integer getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Integer familyMembers) {
		this.familyMembers = familyMembers;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Office email should be valid")
    @Column(name = "email_office")
    private String emailOffice;

    @Email(message = "Personal email should be valid")
    @Column(name = "email_personal")
    private String emailPersonal;

    @Column(name = "family_members")
    private Integer familyMembers;

    
	public CustomerEntity(String firstName, String lastName, String email, String secondaryEmail, int familyMembers) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.emailOffice = email;
	    this.emailPersonal = secondaryEmail;
	    this.familyMembers = familyMembers;
	}
    
}
