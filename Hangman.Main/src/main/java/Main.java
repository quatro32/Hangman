
import ActionListeners.GameStarter;
import Classes.Game;
import Data.HangmanRepository;
import Interfaces.HangmanDataprovider;
import net.bytebuddy.asm.Advice;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shnva
 */
public class Main {
    public static void main(String[] args) {
        HangmanDataprovider repo = new HangmanRepository();
        
        GameStarter starter = new GameStarter() {
            @Override
            public void startGame() {
                Game game = new Game(repo, this);
                Classes.Settings settings = new Classes.Settings(repo);
                GUIS.Main main = new GUIS.Main(game, settings);
                main.setVisible(true);
            }
        };
        
        starter.startGame();
    }
}
