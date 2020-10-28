/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.HangmanDataprovider;
import javax.print.attribute.standard.DateTimeAtCompleted;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnva
 */
public class HighscoreTest {
    
    private HangmanDataprovider repo = new HighscoreRepoMock();
    private long startTimeStamp = System.currentTimeMillis();
    private Highscore mock = new Highscore(startTimeStamp, 2, repo);
    
    public HighscoreTest() {
    }

    /**
     * Test of getElapsedTime method, of class Highscore.
     */
    @Test
    public void testGetElapsedTime() {
        long endStamp = System.currentTimeMillis();
        mock.setEndTimeStamp(endStamp);
        assertEquals(mock.getElapsedTime(), startTimeStamp - endStamp);
    }


    /**
     * Test of addHighscoreToDatabase method, of class Highscore.
     */
    @Test
    public void testAddHighscoreToDatabase() {
        int itemsBefore = repo.getAll(Highscore.class).size();
        repo.add(mock);
        int itemsAfter = repo.getAll(Highscore.class).size();
        assertEquals(itemsAfter, itemsBefore + 1);
    }
}
