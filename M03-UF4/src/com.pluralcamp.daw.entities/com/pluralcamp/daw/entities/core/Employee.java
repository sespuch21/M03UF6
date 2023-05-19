package com.pluralcamp.daw.entities.core;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Employee implements Comparable<Employee> {

    public enum Gender {
        MALE, FEMALE;
    }

    private long id = -1;
    private String code;
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private double monthlySalary;
    private int payments;

    public static final int DEFAULT_PAYMENTS = 14;
    public static final double DEFAULT_SALARY = 1_800;
    private static int counter = 0;

    public Employee(String code, String firstname, String lastname, Gender gender, LocalDate birthDate, LocalDate hireDate, double monthlySalary, int payments) {
        super();
        counter++;
        this.setCode(code);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setGender(gender);
        this.setBirthDate(birthDate);
        this.setHireDate(hireDate);
        this.setMonthlySalary(monthlySalary);
        this.setPayments(payments);
    }

    public Employee(String code, String firstname, String lastname, Gender gender, LocalDate birthDate, LocalDate hireDate, double monthlySalary) {
        this(code, firstname, lastname, gender,  birthDate, hireDate, monthlySalary, DEFAULT_PAYMENTS);
    }

    public Employee(String code, String firstname, String lastname, Gender gender, LocalDate birthDate, double monthlySalary, int payments) {
        this(code, firstname, lastname, gender, birthDate, LocalDate.now(), monthlySalary, payments);
    }

    public Employee(String code, String firstname, String lastname, Gender gender, LocalDate birthDate, double monthlySalary) {
        this(code, firstname, lastname, gender, birthDate, LocalDate.now(), monthlySalary, DEFAULT_PAYMENTS);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (this.getId() != -1) {
            throw new UnsupportedOperationException("No se puede cambiar el id de un objeto ya asignado");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("El id a asignar a una entidad debe ser un valor positivo");
        }

        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getFullName() {
        return String.format("%s %s", this.getFirstname(), this.getLastname());
    }

    public String getReverseName() {
        return String.format("%s, %s", this.getLastname(), this.getFirstname());
    }

    public int getAge() {
        Period period = Period.between(this.getBirthDate(), LocalDate.now());
        return period.getYears();
    }

    public long getSeniority() {
        return ChronoUnit.DAYS.between(this.getHireDate(), LocalDate.now());
    }

    public double getAnnualSalary() {
        return this.getMonthlySalary() * this.getPayments();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------------------\n");
        sb.append("   E M P L O Y E E                                  \n");
        sb.append("----------------------------------------------------\n");
        sb.append(String.format(">Code:        %s %n", this.getCode()));
        sb.append(String.format(">Firstname:   %s %n", this.getFirstname()));
        sb.append(String.format(">Lastname:    %s %n", this.getLastname()));
        sb.append(String.format(">Gender:      %s %n", this.getGender()));
        sb.append(String.format(">FullName:    %s %n", this.getFullName()));
        sb.append(String.format(">ReverseName: %s %n", this.getReverseName()));
        sb.append(String.format(">Age:         %d años %n", this.getAge()));
        sb.append(String.format(">Seniority:   %d días %n", this.getSeniority()));
        sb.append(String.format(">AnnualSalary:%.2f %n", this.getAnnualSalary()));
        return sb.toString();
    }

    @Override
    public int compareTo(Employee o) {
        return this.getLastname().compareToIgnoreCase(o.getLastname());
    }

    public static int getCounter() {
        return counter;
    }
}
