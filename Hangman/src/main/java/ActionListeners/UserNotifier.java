/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionListeners;

import java.util.List;

/**
 *
 * @author shnva
 */
public abstract class UserNotifier {
    public abstract String getUserInput(String message);
    public abstract void notifyUser(String message);
    public abstract void showHighscores(List<String> highscores);
    public abstract void closeGame();
    public abstract void drawTimer(String text);
}
