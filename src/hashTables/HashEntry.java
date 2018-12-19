/**
 *Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

/**
 * used by FHhashQP
 * HashEntry class
 * @param <E>
 */
// HashEntry class for use by FHhashQP -----------------------
public class HashEntry<E>
{
    public E data;
    public int state;

    /**
     * constructor to initialize data
     * @param x
     * @param st
     */
    public HashEntry( E x, int st )
    {
        data = x;
        state = st;
    }

    /**
     * constructor
     */
    public HashEntry()
    {
        this(null, FHhashQP.EMPTY);
    }
}