/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author shnva
 */
public class Enums {
    public enum GameState {
        Idle(0),
        GameOver(1),
        PlayerHasWon(2),
        TimerElapsed(3),
        WronglyGuessedWord(4);
        
        private final int value;

        private GameState(int value) {
            this.value = value;
        }
    }

    public enum DifficultyType {
        Easy(1),
        Medium(2),
        Hard(3);

        private final int value;

        private DifficultyType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static DifficultyType getDifficultyTypeFromInt(int value) {
            switch (value) {
                case 2:
                    return Medium;
                case 3:
                    return Hard;
                default:
                    return Easy;
            }
        }
    }

    public enum QuizQuestionType {
        Sum(1),
        Text(2);

        private final int value;

        private QuizQuestionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
