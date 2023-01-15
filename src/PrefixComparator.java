import java.util.Comparator;


public class    PrefixComparator implements Comparator<Term> {

    private int myPrefixSize; // size of prefix

    /**
     * private constructor, called by getComparator
     * @param prefix is prefix used in compare method
     */
    private PrefixComparator(int prefix) {
        myPrefixSize = prefix;
    }


    /**
     * Factory method to return a PrefixComparator object
     * @param prefix is the size of the prefix to compare with
     * @return PrefixComparator that uses prefix
     */
    public static PrefixComparator getComparator(int prefix) {
        return new PrefixComparator(prefix);
    }


    @Override
    /**
     * Use at most myPrefixSize characters from each of v and w
     * to return a value comparing v and w by words. Comparisons
     * should be made based on the first myPrefixSize chars in v and w.
     * @return < 0 if v < w, == 0 if v == w, and > 0 if v > w
     */
    public int compare(Term v, Term w) {
        // change this to use myPrefixSize as specified,
        String word1 = v.getWord();
        String word2 = w.getWord();

        if (word1.length() >= myPrefixSize && word2.length() >= myPrefixSize) {
            for (int i = 0; i < myPrefixSize; i++) {
                int result = word1.charAt(i) - word2.charAt(i);
                if (result < 0) {
                    return -1;
                } 
                if (result > 0) {
                    return 1;
                }
            }
            return 0;
        }
        if (word1.length() < myPrefixSize && word2.length() >= myPrefixSize) {
            word2 = word2.substring(0, myPrefixSize);
        }
        if (word2.length() < myPrefixSize && word1.length() >= myPrefixSize) {
            word1 = word1.substring(0,myPrefixSize);
        }
        return word1.compareTo(word2);
        // replacing line below with code
        // return v.getWord().compareTo(w.getWord());
    }

}
