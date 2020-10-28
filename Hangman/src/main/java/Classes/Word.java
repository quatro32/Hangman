package Classes;

import Enums.Enums;
import Enums.Enums.DifficultyType;
import Interfaces.Databaseable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shnva
 */
public class Word implements Databaseable {

    private int id;
    private String wordText;
    private int difficultyId;
    private Enums.DifficultyType difficulty;

    public Word() {
        this.id = 0;
        this.wordText = "";
        this.difficulty = Enums.DifficultyType.Easy;
        this.difficultyId = this.difficulty.getValue();
    }

    public Word(int id, String text, Enums.DifficultyType diff) {
        this.id = id;
        this.wordText = text;
        this.difficulty = diff;
    }

    public Word(int id, String text, int difficultyId) {
        this.id = id;
        this.wordText = text;
        this.difficultyId = difficultyId;
        this.difficulty = Enums.DifficultyType.getDifficultyTypeFromInt(difficultyId);
    }

    public Word(String text, int diff) {
        this.wordText = text;
        this.difficulty = Enums.DifficultyType.getDifficultyTypeFromInt(diff);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public Enums.DifficultyType getDifficulty() {
        return DifficultyType.getDifficultyTypeFromInt(this.difficultyId);
    }

    public void setDifficulty(Enums.DifficultyType difficulty) {
        this.difficulty = difficulty;
        this.difficultyId = difficulty.getValue();
    }

    public List<Integer> getLetterIndexes(char c) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < wordText.length(); i++) {
            if (wordText.toLowerCase().charAt(i) == Character.toLowerCase(c)) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    @Override
    public Class getClassType() {
        return this.getClass();
    }
}
