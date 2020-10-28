/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums;
import Interfaces.HangmanDataprovider;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author shnva
 */
public class Settings {

    private final HangmanDataprovider repo;
    private Word selectedWord;

    public Word getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(Word selectedWord) {
        this.selectedWord = selectedWord;
    }

    public Settings(HangmanDataprovider repo) {
        this.repo = repo;
    }
    
    public boolean login(String password) {
        return password.equals("password");
    }

    //public ArrayList<WordListItem> getWordListItems() throws SQLException {
    //    ArrayList<WordListItem> result = new ArrayList<>();
//
    //    for (Map.Entry<Integer, String> entry : Database.DatabaseHelper.getAllWords().entrySet()) {
    //        result.add(new WordListItem(entry.getKey(), entry.getValue()));
    //    }
    //   return result;
    //}
    public List<Word> getAllWords() {
        return repo.getAll(Word.class)
                .stream()
                .map((i) -> (Word)i)
                .collect(Collectors.toList());
    }

    public Word getSelectedWord(String wordText) {
        int id = Integer.parseInt(wordText.substring(wordText.indexOf("(") + 1, wordText.indexOf(")")));
        return (Word)repo.get(Word.class, id);
    }

    public void removeSelectedWord() {
        repo.remove(this.selectedWord);
    }

    public void saveWord(String wordText, int difficultyId) {
        this.selectedWord.setWordText(wordText);
        this.selectedWord.setDifficulty(Enums.DifficultyType.getDifficultyTypeFromInt(difficultyId));
        if(this.selectedWord.getId() == 0) {
            repo.add(this.selectedWord);
        }else{
            repo.update(this.selectedWord);
        }
    }
    
    public void newWord() {
        this.selectedWord = new Word();
    }
}
