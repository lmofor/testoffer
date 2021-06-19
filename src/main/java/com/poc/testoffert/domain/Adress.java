package com.poc.testoffert.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.wildfly.common.annotation.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


/**
 * An adress (a user can have many addresses)
 */
@Document(collection = "adresses")
public class Adress {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(min = 12, max = 12)
    @Field("phone")
    @NotNull
    @NotBlank
    private String phone;

    @Size(min = 3, max = 100)
    @Field("street")
    private String street;

    @Size(min = 3, max = 100)
    @Field("town")
    private String town;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return id.equals(adress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
