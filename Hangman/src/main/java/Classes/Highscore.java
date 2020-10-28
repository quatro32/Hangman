/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Enums.Enums;
import Interfaces.Databaseable;
import Interfaces.HangmanDataprovider;

/**
 *
 * @author shnva
 */
public class Highscore implements Comparable<Highscore>, Databaseable {
    
    private int id, difficultyId;
    private long startTimeStamp, endTimeStamp, elapsedTime, time;
    private String player;
    private Enums.DifficultyType difficulty;
    private HangmanDataprovider repo;

    public Highscore() {

    }

    public Highscore(int id, int difficultyId, long time, String player) {
        this.id = id;
        this.difficultyId = difficultyId;
        this.time = time;
        this.player = player;
    }

    public Highscore(long timestamp, int difficultyId, HangmanDataprovider repo) {
        this.repo = repo;
        this.startTimeStamp = timestamp;
        this.difficultyId = difficultyId;
    }

    public Highscore(long elapsedTime, String player, int difficultyId) {
        this.elapsedTime = elapsedTime;
        this.player = player;
        this.difficultyId = difficultyId;
        this.difficulty = Enums.DifficultyType.getDifficultyTypeFromInt(difficultyId);
    }

    public int getDifficultyId() {
        return this.difficultyId;
    }

    public String getPlayer() {
        return this.player;
    }

    private long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public void setEndTimeStamp(long ts) {
        this.endTimeStamp = ts;
        setElapsedTime(getEndTimeStamp() - getStartTimeStamp());
    }

    private long getEndTimeStamp() {
        return this.endTimeStamp;
    }

    public long getElapsedTime() {
        if (time > 0) {
            return this.time;
        }
        return this.elapsedTime;
    }

    private void setElapsedTime(long et) {
        this.elapsedTime = et;
    }

    public String getElapsedTimeInText() {
        long difference = getElapsedTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;

        long elapsedHours = difference / hoursInMilli;
        difference = difference % hoursInMilli;

        long elapsedMinutes = difference / minutesInMilli;
        difference = difference % minutesInMilli;

        long elapsedSeconds = difference / secondsInMilli;

        return String.format("%d:%d:%d:%d", elapsedHours, elapsedMinutes, elapsedSeconds, difference);
    }

    public void addHighscoreToDatabase(String player) {
        this.player = player;
        this.time = getElapsedTime();
        repo.add(this);
    }

    @Override
    public int compareTo(Highscore o) {
        return Long.compare(this.time, o.getElapsedTime());
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Class getClassType() {
        return this.getClass();
    }
}
