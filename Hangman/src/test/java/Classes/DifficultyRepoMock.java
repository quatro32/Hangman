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
public class DifficultyRepoMock implements HangmanDataprovider {
    private final List<Databaseable> difficulties = new ArrayList<>() {
        {
            add(new EasyDifficulty(1, "Easy", 0));
            add(new MediumDifficulty(2, "Medium", 0));
            add(new HardDifficulty(3, "Hard", 1));
        }
    };

    @Override
    public List<Databaseable> getAll(Class c) {
        return difficulties;
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
        this.difficulties.add(item);
    }

    @Override
    public void remove(Databaseable item) {
        this.difficulties.remove(item);
    }

    @Override
    public void update(Databaseable item) {
        Optional<Databaseable> existingItem = this.difficulties.stream().filter(i -> i.getId() == item.getId()).findFirst();
        if(!existingItem.isEmpty()){
            this.difficulties.remove(existingItem.get());
            this.difficulties.add(item);
        }
    }
}
