/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.Databaseable;
import Interfaces.HangmanDataprovider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author shnva
 */
public class QuizQuestionRepoMock implements HangmanDataprovider {
    
    private final List<Databaseable> quizQuestions = new ArrayList<>() {
        {
            add(new QuizQuestion(1, "Is dit een vraag?", "ja"));
            add(new QuizQuestion(2, "1==1?", "ja"));
            add(new QuizQuestion(3, "3x2=100", "nee"));
        }
    };

    @Override
    public List<Databaseable> getAll(Class c) {
        return quizQuestions;
    }

    @Override
    public Databaseable get(Class c, int id) {
        for (Databaseable item : getAll(c)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void add(Databaseable item) {
        this.quizQuestions.add(item);
    }

    @Override
    public void remove(Databaseable item) {
        this.quizQuestions.remove(item);
    }

    @Override
    public void update(Databaseable item) {
        Optional<Databaseable> existingItem = this.quizQuestions.stream().filter(i -> i.getId() == item.getId()).findFirst();
        if(!existingItem.isEmpty()){
            this.quizQuestions.remove(existingItem.get());
            this.quizQuestions.add(item);
        }
    }
}
