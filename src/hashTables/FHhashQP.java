/**
 * Tuba Siddiqui
 * CS1C
 * Assignment 7
 */

package hashTables;

import java.util.*;

/**
 *
 * @param <E>
 */
// FHhashQP class --------------------------------------------
public class FHhashQP<E>
{
    protected static final int ACTIVE = 0;
    protected static final int EMPTY = 1;
    protected static final int DELETED = 2;

    static final int INIT_TABLE_SIZE = 97;
    static final double INIT_MAX_LAMBDA = 0.49;

    protected HashEntry<E>[] mArray;
    protected int mSize;
    protected int mLoadSize;
    protected int mTableSize;
    protected double mMaxLambda;

    /**
     * public methods
     * @param tableSize
     */
    // public methods ---------------------------------
    public FHhashQP(int tableSize)
    {
        mLoadSize = mSize = 0;
        if (tableSize < INIT_TABLE_SIZE)
            mTableSize = INIT_TABLE_SIZE;
        else
            mTableSize = nextPrime(tableSize);

        allocateArray();  // uses mTableSize;
        mMaxLambda = INIT_MAX_LAMBDA;
    }

    /**
     * public methods
     */
    public FHhashQP()
    {
        this(INIT_TABLE_SIZE);
    }

    /**
     *
     * @param x
     * @return
     */
    public boolean insert( E x)
    {
        int bucket = findPos(x);

        if ( mArray[bucket].state == ACTIVE )
            return false;

        mArray[bucket].data = x;
        mArray[bucket].state = ACTIVE;
        mSize++;

        // check load factor
        if( ++mLoadSize > mMaxLambda * mTableSize )
            rehash();

        return true;
    }

    /**
     *
     * @param x
     * @return
     */
    public boolean remove( E x )
    {
        int bucket = findPos(x);

        if ( mArray[bucket].state != ACTIVE )
            return false;

        mArray[bucket].state = DELETED;
        mSize--; // mLoadSize not dec'd because it counts any non-EMP location
        return true;
    }

    /**
     *
     * @param x
     * @return
     */
    public boolean contains(E x )
    {
        return mArray[findPos(x)].state == ACTIVE;
    }

    public int size()  { return mSize; }

    void makeEmpty()
    {
        int k, size = mArray.length;

        for(k = 0; k < size; k++)
            mArray[k].state = EMPTY;
        mSize = mLoadSize = 0;
    }

    /**
     *
     * @param lam
     * @return
     */
    public boolean setMaxLambda( double lam )
    {
        if (lam < .1 || lam > INIT_MAX_LAMBDA )
            return false;
        mMaxLambda = lam;
        return true;
    }

    // protected methods of class ----------------------

    /**
     *
     * @param x
     * @return
     */
    int findPos( E x )
    {
        int kthOddNum = 1;
        int index = myHash(x);

        while ( mArray[index].state != EMPTY
                && !mArray[index].data.equals(x) )
        {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if ( index >= mTableSize )
                index -= mTableSize;
        }
        return index;
    }


    protected void rehash()
    {
        // we save old list and size then we can reallocate freely
        HashEntry<E>[] oldArray = mArray;
        int k, oldTableSize = mTableSize;;

        mTableSize = nextPrime(2*oldTableSize);

        // allocate a larger, empty array
        allocateArray();  // uses mTableSize;

        // use the insert() algorithm to re-enter old data
        mSize = mLoadSize = 0;
        for(k = 0; k < oldTableSize; k++)
            if (oldArray[k].state == ACTIVE)
                insert( oldArray[k].data );
    }

    /**
     *
     * @param x
     * @return
     */
    protected int myHash(E x)
    {
        int hashVal;

        hashVal = x.hashCode() % mTableSize;
        if(hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     *
     * @param n
     * @return
     */
    protected static int nextPrime(int n)
    {
        int k, candidate, loopLim;

        // loop doesn't work for 2 or 3
        if (n <= 2 )
            return 2;
        else if (n == 3)
            return 3;

        for (candidate = (n%2 == 0)? n+1 : n ; true ; candidate += 2)
        {
            // all primes > 3 are of the form 6k +/- 1
            loopLim = (int)( (Math.sqrt((double)candidate) + 1)/6 );

            // we know it is odd.  check for divisibility by 3
            if (candidate%3 == 0)
                continue;

            // now we can check for divisibility of 6k +/- 1 up to sqrt
            for (k = 1; k <= loopLim; k++)
            {
                if (candidate % (6*k - 1) == 0)
                    break;
                if (candidate % (6*k + 1) == 0)
                    break;
            }
            if (k > loopLim)
                return candidate;
        }
    }


    void allocateArray()
    {
        int k;

        mArray = new HashEntry[mTableSize];
        for (k = 0; k < mTableSize; k++)
            mArray[k] = new HashEntry<E>();
    }
}