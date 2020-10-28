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
public class WordRepoMock implements HangmanDataprovider {

    private List<Databaseable> words = new ArrayList<>() {
        {
            add(new Word(1, "woord", 1));
            add(new Word(2, "hallo", 1));
            add(new Word(3, "speculaas", 2));
            add(new Word(4, "gevaar", 2));
            add(new Word(5, "ventieldopjesfabriek", 3));
            add(new Word(6, "halloditiseentestwoord", 3));
        }
    };
    
    @Override
    public List<Databaseable> getAll(Class c) {
        return words;
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
        this.words.add(item);
    }

    @Override
    public void remove(Databaseable item) {
        this.words.remove(item);
    }

    @Override
    public void update(Databaseable item) {
        Optional<Databaseable> existingItem = this.words.stream().filter(i -> i.getId() == item.getId()).findFirst();
        if(!existingItem.isEmpty()){
            this.words.remove(existingItem.get());
            this.words.add(item);
        }
    }
    
}
