/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Mocks.Difficulty;
import Mocks.Highscore;
import Mocks.QuizQuestion;
import Mocks.Word;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author shnva
 */
public class DataproviderTest {
        
    public DataproviderTest() throws ClassNotFoundException {
    }

    /**
     * Test of remove method, of class Dataprovider.
     */
    @Test
    public void testRemove() {
        Dataprovider provider = new Dataprovider<>(QuizQuestion.class);
        List<QuizQuestion> quizQuestions = provider.getAll();
        QuizQuestion quizQuestion = quizQuestions.get(quizQuestions.size() - 1);
        int affected = provider.remove(quizQuestion);
        assertEquals(1, affected);
    }

    /**
     * Test of add method, of class Dataprovider.
     */
    @Test
    public void testAdd() {
        Dataprovider provider = new Dataprovider<>(QuizQuestion.class);
        //get the amount of items before a new item is added
        int oldAmountOfItems = provider.getAll().size();
        
        QuizQuestion newQuestion = new QuizQuestion("Is dit een test voor een testAdd()? Ja/nee?", "Ja");
        provider.add(newQuestion);
        
        int newAmountOfItems = provider.getAll().size();
        assertEquals(newAmountOfItems, oldAmountOfItems + 1);
    }

    /**
     * Test of update method, of class Dataprovider.
     */
    @Test
    public void testUpdate() {
        Dataprovider provider = new Dataprovider<>(Word.class);
        Word oldWord = (Word) provider.get(24); // a word created for unit testing
        oldWord.setWordText(oldWord.getWordText() + "123");
        
        provider.update(oldWord);
        
        Word newWord = (Word)provider.get(24);
        
        assertEquals(oldWord.getId(), newWord.getId());
        assertEquals(oldWord.getWordText(), newWord.getWordText());
        assertEquals(oldWord.getDifficultyId(), newWord.getDifficultyId());
    }

    /**
     * Test of getAll method, of class Dataprovider.
     */
    @Test
    public void testGetAll() {
        Dataprovider provider = new Dataprovider<>(Word.class);
        List<Word> words  = provider.getAll();
        assertNotNull(words);
        
        provider = new Dataprovider<>(Highscore.class);
        List<Highscore> highscores  = provider.getAll();
        assertNotNull(highscores);
        
        provider = new Dataprovider<>(Difficulty.class);
        List<Difficulty> difficulties  = provider.getAll();
        assertNotNull(difficulties);
        
        provider = new Dataprovider<>(QuizQuestion.class);
        List<QuizQuestion> quizQuestions  = provider.getAll();
        assertNotNull(quizQuestions);
    }

    /**
     * Test of get method, of class Dataprovider.
     */
    @Test
    public void testGet() {
        Dataprovider provider = new Dataprovider<>(Word.class);
        
        //an actual copy of the word in the database with id == 1
        Word wMock1 = new Word(1, 1, "tester");
        Word word1 = (Word)provider.get(1);
        assertEquals(wMock1.getId(), word1.getId());
        assertEquals(wMock1.getWordText(), word1.getWordText());
        assertEquals(wMock1.getDifficultyId(), word1.getDifficultyId());
        
        //an actual copy of the word in the database with id == 2
        Word wMock2 = new Word(2, 1, "huis");
        Word word2 = (Word)provider.get(2);
        assertEquals(wMock2.getId(), word2.getId());
        assertEquals(wMock2.getWordText(), word2.getWordText());
        assertEquals(wMock2.getDifficultyId(), word2.getDifficultyId());
    }    
}
