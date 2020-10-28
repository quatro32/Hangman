/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.Databaseable;

/**
 *
 * @author shnva
 */
public final class QuizQuestion implements Databaseable {
    private int id;
    private String questionText, answer;
    
    public QuizQuestion() {
        
    }
    
    public QuizQuestion(int id, String questionText, String answer) {
        this.id = id;
        this.questionText = questionText;
        this.answer = answer;
    }
    
    public boolean checkAnswer(String answer) {
        return this.answer.toLowerCase().equals(answer.toLowerCase());
    }
    
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Class getClassType() {
        return this.getClass();
    }
}
