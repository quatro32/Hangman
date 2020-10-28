/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ActionListeners.TimerListener;
import ActionListeners.UserNotifier;
import Enums.Enums;
import Enums.Enums.DifficultyType;
import Interfaces.HangmanDataprovider;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.Timer;
import Interfaces.Timeable;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

/**
 *
 * @author shnva
 */
public class HardDifficulty extends Difficulty implements Timeable {
    
    private String word; 
    private int id, timeInMinutes;
    private Timer timer;
    
    public HardDifficulty() {
        
    }
    
    public HardDifficulty(int id, String word, int timeInMinutes) {
        this.id = id;
        this.word = word;
        this.timeInMinutes = timeInMinutes;
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    @Override
    public Word getRandomWord(HangmanDataprovider repo) {
        DifficultyType diffType = DifficultyType.Hard;
        Random r = new Random();
        List<Word> words = repo.getAll(Word.class)
                .stream()
                .map((i) -> (Word)i)
                .filter(i -> i.getDifficulty() == diffType)
                .collect(Collectors.toList());
        return words.get(r.nextInt(words.size()));
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Class getClassType() {
        return super.getClass();
    }
    
    @Override
    public boolean hasTimer() {
        return true;
    }

    @Override
    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void setTimer(Game game, UserNotifier notifier) {
        TimerListener listener = new TimerListener() {
            int timeLeft = getTimerTime();

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft -= 100;
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:S");
                notifier.drawTimer(df.format(timeLeft));
                if (timeLeft <= 0) {
                    timerElapsed();
                }
            }

            @Override
            public void timerElapsed() {
                stopTimer();
                game.finishGame(Enums.GameState.TimerElapsed, notifier);
            }
        };

        timer = new Timer(100, listener);
        timer.start();
    }
    
    @Override
    public int getTimerTime() {
        return this.timeInMinutes * 1000 * 60;
    }
}
