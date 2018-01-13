package com.shivamrajput.finance.hw.shivamrajputhw.module.user.controller.dto;


public class UserDTO {

    public Long id;
    public String firstname;

    public String lastname;

    public Long personalNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public UserDTO() {
    }

    public UserDTO(String firstname, String lastname, Long personalNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalNumber = personalNumber;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                '}';
    }
}
