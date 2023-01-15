import java.util.*;

public class HashListAutocomplete implements Autocompletor {

    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null || weights == null) {
            throw new NullPointerException("One or more arguments null");
        }
        initialize(terms,weights);
    }

    @Override
    public List<Term> topMatches(String prefix, int k) {
        if (prefix == null) {
            throw new NullPointerException("null");
        }
        
        if (prefix.length() > MAX_PREFIX) {
            prefix = prefix.substring(0,MAX_PREFIX);
        }

        if (k <= 0 || !myMap.containsKey(prefix)) {
            return new ArrayList<Term>();
        }

        List<Term> allKeys = myMap.get(prefix);

        List<Term> listValues = allKeys.subList(0, Math.min(k, allKeys.size()));     
        return listValues;
    }

    @Override
    public void initialize(String[] terms, double[] weights) {
        myMap = new HashMap<String, List<Term>>();

        for (int i = 0; i < terms.length; i++) {
            int count = 0;
            Term t = new Term(terms[i], weights[i]);

            while (count <= terms[i].length() && count <= MAX_PREFIX) {
                String str = terms[i].substring(0, count);
                if (!myMap.containsKey(str)) {
                    List<Term> values = new ArrayList<Term>();
                    values.add(t);
                    myMap.put(str, values);
                } else {
                    myMap.get(str).add(t);
                }
                count++;
            }
        }
        for (String key : myMap.keySet()) {
            Collections.sort(myMap.get(key), Comparator.comparing(Term::getWeight).reversed());
        }
    }

    @Override
    public int sizeInBytes() {
        if (mySize == 0) {
            for (String str : myMap.keySet()) {
                mySize += BYTES_PER_CHAR*str.length();
                for (Term t : myMap.get(str)) {
                    mySize += BYTES_PER_DOUBLE +
                            BYTES_PER_CHAR*t.getWord().length();
                }
            }
        }
        return mySize;
    }
}

