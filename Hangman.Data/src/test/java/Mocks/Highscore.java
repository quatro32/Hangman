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
public class Highscore {
    private int id, difficultyid;
    private long time;
    private String name;
    
    public Highscore() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDifficultyid() {
        return difficultyid;
    }

    public void setDifficultyid(int difficultyid) {
        this.difficultyid = difficultyid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
