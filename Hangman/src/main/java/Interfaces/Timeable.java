/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import ActionListeners.UserNotifier;
import Classes.Game;

/**
 *
 * @author shnva
 */
public interface Timeable {
    public void stopTimer();
    public int getTimerTime();
    public void setTimer(Game game, UserNotifier notifier);
}
