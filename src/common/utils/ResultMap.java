package common.utils;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class ResultMap<K,V> extends HashMap<K, V> {
	private static final long serialVersionUID = 9155660212774034975L;

	public V put(K key, V value) {
		if(key instanceof String){
			key = (K)((String)key).toUpperCase();
		}
       return super.put(key, value);
    }
}
