import java.util.HashMap;
import java.util.Map;

class DLNode {
    int key;
    int value;
    DLNode prev;
    DLNode next;
}

public class LRUCache {
    int capacity;
    Map<Integer, DLNode> cacheMap;
    DLNode head;
    DLNode tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new DLNode();
        this.tail = new DLNode();
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }

    public static void main(String[] args) {
        int capacity = 2;
        LRUCache cache = new LRUCache(capacity);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

    private void get(int key) {
        DLNode node = cacheMap.get(key);
        if (node == null) {
            System.out.println("-1");
        } else {
            moveNodeToHead(node);
            System.out.println(node.value);
        }
    }

    private void moveNodeToHead(DLNode node) {
        removeNode(node);
        addToHead(node);
    }

    //adds just after head
    private void addToHead(DLNode node) {
        node.prev = head;
        node. next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLNode node) {
        DLNode post = node.next;
        DLNode pre = node. prev;
        post.prev = pre;
        pre.next = post;
    }

    private void put(int key, int value) {
        DLNode node = cacheMap.get(key);
        if (node == null) {
            int count = cacheMap.size();
            DLNode node1 = new DLNode();
            node1.key = key;
            node1.value = value;
            addToHead(node1);
            cacheMap.put(key, node1);
            if (count == capacity) {
                DLNode node2 = popTail();
                System.out.println("Evicted "+node2.key);
                cacheMap.remove(node2.key);
            }
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    private DLNode popTail() {
        DLNode node = tail.prev;
        removeNode(node);
        return node;
    }

}