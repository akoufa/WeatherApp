package com.akoufatzis.weatherapp.data.memory;

import java.util.HashMap;

import rx.Observable;

public class MemoryCache {

    // 5 min
    public static final long VALIDATION_PERIOD = 1000 * 60 * 5;

    //Special value for infinity
    public static final long VALIDATION_INFINITY = -1;

    public static class MemoryKeys {

    }

    private HashMap<String, MemoryItem> memoryCache = new HashMap<>();

    public void put(String key, Object value, long validationPeriod) {

        memoryCache.put(key, new MemoryItem(value, validationPeriod));
    }

    public <T> Observable<T> getObservable(String key) {

        MemoryItem<T> memoryItem = memoryCache.get(key);

        return (memoryItem != null && memoryItem.isValid()) ? Observable.just(memoryItem.getItem()) : Observable.empty();
    }

    public <T> T get(String key) {

        MemoryItem<T> memoryItem = memoryCache.get(key);

        return (memoryItem != null) ? memoryItem.getItem() : null;
    }

    public void clear() {

        memoryCache.clear();
    }
}
