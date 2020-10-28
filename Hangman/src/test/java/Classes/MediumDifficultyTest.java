/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums;
import Interfaces.HangmanDataprovider;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnva
 */
public class MediumDifficultyTest {
    
    private final HangmanDataprovider repo = new DifficultyRepoMock();
    private final MediumDifficulty mock = (MediumDifficulty)repo.get(MediumDifficulty.class, Enums.DifficultyType.Medium.getValue());
    private final Word word = mock.getRandomWord(new WordRepoMock());
    
    public MediumDifficultyTest() {
    }

    /**
     * Test of getRandomWord method, of class EasyDifficulty.
     */
    @Test
    public void testGetRandomWord() {
        assertEquals(word.getDifficulty(), Enums.DifficultyType.Medium);
    }
}
