package com.exampledemo.parsaniahardik.updatesqlitedemonuts;

import java.io.Serializable;

/**
 * Created by Parsania Hardik on 26-Apr-17.
 */
public class UserModel implements Serializable{

    private String name, hobby;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
