/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author shnva
 */
public interface HangmanDataprovider {
    public List<Databaseable> getAll(Class c);
    public Databaseable get(Class c, int id);
    public void add(Databaseable item);
    public void remove(Databaseable item);
    public void update(Databaseable item);
}
