package com.martinodutto.tpt.controllers.entities;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class PlayerForm {

    @NotEmpty
    @Length(max = 32)
    private String _name;

    @NotEmpty
    @Length(max = 32)
    private String _surname;

    @NotEmpty
    @Length(max = 1)
    private String _gender;

    @NotEmpty
    private String _guest;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_guest() {
        return _guest;
    }

    public void set_guest(String _guest) {
        this._guest = _guest;
    }

    @Override
    public String toString() {
        return "PlayerForm{" +
                "_name='" + _name + '\'' +
                ", _surname='" + _surname + '\'' +
                ", _gender='" + _gender + '\'' +
                ", _guest='" + _guest + '\'' +
                '}';
    }
}
