package src.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import ciic4020.project2.sortedlist.SortedList;
import ciic4020.project2.sortedlist.SortedArrayList;

/**
 * This class implements the SortedList strategy to count frequencies in an ArrayList.
 * @author Maria Muñoz
 *
 * @param <E> The type of the elements whose frequencies are being counted.
 */
public class SortedListFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	/**
	 * Our SortedList classes require the data type to be Comparable.
	 * However, Map.Entry and AbstractMap.SimpleEntry are not Comparable,
	 * so we extend AbstractMap.SimpleEntry and create a Comparable
	 * version that we can use with our SortedList.
	 * Note: The K (key) of this class will be the E of SortedListFD,
	 *       so it will be Comparable.
	 * @author Juan O. Lopez
	 *
	 * @param <K>  The type of the key of each entry
	 * @param <V>  The type of the value of each entry
	 */
	@SuppressWarnings("serial")
	private static class ComparableEntry<K extends Comparable<K>, V>
			extends AbstractMap.SimpleEntry<K, V>
			implements Comparable<Map.Entry<K, V>> {

		public ComparableEntry(K key, V value) {
			super(key, value);
		}

		@Override
		public int compareTo(Map.Entry<K, V> entry) {
			/* Entries will be compared based on their keys, which are Comparable */
			return getKey().compareTo(entry.getKey());
		}
		
	} // End of ComparableEntry class

	/* Constructor */
	public SortedListFD() {
		super("SortedList");
	}

	/**
	 * What we're trying to do with this strategy is take advantage of the fact that when we go to count the frequency of answers or
	 * or names, they are in order. Which means that as soon as the next one is bigger or different from the ones that's being counted
	 * it will add to the SortedList. With that sorted list, is where I count, and create the Entry that's to be added to the final list
	 * that I will be returning. Since it wasn't getting to the last entry on the SortedList, I decided to check if count was not 0 and 
	 * if i was smaller than the SortedList size. If those two conditions are true, then that entry is fully added into the results
	 * list.
	 * 
	 * @param dataSet
	 * @return results
	 * @author Maria Alejandra Munoz Valenzuela
	 */
	@Override
	public ArrayList<Map.Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>();
		SortedList<ComparableEntry<E, Integer>> sortedlist = new SortedArrayList<ComparableEntry<E, Integer>>(1000);
		for(E e : dataSet) {
			ComparableEntry<E, Integer> entry = new ComparableEntry<E, Integer>(e, 1); 
			sortedlist.add(entry);
		}
		int count = 0;
		int i = 0;
		for (i = 0; i < sortedlist.size() - 1; i++) {
			if(sortedlist.get(i).compareTo(sortedlist.get(i + 1)) < 0) {
				count++;
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(sortedlist.get(i).getKey(), count);
				results.add(entry);
				count = 0;
			} else if(sortedlist.get(i).compareTo(sortedlist.get(i + 1)) == 0){
				count++;
			} else {
				count++;
			}
		}
		if(count != 0 && i < sortedlist.size()) { 
			count++;
			Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(sortedlist.get(i - 1).getKey(), count);
			results.add(entry);
		}
		return results;	
	}

}