package arithmatic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenzhuojun
 * @version 1.0 2022/10/8 5:51 下午
 * @Description https://blog.csdn.net/m0_52517879/article/details/123829258
 */
public class LRU<K, V> {

    // 只用链表的话，查找元素节点的时间复杂度为 O(n) ，引入散列表，讲复杂度降为 O(1)
    private final Map<K, Node<K, V>> cache = new HashMap<>();
    // 元素节点数量
    private int size = 0;
    // 容量
    private int capacity = 16;
    // 近期使用的元素节点，放在链表头部
    private Node<K, V> head;
    // 要过期的元素节点，放在链表尾部
    private Node<K, V> tail;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public void setHead(Node<K, V> head) {
        this.head = head;
    }

    public Node<K, V> getTail() {
        return tail;
    }

    public void setTail(Node<K, V> tail) {
        this.tail = tail;
    }

    /**
     * 往链表中加节点 直接加在链表头
     *
     * @param node
     */
    public void addNode(Node<K, V> node) {
        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.head.setPreNode(node);
            node.setPreNode(null);
            node.setNextNode(this.head);
            this.head = node;
        }
        this.size++;
    }

    /**
     * 刚使用的节点 提至链表头
     *
     * @param node
     */
    public void moveToHead(Node<K, V> node) {
        // 如果原来就是链表头 则不需要处理
        if (this.head == node) {
            return;
        }
        if (this.size == 1) {
            return;
        }
        // 原来是 tail 需要重新指定 tail
        if (this.tail == node) {
            this.tail = node.getPreNode();
        }
        // 删除节点
        node.getPreNode().setNextNode(node.getNextNode());
        if (node.getNextNode() != null) {
            node.getNextNode().setPreNode(node.getPreNode());
        }
        // 移到队头
        node.setPreNode(null);
        node.setNextNode(this.head);
        this.head.setPreNode(node);
        this.head = node;
    }

    /**
     * 移除尾部节点
     *
     * @return
     */
    public Node<K, V> removeTail() {
        Node<K, V> node;
        if (this.size == 1) {
            node = tail;
            this.head = null;
            this.tail = null;
        } else {
            node = this.tail;
            node.getPreNode().setNextNode(null);
            node.setPreNode(null);
            this.tail = node.getPreNode();
        }
        this.size--;
        return node;
    }

    public void put(K key, V val) {
        Node<K, V> node_ = this.cache.get(key);
        if (node_ != null) {
            node_.setVal(val);
            moveToHead(node_);
        } else {
            Node<K, V> node = new Node<>(key, val);
            addNode(node);
            this.cache.put(node.getKey(), node);
        }
        if (this.capacity < this.size) {
            Node<K, V> remove = removeTail();
            this.cache.remove(remove.getKey());
            this.size--;
        }
    }

    public V get(K key) {
        if (!this.cache.containsKey(key)) {
            return null;
        }
        Node<K, V> node = this.cache.get(key);
        moveToHead(node);
        return node.getVal();
    }

    public void print() {
        this.cache.forEach((k, v) -> {
            System.out.println(k + " : " + v.getVal());
        });
    }

    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>();
        lru.setCapacity(5);
        lru.put("a", 1);
        lru.put("b", 2);
        lru.put("c", 3);
        lru.put("d", 4);
        lru.put("e", 5);
        Integer a = lru.get("a");
        System.out.println(a);
        lru.put("f", 6);
        lru.print();
    }
}

/**
 * 节点类
 *
 * @param <K>
 * @param <V>
 */
class Node<K, V> {

    private K key;
    private V val;
    private Node<K, V> nextNode;
    private Node<K, V> preNode;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public Node<K, V> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<K, V> getPreNode() {
        return preNode;
    }

    public void setPreNode(Node<K, V> preNode) {
        this.preNode = preNode;
    }
}

