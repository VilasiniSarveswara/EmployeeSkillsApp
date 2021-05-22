package com.perficient.perficientapiserver.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Employee {
    private UUID employee_id;
    private String firstName;
    private String lastName;
    private Address address;
    private String contactEmail;
    private String companyEmail;
    private LocalDate birthDate;
    private LocalDate hiredDate;
    private String role;
    private String businessUnit;
    private UUID assignedTo;

    public Employee(){

    }

    public Employee(UUID employee_id, String firstName, String lastName, Address address, String contactEmail, String companyEmail, LocalDate birthDate, LocalDate hiredDate, String role, String businessUnit, UUID assignedTo) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactEmail = contactEmail;
        this.companyEmail = companyEmail;
        this.birthDate = birthDate;
        this.hiredDate = hiredDate;
        this.role = role;
        this.businessUnit = businessUnit;
        this.assignedTo = assignedTo;
    }

    public UUID getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(UUID employee_id) {
        this.employee_id = employee_id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public UUID getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UUID assignedTo) {
        this.assignedTo = assignedTo;
    }
}
