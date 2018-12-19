/**
 * Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

import cs1c.SongEntry;

/**
 * wrapper class to compare SongEntry object based on integer ID field
 * use Comparable interface
 */
//Both override the compareTo(), equals(), hashCode() and toString() methods.
//Wrapper class for a SongEntry object. We will use this to compare objects based on the songs int id field
//Declare that it implements the Comparable< ... > interface of type Integer.
public class SongCompInt implements Comparable<Integer> {

    /**
     * SongEntry object
     */
    //Contains a single SongEntry object as the attribute.
    private SongEntry songObject;

    /**
     * constructor
     * @param e
     */
    //constructor
    public SongCompInt(SongEntry e) {
        songObject = e;
    }

    /**
     * override compareTo()
     * implement find on key
     * @param key
     * @return
     */
    //Both override the compareTo(), equals(), hashCode() and toString() methods
    public int compareTo (Integer key)
    {
        return songObject.getID() - key;
    }

    /**
     * override equals()
     * let equals() preserve the equals(0 provided by embedded data
     * @param rhs
     * @return songObject.equals(rhs.songObject)
     */
    public boolean equals(SongCompInt rhs) {
        return songObject == rhs.songObject;
    }

    /**
     * override hashCode()
     * @return
     */
    public int hashCode() {
        return songObject.getID();
    }

    /**
     * override toString()
     * output data for specific instance of wrapper class
     * @return
     */
    public String toString() {
        return songObject.toString();
    }
}
