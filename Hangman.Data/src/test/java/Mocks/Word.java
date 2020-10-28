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
public class Word {
    private int id, difficultyId;
    private String wordText;
    
    public Word() {
        
    }
    
    public Word(int id, int difficultyId, String wordText) {
        this.id = id;
        this.difficultyId = difficultyId;
        this.wordText = wordText;
    }

    public Word(int difficultyId, String wordText) {
        this.difficultyId = difficultyId;
        this.wordText = wordText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId = difficultyId;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public int getId() {
        return id;
    }

    public int getDifficultyId() {
        return difficultyId;
    }

    public String getWordText() {
        return wordText;
    }
}
