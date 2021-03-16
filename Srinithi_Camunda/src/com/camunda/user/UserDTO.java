package com.camunda.user;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
    private int id;
    private String email;

    @SerializedName(value="firstName", alternate="first_name")
    private String firstName;

    @SerializedName(value="lastName", alternate="last_name")
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
