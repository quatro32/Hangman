/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnva
 */
public class QuizMasterTest {
    
    private final QuizMaster quizMasterMock = new QuizMaster(new QuizQuestionRepoMock());
    
    public QuizMasterTest() {
    }

    /**
     * Test of getCurrentQuizQuestion method, of class QuizMaster.
     */
    @Test
    public void testGetCurrentQuizQuestion() {
        quizMasterMock.askQuizQuestion();
        QuizQuestion question = quizMasterMock.getCurrentQuizQuestion();
        assertNotNull(question);
    }

    /**
     * Test of checkAnswer method, of class QuizMaster.
     */
    @Test
    public void testCheckAnswer() {
        quizMasterMock.askQuizQuestion();
        assertEquals(quizMasterMock.checkAnswer(quizMasterMock.getCurrentQuizQuestion().getAnswer()), true);
        assertEquals(quizMasterMock.checkAnswer("Dit is NIET het antwoord!!"), false);
    }

    /**
     * Test of askQuizQuestion method, of class QuizMaster.
     */
    @Test
    public void testAskQuizQuestion() {
        quizMasterMock.askQuizQuestion();
        assertNotNull(quizMasterMock.getCurrentQuizQuestion());
    }
    
}
