/**
 * Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

import java.util.NoSuchElementException;

/**
 * return found object or throw exception
 * @param <KeyType>
 * @param <E>
 */
//extend FHhashQP
//Your new class will take a second parameter KeyType
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>>
        extends FHhashQP<E> {

    //attributes/methods
    //E find(KeyType key) - returns the found object, or throws a java.util.NoSuchElementException
    public E find(KeyType key) {
        int myPos = findPosKey(key);

        if (mArray[myPos].state == ACTIVE) {
            return mArray[myPos].data;
        }
        else {
            throw new NoSuchElementException(); }
    }

    /**
     * use key to hash
     * @param key
     * @return
     */
    //int myHashKey( KeyType key) - a private or protected method, which provides a trivial modification to
    //myHash() which uses the key rather than the object, to hash
    private int myHashKey(KeyType key) {
        int hashVal;

        hashVal = key.hashCode() % mTableSize;
        if(hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     * use key to get position
     * @param key
     * @return
     */
    //int findPosKey( KeyType key ) - a private or protected method, which provides trivial modification to findPos()
    //which uses the key rather than the object, to get a position
    private int findPosKey(KeyType key)
    {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while (mArray[index].state != EMPTY
                && mArray[index].data.compareTo(key) != 0 )
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if ( index >= mTableSize )
                index -= mTableSize;
        }
        return index;
    }
}
