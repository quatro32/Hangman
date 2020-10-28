/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums.DifficultyType;
import Interfaces.HangmanDataprovider;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author shnva
 */
public class MediumDifficulty extends Difficulty {
    
    private String word; 
    private int id, timeInMinutes;
    
    public MediumDifficulty() {
        
    }
    
    public MediumDifficulty (int id, String word, int timeInMinutes) {
        this.id = id;
        this.word = word;
        this.timeInMinutes = timeInMinutes;
    }

    @Override
    public Word getRandomWord(HangmanDataprovider repo) {
        DifficultyType diffType = DifficultyType.Medium;
        Random r = new Random();
        List<Word> words = repo.getAll(Word.class)
                .stream()
                .map((i) -> (Word)i)
                .filter(i -> i.getDifficulty() == diffType)
                .collect(Collectors.toList());
        return words.get(r.nextInt(words.size()));
    }  

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Class getClassType() {
        return super.getClass();
    }
}
