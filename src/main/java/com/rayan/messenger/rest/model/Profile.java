package com.rayan.messenger.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
    private String _id;
    private String _rev;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public Profile() {
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Profile [_id=" + _id + ", _rev=" + _rev + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", age=" + age + "]";
    }


}
