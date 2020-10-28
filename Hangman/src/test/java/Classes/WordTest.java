/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shnva
 */
public class WordTest {
    
    private final Word mock = new Word(149, "MockMock", Enums.DifficultyType.Hard);
    
    public WordTest() {
    }
    
    /**
     * Test of letterIndexes method, of class Word.
     */
    @Test
    public void testLetterIndexes() {
        int sizeLarge = mock.getLetterIndexes('M').size();
        int sizeSmall = mock.getLetterIndexes('m').size();
        assertEquals(sizeSmall, sizeLarge);
    }
}
