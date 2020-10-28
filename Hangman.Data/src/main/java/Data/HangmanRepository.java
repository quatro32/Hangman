/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Interfaces.Databaseable;
import Interfaces.HangmanDataprovider;
import java.util.List;

/**
 *
 * @author shnva
 */
public class HangmanRepository implements HangmanDataprovider {
    
    private Dataprovider provider;

    @Override
    public List<Databaseable> getAll(Class c) {
        provider = new Dataprovider<>(c);
        return provider.getAll();
    }

    @Override
    public Databaseable get(Class c, int id) {
        for(Databaseable item : getAll(c)) {
            int gid = item.getId();
            if(item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void add(Databaseable item) {
        provider = new Dataprovider<>(item.getClassType());
        provider.add(item);
    }

    @Override
    public void remove(Databaseable item) {
        provider = new Dataprovider<>(item.getClassType());
        provider.remove(item);
    }

    @Override
    public void update(Databaseable item) {
        provider = new Dataprovider<>(item.getClassType());
        provider.update(item);
    }
    
}
