package com.example.book.service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
 private String email;
 private String firstName;
 private String lastName;
 private String phone;
 private LocalDate birthDate;
}
