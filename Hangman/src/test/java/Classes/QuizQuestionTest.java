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
public class QuizQuestionTest {

    public QuizQuestionTest() {
    }

    /**
     * Test of checkAnswer method, of class QuizQuestion.
     */
    @Test
    public void testCheckAnswer() {
        QuizQuestion mock = new QuizQuestion(21, "Is dit een mockvraag?", "Ja");
        
        assertEquals(mock.checkAnswer("JA"), true);
        assertEquals(mock.checkAnswer("ja"), true);
        assertEquals(mock.checkAnswer("jA"), true);
        assertEquals(mock.checkAnswer("nee!!!"), false);
    }

    /**
     * Test of getQuestionText method, of class QuizQuestion.
     */
    @Test
    public void testGetQuestionText() {
        QuizQuestion mock = new QuizQuestion(89, "Is dit een normale vraag?", "Nee!");
        
        assertEquals(mock.getQuestionText(), "Is dit een normale vraag?");
        assertNotSame(mock.getQuestionText(), "Is dit geen normale vraag??????????");
    }

    /**
     * Test of setQuestionText method, of class QuizQuestion.
     */
    @Test
    public void testSetQuestionText() {
    }

    /**
     * Test of getAnswer method, of class QuizQuestion.
     */
    @Test
    public void testGetAnswer() {
    }

    /**
     * Test of setAnswer method, of class QuizQuestion.
     */
    @Test
    public void testSetAnswer() {
    }
    
}
