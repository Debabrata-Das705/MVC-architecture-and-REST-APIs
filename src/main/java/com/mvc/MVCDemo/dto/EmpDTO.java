package com.mvc.MVCDemo.dto;

import com.mvc.MVCDemo.Advice.EmployeeRoleValidation;
import com.mvc.MVCDemo.Annotation.EmployeeRoleValidator;
import com.mvc.MVCDemo.Cool.PrimeNumberValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpDTO {
    private Long id;
   @NotBlank(message = "Name can not be blank..")
   @Size(min = 3, max = 10, message = "Size should be in b/w..")
    private String name;

   @Max(value = 65,message = "Age must under 65..")
   @Min(value = 18,message = "Age must grester than 18..")
    private int age;

   @PastOrPresent(message = "Add date past or present not future..")
    private LocalDate date;

    @NotBlank
    @Email(message = "Give a valid email")
    private String email;

    @NotBlank
  //
    //@Pattern(regexp ="^(ADMIN|USER)$", message = "User or admin")
    @EmployeeRoleValidation
    private String role;

    @Digits(integer = 6,fraction = 2,message = "xxxxxx.yy")
    // or
    @DecimalMax(value = "100.50")
    private Double salery;

    @PrimeNumberValidation
    private Integer prime;

}
