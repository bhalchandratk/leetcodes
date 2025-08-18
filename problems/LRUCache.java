 package problems;

import java.util.HashMap;

public class LRUCache<KEY,VALUE> {
    private int capacity;
    private HashMap<KEY,VALUE> storage = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello World");

        LRUCache<String, String> lruCache = new LRUCache(10);
        lruCache.put("Bhalchandra", "Kadam");

        System.out.println(lruCache);
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public void put(KEY key, VALUE value){
        storage.put(key, value);
    }

    public VALUE get(KEY key){
        return storage.get(key);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LRUCache{\n");
        sb.append("capacity=").append(capacity);
        for(var pair: storage.entrySet()){
            sb.append("\n  ");
            sb.append(pair.getKey()).append("=").append(pair.getValue());
        }

        sb.append("\n}");
         
         return sb.toString();
    }
}