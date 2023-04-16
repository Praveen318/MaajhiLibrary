package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {
	@NotBlank(message="Name can't be blank")
	private String name;
	@Pattern(regexp="^[0-9]{10}$", message="Mobile number should be 10 digits")
	private String mobile;
}
