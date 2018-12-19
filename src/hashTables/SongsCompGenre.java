/**
 * Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * wrapper class for SongEntry Object
 * determine number of songs in each string genre
 * implements Comparable
 */
//Both override the compareTo(), equals(), hashCode() and toString() methods.
//Wrapper class for a SongEntry object. We will use this to compare objects based on the songs String genre field. We will
//use this to determine the number of songs in each genre.
//Declare that it implements the Comparable< ... > interface of type String.
public class SongsCompGenre implements Comparable<String> {

    /**
     * attribute of type string for name of genre
     */
    //Contains an attribute of type String for the name of the genre.
    private String genre;

    /**
     * attribute of ArrayList<SongEntry> for all songs in genre
     */
    //Contains an attribute of type ArrayList<SongEntry> for all the songs in that genre, which is our data.
    private ArrayList<SongEntry> songObject;

    //default constructor
    public SongsCompGenre() {
        songObject = new ArrayList<>();
        genre = "undefined";
    }

    /**
     * constructor
     * @param genre
     * @param mySong
     */
    //constructor
    public SongsCompGenre(String genre, SongEntry mySong) {
        this();
        this.genre = genre;
        songObject.add(mySong);
    }

    /**
     * override toString()
     * output data for specific instance of wrapper class
     * @return
     */
    public String toString() {
        return songObject.toString();
    }

    /**
     * override equals()
     * let equals() preserve the equals(0 provided by embedded data
     * @param rhs
     * @return
     */
    public boolean equals(SongsCompGenre rhs) {
        return genre == rhs.genre;
    }

    /**
     * override hashCode()
     * @return
     */
    public int hashCode() {
        return genre.hashCode();
    }

    /**
     * adds song to list of songs
     * @param e
     */
    //Contains the method void addSong(SongEntry e), which adds a song to the list of songs.
    public void addSong(SongEntry e) {
        songObject.add(e);
    }

    /**
     * override compareTo()
     * implement find on key
     * @param key
     * @return
     */
    public int compareTo(String key) {
        return genre.compareTo(key);
    }

    /**
     * getter methods for attributes
     * get name for genre
     * @return
     */
    //Getter methods for the attributes
    public String getName() {
        return genre;
    }

    /**
     * getter methods for attributes
     * get songs (data) in that genre
     * @return
     */
    public ArrayList<SongEntry> getData() {
        return songObject;
    }

}
