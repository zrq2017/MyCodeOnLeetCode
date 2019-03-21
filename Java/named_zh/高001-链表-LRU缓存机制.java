/*****
LRU缓存机制

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:

LRUCache cache = new LRUCache( 2 缓存容量);

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
**/
class LRUCache {
    /**
    * 使用map存储键值，使用链表的顺序来表示LRU
    * 链表的头表示最近一次访问的，链表的尾表示最久之前访问的
    * 1.新加入的数据
            ————> *** 头 <—————
                  ***          |
                  ***          |2.被访问的数据
                  ***     —————
                  ***
                  ***
            <———— *** 尾
        3.淘汰的数据
    **/
    public static class CacheNode {
        int key;
        int value;
        CacheNode pre;
        CacheNode next;

        public CacheNode(int key, int value, CacheNode pre, CacheNode next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    private int capacity;

    private Map<Integer, CacheNode> cacheKeyMap;

    private CacheNode head;

    private CacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheKeyMap = new HashMap<>(capacity);
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        CacheNode cacheNode = cacheKeyMap.get(key);
        if (cacheNode == null) {
            return -1;
        }
        touch(cacheNode);
        return cacheNode.value;
    }

    // touch to head
    private void touch(CacheNode cacheNode) {
        if (head != cacheNode) {
            cacheNode.pre.next = cacheNode.next;
            if (cacheNode.next != null) {
                cacheNode.next.pre = cacheNode.pre;
            } else {
                // cacheNode is tail
                tail = cacheNode.pre;
            }
            head.pre = cacheNode;
            cacheNode.pre = null;
            cacheNode.next = head;
            head = cacheNode;
        }
    }

    public void put(int key, int value) {
        CacheNode cacheNode = cacheKeyMap.get(key);
        // not cached
        if (cacheNode == null) {
            cacheNode = new CacheNode(key, value, null, null);
            // first add
            if (head == null) {
                head = cacheNode;
                tail = cacheNode;
                cacheKeyMap.put(key, cacheNode);
                return;
            }
            // add to head
            cacheNode.next = head;
            head.pre = cacheNode;
            head = cacheNode;
            cacheKeyMap.put(key, cacheNode);
            // expire tail if need
            if (cacheKeyMap.size() > capacity) {
                cacheKeyMap.remove(tail.key);
                CacheNode tailPre = tail.pre;
                tail.pre = null;
                tailPre.next = null;
                tail = tailPre;
            }
        } else {
            touch(cacheNode);
            cacheNode.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 /**
 *思想同上，但是超时，比上面的容易理解
 **/
 class LRUCache {
    private Map<Integer,Integer>  map;
    private LinkedList<Integer>   list;
    private int                   size;

    public LRUCache(int capacity) {
        size  = capacity;
        map   = new HashMap<>(capacity);
        list  = new LinkedList<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            lru(key);
            return map.get(key);
        }
        else{
            return -1;
        }
    }

    public void put(int key, int value) {
        lru(key);
        map.put(key,value);
    }

    public void lru(int key){
        // 命中，刷新缓存
        if(list.contains(key)){
            // 重置命中节点
            Iterator<Integer> iterator = list.iterator();
            while(iterator.hasNext()){
                int val = iterator.next();
                if (val == key){
                    iterator.remove();
                }
            }
        }
        // 未命中，插入缓存
        else{
            // 缓存已满，删除尾节点
            if(list.size() == size){
                map.remove(list.removeLast());
            }
        }
        // 将新节点插入到链表头
        list.addFirst(key);
    }
}