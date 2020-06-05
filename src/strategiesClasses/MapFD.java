package src.strategiesClasses;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapFD<E extends Comparable<E>> extends AbstractFDStrategy<E> {

	public MapFD() {
		super("Map");
	}
	
	/**
	 * This strategy is far easier than anything else in this project. Basically we just check if the newly added
	 * HashTable contains the key we're trying to add, and if it does exist, we just add a value to the already existing
	 * key. I decided to use replace because if I thought it would be better to follow and I believe that the Big O
	 * notation of this will be better than if I used add. And there is a difference between the two, because add will
	 * update the key no matter what, while replace will only change the value (or key if that's what it's changing) but will
	 * not add it to the HashTable if it doesn't exist. Once the HashTable is created with it's correct keys and values
	 * all there is left to do is add it the result list. Thankfully, HashTable has a method that returns a set of Keys
	 * and Values that can be looped through and added into the results without much fault.
	 * 
	 * @param dataSet
	 * @return result
	 * @author Maria Alejandra Munoz Valenzuela
	 */
	@Override
	public ArrayList<Entry<E, Integer>> computeFDList(ArrayList<E> dataSet) {
		ArrayList<Map.Entry<E, Integer>> results = new ArrayList<Map.Entry<E, Integer>>(); 
		Hashtable<E, Integer> mapTable = new Hashtable<E, Integer>();
		for (E e : dataSet) {
			if(!mapTable.containsKey(e)) {
				mapTable.put(e, 1);
			} else {
				mapTable.replace(e, mapTable.get(e) + 1);
			}
		}
		Set<Entry<E, Integer>> keySets = mapTable.entrySet();
		for (Entry<E, Integer> e : keySets) {
			results.add(e);
		}
		
		return results;
	}

}
