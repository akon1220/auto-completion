import java.util.Comparator;


public class CountedComparator<E extends Comparable<E>> implements Comparator<E> {
	private Comparator<E> myComparator;
	private int myCount;
	
	public CountedComparator(){
		myComparator = Comparator.naturalOrder();
	}
	
	public CountedComparator(Comparator<E> comp) {
		myComparator = comp;
	}
	
	@Override
	public int compare(E o1, E o2) {
		myCount++;
		return myComparator.compare(o1,o2);
	}
	
	/**
	 * Return number of times compare is called
	 * @return
	 */
	public int getCount() {
		return myCount;
	}
	
	/**
	 * Reset internal counter to zero so that
	 * getCount() will return zero immediately after clear()
	 */
	public void reset() {
		myCount = 0;
	}
	
}
