package src.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class OrderedFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public OrderedFD() {
		super("Ordered");
	}

	/**
	 * Similarly to the SortedList, we have an ordered list from which we can counts the amount of same inputs that are currently in the list.
	 * First, we create a temp data set with the Java.Util sort which organizes the elements from smallest to biggest. Once that is done, we begin 
	 * to count. If the comparator returns a number lower than 0 this means the next available object is different from the one that the loop is being
	 * done in, which means it is time to add to the result array which is what we are looking for in the end. Like in the SortedList, it wasn't adding
	 * the last element because it was the end of the list, so I decided to add a contidion that checked if count was not 0 and that i was smaller than 
	 * the tempDataSet, and if those two were true, then that entry was never fully added to the results list that we are returning and there is a need 
	 * to add it so that the FD is correctly done.
	 * 
	 * @param dataSet
	 * @return results
	 * @author Maria Alejandra Munoz Valenzuela
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		ArrayList<E> tempDataSet = new ArrayList<E>(dataSet);
		tempDataSet.sort(null);
		int count = 0;
		int i = 0;
		for (i = 0; i < tempDataSet.size() - 1; i++) {
			if(tempDataSet.get(i).compareTo(tempDataSet.get(i + 1)) < 0) {
				count++;
				Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(tempDataSet.get(i), count);
				results.add(entry);
				count = 0;
			} else if(tempDataSet.get(i).compareTo(tempDataSet.get(i + 1)) == 0){
				count++;
			} else {
				count++;
			}
		}
		if(count != 0 && i < tempDataSet.size()) { 
			count++;
			Map.Entry<E, Integer> entry = new AbstractMap.SimpleEntry<E, Integer>(tempDataSet.get(i - 1), count);
			results.add(entry);
		}
		return results;
	}


}