/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums;
import Interfaces.HangmanDataprovider;

/**
 *
 * @author shnva
 */
public class DifficultyFactory {
    public Difficulty getDifficulty(HangmanDataprovider repo, Enums.DifficultyType diff) {
        switch (diff) {
            case Medium:
                return (Difficulty)repo.get(MediumDifficulty.class, diff.getValue());
            case Hard:
                return (Difficulty)repo.get(HardDifficulty.class, diff.getValue());
            default: //if no other than easy is selected, always select easy
                return (Difficulty)repo.get(EasyDifficulty.class, diff.getValue());
        }
    }
}
