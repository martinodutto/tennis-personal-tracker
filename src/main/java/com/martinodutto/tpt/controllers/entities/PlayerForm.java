package com.martinodutto.tpt.controllers.entities;

public class PlayerForm {

    private String _name;

    private String _surname;

    private String _gender;

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
