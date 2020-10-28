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
public class QuizQuestion {
    private int id;
    private String questionText, answer;
    
    public QuizQuestion() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public QuizQuestion(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }
}
