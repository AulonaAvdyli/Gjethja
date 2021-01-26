package com.katrasolutions.gjethja.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

public class RegisterRequest {

    @NotBlank(message = "first name must not be empty")
    private String firstName;

    @NotBlank(message = "last name must not be empty")
    private String lastName;

    @Pattern(regexp = "[A-Za-z0-9+_.-]+@(.+)$", message = "Please, enter a valid email")
    private String email;

    @NotBlank(message = "password must not be empty")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    @NotBlank(message = "Confirm Password must not be empty")
    private String confirmPassword;

    private String bio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "city must not be empty")
    private String city;

    @NotBlank(message = "Address must not be empty")
    private String address;

    @NotBlank(message = "gender must not be empty")
    private String gender;

    @NotBlank(message = "phone number must not be empty")
    private String phoneNumber;

    private String url;

    @JsonIgnore
    @AssertTrue(message = "Passwords do not match")
    public boolean isPasswordAndConfirmPasswordEqual() {
        return this.password.equals(confirmPassword);
    }

    @JsonIgnore
    @AssertTrue(message = "age must be 18+")
    public boolean isAgeAbove18() {
        if (dateOfBirth == null) {
            return false;
        }
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears() >= 18;
    }

    private RegisterRequest(String firstName, String lastName, String email, String password, String confirmPassword, String bio, LocalDate dateOfBirth, String city, String address, String gender, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public RegisterRequest() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder {

        private String firstName;

        private String lastName;

        private String email;

        private String password;

        private String confirmPassword;

        private String bio;

        private LocalDate dateOfBirth;

        private String city;

        private String address;

        private String gender;

        private String phoneNumber;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(firstName, lastName, email, password, confirmPassword, bio, dateOfBirth, city, address, gender, phoneNumber);
        }
    }
}
