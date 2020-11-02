package Classes;

import ActionListeners.GameStarter;
import ActionListeners.UserNotifier;
import Enums.Enums.DifficultyType;
import Enums.Enums.GameState;
import Interfaces.HangmanDataprovider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import Interfaces.Timeable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shnva
 */
public class Game {

    private DifficultyType difficultyType;
    private final QuizMaster quizMaster;
    private Difficulty currentDifficulty;
    private Word playingWord;
    private Highscore currentHighscore;
    private int amountOfTries = 9;
    private final HangmanDataprovider repo;
    private ArrayList<Character> guessedCharacters, wrongCharacters;
    private final GameStarter gameStarter;

    public ArrayList<Character> getWrongCharacters() {
        return wrongCharacters;
    }

    public ArrayList<Character> getGuessedCharacters() {
        return guessedCharacters;
    }

    public Word getPlayingWord() {
        return playingWord;
    }

    public int getAmountOfTries() {
        return amountOfTries;
    }
    
    public DifficultyType getDifficultyType() {
        return difficultyType;
    }
    
    private void setSelectedDifficulty(DifficultyType diff) {
        this.currentDifficulty = new DifficultyFactory().getDifficulty(repo, diff);
        this.difficultyType = diff;
    }

    public Game(HangmanDataprovider repo, GameStarter starter) {
        this.gameStarter = starter;
        this.repo = repo;
        this.guessedCharacters = new ArrayList<>();
        this.wrongCharacters = new ArrayList<>();
        this.quizMaster = new QuizMaster(repo);
    }
    
    public void startGame(DifficultyType diff) {
        setSelectedDifficulty(diff);
        this.playingWord = currentDifficulty.getRandomWord(repo);
        this.currentHighscore = new Highscore(System.currentTimeMillis(), this.difficultyType.getValue(), repo);

        //Used for debugging purposes only!!!
        System.out.println(this.playingWord.getWordText());
    }
    
    public int getCurrentDifficultyId() {
        return difficultyType.getValue();
    }
    
    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }
    
    public void getHint(UserNotifier notifier) {
        if (difficultyType == DifficultyType.Easy) {
            EasyDifficulty ed = (EasyDifficulty) this.currentDifficulty;
            guessLetter(ed.getHint(this.playingWord, this.guessedCharacters), notifier);
        }
    }
    
    public void setTimer() {
        
    }
    
    public void askQuizQuestion(UserNotifier notifier) {
        if (this.amountOfTries > 0 && (this.amountOfTries == 6 || this.amountOfTries < 3)) {
            this.quizMaster.askQuizQuestion();
            QuizQuestion question = this.quizMaster.getCurrentQuizQuestion();
            String answer = notifier.getUserInput(question.getQuestionText());
            if(!answerQuizQuestion(answer)) {
                notifier.notifyUser("Helaas, fout beantwoord. Er gaat een extra beurt van je speelbeurten af.");
            }
        }
    }

    public void guessLetter(char c, UserNotifier notifier) {
        Pattern regex = Pattern.compile("[a-zA-Z]");
        Matcher matcher = regex.matcher(String.valueOf(c));
        
        if (matcher.find()) {
            var indexes = playingWord.getLetterIndexes(c);
            if (!guessedCharacters.contains(c) && !wrongCharacters.contains(c)) {
                if (indexes.size() > 0) {
                    guessedCharacters.add(c);
                    if (guessedCharacters.size() == this.playingWord.getWordText().length()) {
                        finishGame(GameState.PlayerHasWon, notifier);
                    }
                } else {
                    wrongCharacters.add(c);
                    amountOfTries--;
                    if (amountOfTries == 0) {
                        finishGame(GameState.GameOver, notifier);
                    }
                }
            } else {
                notifier.notifyUser("Deze letter is al ingevoerd!");
            }
        } else {
            notifier.notifyUser("De ingevulde letter moet een alfabetische letter zijn!");
        }
    }

    public void finishGame(GameState state, UserNotifier notifier) {
        //always set end timestamp, also if game is lost
        this.currentHighscore.setEndTimeStamp(System.currentTimeMillis());
        
        if(this.currentDifficulty instanceof Timeable) {
            Timeable timeableDifficulty = (Timeable)this.currentDifficulty;
            timeableDifficulty.stopTimer();
        }
        
        if (state == GameState.PlayerHasWon) {
            String username = notifier.getUserInput("Je hebt gewonnen! Voer je naam in om je tijd aan de highscores to te voegen.");
            this.currentHighscore.addHighscoreToDatabase(username);
            notifier.showHighscores(getHighscoresText());
        } else {
            switch (state) {
                case GameOver:
                    notifier.notifyUser("Helaas... Je hebt geen beurten meer. Game over!");
                    break;
                case WronglyGuessedWord:
                    notifier.notifyUser("Helaas... Je hebt niet het juiste woord geraden. Game over!");
                    break;
                case TimerElapsed:
                    notifier.notifyUser("Helaas... De tijd is op. Game over!");
                    break;
                default:
                    break;
            }
            notifier.closeGame();
            gameStarter.restartGame();
        }
    }

    private boolean answerQuizQuestion(String answer) {
        boolean result = quizMaster.checkAnswer(answer);
        if(!result) {
            this.amountOfTries--;
        }
        return result;
    }
    
    public void quitGame() {
        gameStarter.restartGame();
    }
    
    public List<String> getHighscoresText() {
        List<String> result = new ArrayList<>();
        List<Highscore> highscores = repo.getAll(Highscore.class)
                .stream()
                .map(i -> (Highscore)i)
                .filter(i -> i.getDifficultyId() == difficultyType.getValue())
                .collect(Collectors.toList());

        Collections.sort(highscores);

        highscores.stream().limit(10).forEach(item -> {
            result.add(item.getPlayer() + " - " + item.getElapsedTimeInText());
        });
 
        return result;
    }

    public void guessWord(String s, UserNotifier notifier) {   
        if (this.playingWord.getWordText().toLowerCase().equals(s.toLowerCase())) {
            finishGame(GameState.PlayerHasWon, notifier);
        } else {
            finishGame(GameState.WronglyGuessedWord, notifier);
        }
    }
}
