package Studentu_registracijas_sistema;

import java.time.LocalDateTime;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String persondId;
    private LocalDateTime registrationDateTime;

    public Student(String firstName, String lastName, String email, String personId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.persondId = personId;
        this.registrationDateTime = LocalDateTime.now();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPersonId() {
        return this.persondId;
    }

    public LocalDateTime getRegistrationDateTime() {
        return this.registrationDateTime;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }
}
