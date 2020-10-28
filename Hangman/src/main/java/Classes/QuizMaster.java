/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.HangmanDataprovider;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author shnva
 */
public class QuizMaster {
    private List<QuizQuestion> availableQuizQuestions, askedQuizQuestions;
    private QuizQuestion currentQuizQuestion;    
    private final HangmanDataprovider repo;

    public QuizQuestion getCurrentQuizQuestion() {
        return currentQuizQuestion;
    }

    public QuizMaster(HangmanDataprovider repo) {
        this.repo = repo;
        this.askedQuizQuestions = new ArrayList<>();
        this.availableQuizQuestions = getQuizQuestions();
    }
    
    public boolean checkAnswer(String answer) {
        return this.currentQuizQuestion.checkAnswer(answer);
    }

    public void askQuizQuestion() {
        Random r = new Random();
        QuizQuestion question = availableQuizQuestions.get(r.nextInt(availableQuizQuestions.size()));
        this.askedQuizQuestions.add(question);
        this.availableQuizQuestions.remove(question);
        this.currentQuizQuestion = question;
    }
    
    private List<QuizQuestion> getQuizQuestions() {
        return repo.getAll(QuizQuestion.class)
                .stream()
                .map(i -> (QuizQuestion)i)
                .collect(Collectors.toList());
    }
}
