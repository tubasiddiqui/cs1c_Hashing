/**
 * Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class will create and populate two hash tables of
 * type FHhashQPwFind class, one for each wrapper class
 */
//Define the TableGenerator class to have two class fields of type
//FHhashQPwFind which extend the parent class FHhashQP.
public class TableGenerator {
    /**
     * 2 hash tables of FHhashQPwFind
     * tableOfSongIDs a hash table based on SongCompInt
     * tableOfSongGenres a hash table based on SongsCompGenre
     * ArrayList of genre names
     */
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongsIDs = new FHhashQPwFind<>();
    private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres = new FHhashQPwFind<>();
    private ArrayList<String> genreNames = new ArrayList<>();

    /**
     * tableOfSongIDs a hash table based on SongCompInt
     * compare songs based on int ID field
     * @param allSongs
     * @return
     */
    //Populates a hash table for comparing songs based on their int field ID.
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs) {
        for(int i = 0; i < allSongs.length; i++) {
            SongCompInt e = new SongCompInt(allSongs[i]);
            tableOfSongsIDs.insert(e);
        }
        return tableOfSongsIDs;
    }

    /**
     * tableOfSongGenres a hash table based on SongsCompGenre
     * compare songs based on their String field genre.
     * @param allSongs
     * @return
     */
    //Populates a hash table for comparing songs based on their String field genre.
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {
        for(int i = 0; i < allSongs.length; i++) {
            try {
                SongsCompGenre gen = tableOfSongGenres.find(allSongs[i].getGenre());
                gen.addSong(allSongs[i]);
            }
            catch(NoSuchElementException e) {
                    SongsCompGenre mySongs = new SongsCompGenre(allSongs[i].getGenre(), allSongs[i]);
                    tableOfSongGenres.insert(mySongs);
                    genreNames.add(allSongs[i].getGenre());
                }
        }
        return tableOfSongGenres;

    }

    /**
     * ArrayList of genre names
     * @return unique genre names found when populating genre table
     */
    public ArrayList<String> getGenreNames() {
        return genreNames;
    }
}