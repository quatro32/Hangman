/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.Databaseable;
import Interfaces.HangmanDataprovider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author shnva
 */
public class HighscoreRepoMock implements HangmanDataprovider {
    
    private final List<Databaseable> highscores = new ArrayList<>() {
        {
            add(new Highscore(1, 1, 11000, "tester1"));
            add(new Highscore(1, 1, 22000, "tester2"));
            add(new Highscore(1, 2, 33000, "tester3"));
            add(new Highscore(1, 3, 500, "tester4"));
            add(new Highscore(1, 3, 1000, "tester5"));
        }
    };

    @Override
    public List<Databaseable> getAll(Class c) {
        return highscores;
    }

    @Override
    public Databaseable get(Class c, int id) {
        for (Databaseable item : getAll(c)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void add(Databaseable item) {
        this.highscores.add(item);
    }

    @Override
    public void remove(Databaseable item) {
        this.highscores.remove(item);
    }

    @Override
    public void update(Databaseable item) {
        Optional<Databaseable> existingItem = this.highscores.stream().filter(i -> i.getId() == item.getId()).findFirst();
        if(!existingItem.isEmpty()){
            this.highscores.remove(existingItem.get());
            this.highscores.add(item);
        }
    }
}
