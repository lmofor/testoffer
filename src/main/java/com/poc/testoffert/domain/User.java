package com.poc.testoffert.domain;


import com.poc.testoffert.aop.annotation.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.poc.testoffert.config.Constants;


/**
 * A user
 */

@Document(collection = "user")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "login should not be null")
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50, message = "login should not be more than 50 characters")
    @NotEmpty(message = "login should not be empty")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String login;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @Email(message = "Email should be in email format !")
    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    @Size(min = 5, max = 254, message = "email should not be less than 5 and more than 254 characters")
    private String email;

    @Size(min = 2, max = 2, message = "languageCode should be 2 characters")
    @NotNull(message = "languageCode should not be null")
    @Field("language_code")
    private String languageCode = Constants.DEFAULT_LANGUAGE_CODE;

    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("last_name")
    private String lastName;

    @Size(max = 101)
    @Field("full_name")
    private String fullName;

    @Size(max = 255)
    @Field("country")
    @NotNull(message = "country should not be null")
    private String country;

    @Field("registration_date")
    private Instant registrationDate;

    @Field("age")
    @NotNull(message = "age should not be null")
    private Integer age;

    private boolean enabled;

    @DBRef
    @CascadeSave
    //@JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @DBRef
    @CascadeSave
    //@JsonIgnore
    private Set<Address> addresses = new HashSet<>();

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && login != null && email != null && (login.equals(user.login) || email.equals(user.email) || id.equals(user.id));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,login, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", registrationDate=" + registrationDate +
                ", age=" + age +
                ", enabled=" + enabled +
                '}';
    }
}
