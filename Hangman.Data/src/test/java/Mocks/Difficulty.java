/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mocks;

/**
 *
 * @author shnva
 */
public class Difficulty {
    private int id, timeinminutes;
    private String name;
    
    public Difficulty() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeinminutes() {
        return timeinminutes;
    }

    public void setTimeinminutes(int timeinminutes) {
        this.timeinminutes = timeinminutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
