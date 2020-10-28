/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums.DifficultyType;
import Interfaces.HangmanDataprovider;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnva
 */
public class EasyDifficultyTest {
    
    private final HangmanDataprovider repo = new DifficultyRepoMock();
    private final EasyDifficulty mock = (EasyDifficulty)repo.get(EasyDifficulty.class, DifficultyType.Easy.getValue());
    private final Word word = mock.getRandomWord(new WordRepoMock());
    
    public EasyDifficultyTest() {
    }

    /**
     * Test of getRandomWord method, of class EasyDifficulty.
     */
    @Test
    public void testGetRandomWord() {
        assertEquals(word.getDifficulty(), DifficultyType.Easy);
    }

    /**
     * Test of getHint method, of class EasyDifficulty.
     */
    @Test
    public void testGetHint() {
        char c = mock.getHint(word, new ArrayList<>());
        assertTrue(word.getWordText().indexOf(c) > 0);
    }
}
