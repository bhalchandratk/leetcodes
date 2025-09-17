 package problems;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Comparator;
/**
 * LRUCache implementation in Java.
 * This is a basic implementation that does not yet handle eviction of least recently used items.
 * It currently only supports put and get operations.
 */
public class LRUCache<KEY,VALUE> {

    static class Node<Value>{
        private Value value;
        private LocalDateTime timestamp;

        public Node(Value value) {
            this.value = value;
            this.timestamp = LocalDateTime.now();
        }

        public Value getValue(){
            // Update the timestamp to mark this node as recently used
            this.timestamp = LocalDateTime.now();
            toString();
            return value;
        }

        @Override
        public String toString() {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
            return "Node{" + "value=" + value +  ", timestamp=" + timestamp.format(formatter) + "}";
        }
    }

    private int capacity;
    private HashMap<KEY,Node<VALUE>> storage = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello World");

        LRUCache<String, String> lruCache = new LRUCache(5);
        try{
            lruCache.put("Lonavala", "50km");
            // Thread.sleep(1000);
            lruCache.put("Mulshi", "70km");
            // Thread.sleep(1000);
            lruCache.put("Mahabaleshwar", "170km");
            // Thread.sleep(1000);
            lruCache.put("Wai", "140km");
            // Thread.sleep(1000);
            lruCache.put("Ozar", "180km");

            System.out.println(lruCache);
            // Thread.sleep(1000);
            System.out.println("Get Lonavala: " + lruCache.get("Lonavala"));
            
            System.out.println("Get Mulshi: " + lruCache.get("Mulshi"));
            
            lruCache.put("Panchgani", "160km");
            System.out.println("After adding Panchgani: " + lruCache);Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    
        
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public void put(KEY key, VALUE value){
        if(storage.size() >= capacity){
            evit();
        }

        storage.put(key, new Node<>(value));
    }

    private void evit() {
         
         var evitEntry = storage.entrySet().stream().min(Comparator.comparing(entry -> entry.getValue().timestamp));
         System.out.println("Evicting: " + evitEntry);
         storage.remove(evitEntry.get().getKey());
    }

    public VALUE get(KEY key){
        return storage.get(key).getValue();
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