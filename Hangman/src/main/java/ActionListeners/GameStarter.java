/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionListeners;

/**
 *
 * @author shnva
 */
public abstract class GameStarter {
    public abstract void startGame();
    public void restartGame() {
        startGame();
    }
}
