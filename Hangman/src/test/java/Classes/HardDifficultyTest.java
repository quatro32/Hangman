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
public class HardDifficultyTest {
    
    private final HangmanDataprovider repo = new DifficultyRepoMock();
    private final HardDifficulty mock = (HardDifficulty)repo.get(HardDifficulty.class, Enums.DifficultyType.Hard.getValue());
    private final Word word = mock.getRandomWord(new WordRepoMock());
    
    public HardDifficultyTest() {
    }

    /**
     * Test of getTimerTime method, of class HardDifficulty.
     */
    @Test
    public void testGetTimerTime() {
        int timeInMs = mock.getTimeInMinutes() * 1000 * 60;
        int time = mock.getTimerTime();
        assertEquals(timeInMs, mock.getTimerTime());
    }

    /**
     * Test of getRandomWord method, of class HardDifficulty.
     */
    @Test
    public void testGetRandomWord() {
        assertEquals(word.getDifficulty(), Enums.DifficultyType.Hard);
    }    
}
