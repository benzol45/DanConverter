package com.example.convertor.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class PersonResult {
    private int number;
    @NotNull
    @NotBlank
    private String surname;
    @NotNull
    @NotBlank
    private String name;
    private String fatherName;
    @NotNull
    @NotBlank
    private String SNILS;
    @NotNull
    @NotBlank
    private String position;
    @NotNull
    @NotBlank
    private String company;
    @NotNull
    @Min(1)
    private Double companyINN;
    @NotNull
    @NotBlank
    private String studyingTitle;
    @NotNull
    private Date checkDate;
    @NotNull
    @NotBlank
    private String protocolNumber;
}
