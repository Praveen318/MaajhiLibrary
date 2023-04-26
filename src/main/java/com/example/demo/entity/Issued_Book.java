package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Issued_Book {
	@Id
	private Long id;
	private String name;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Boolean getStatus() {
//		return status;
//	}
//
//	public void setStatus(Boolean status) {
//		this.status = status;
//	}
//
//	public LocalDate getIssueDate() {
//		return issueDate;
//	}
//
//	public void setIssueDate(LocalDate issueDate) {
//		this.issueDate = issueDate;
//	}
//
//	public LocalDate getReturnDate() {
//		return returnDate;
//	}
//
//	public void setReturnDate(LocalDate returnDate) {
//		this.returnDate = returnDate;
//	}
//
//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}


}