# 学习笔记week01
***这周主要学习了链表，数组，栈和队列等常见的数据结构。
数组和链表是对应存储结构，而栈和队列是个受限性表，可以有数组和链表实现，有着各自的使用场景，比如栈可以实现浏览器的前进后退等。
链表和搭配hash可以实现时间复杂度O(1)的LRU Cache***

## 数组
* 一段连续的存储空间，随机访问时间复杂度O(1)
* 插入和删除，需要移动数组中其他数据，时间复杂度为O(n)
* java中数组实现 ArrayList 基本数据类型 int a[100] 插入和删除数据时如果存储空间不够，有动态拓容，插入和删除频繁时很耗时，不推荐

### LRU Cache
这边特别记录下LRU Cache的实现方法，包括java中原始实现和自己实现两种
* 使用java封装好的数据结构 LinkedHashMap
```
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}
```
* 使用双向链表和hashmap

***在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。这是个做链表时减少临界判断的一个常规操作，需要熟记。***
```
class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(){}
        public Node(int _key,int _value) {
            this.key = _key;
            this.value = _value;
        }
    }

    private int size;
    private int capacity;
    private Node head;
    private Node tail;
    private Map<Integer,Node> cache = new HashMap();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head =new Node();
        tail=new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        //判断缓存中是否有元素，没有直接返回-1
        if(!cache.containsKey(key)){
            return -1;
        }
        Node node = cache.get(key);
        //把节点删除，然后添加进头部
        remove(node);
        addToHead(node);
        return node.value;
    }

    private void remove(Node node) {
        //删除node节点的操作方式
        size--;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private int removeTail(){
        size--;
        int tailKey = this.tail.prev.key;
        this.tail.prev.prev.next = this.tail;
        this.tail.prev =  this.tail.prev.prev;
        return tailKey;
    }

    private void addToHead(Node node) {
        size++;
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
    
    public void put(int key, int value) {
        //判断原来你是否有key对应的节点
        Node node = null;
        if(!cache.containsKey(key)) {
             node = new Node(key,value);
             addToHead(node);
             cache.put(key,node);
             //判断数量是否超过最大大小，是的话删除尾节点
             if(size>capacity) {
                int tailKey = removeTail();
                cache.remove(tailKey);
             }
        }else{
             node = cache.get(key);
             //判断value节点是否改变，如果改变，修改value
             if(node.value!=value){
                node.value = value;
             }
             //删除节点
             remove(node);
             //添加到头节点
             addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

```
官方示例代码，比我写的更简洁些
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```


## 链表
* 零散的内存空间，对比数组来说会多个内存指针，查找时间复杂度O(n)
* 插入删除时间复杂度O(1)
* 变种（双向链表（除后驱节点外，有前驱节点），循环链表（尾指针指向头指针）
* java 实现 LinkedList 是个双向链表

## 跳表

实现代码
```

```

## 栈
* 先进后出 插入删除O(1)复杂度
* 双端队列
* java现实中使用栈结构，更推荐
```
 Deque<Integer> stack = new ArrayDeque<Integer>();
```



## 队列
* 先进先出 插入和删除O(1)复杂度
* 优先队列 插入O(1)  取出O(logN) 按照元素的优先级取出
* 优先队列底层实现比较复杂和多样，如堆 heap bst treap
* 解法常见套路，两个队列或两个栈

### 改写Deque作业
```
  Deque<String> deque = new LinkedList<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        System.out.println(deque);

        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size()>0){
            System.out.println(deque.pop());
        }
        System.out.println(deque);
```

### Queue和PriorityQueue源码分析（java)
 
 Queue在java中是个接口定义，它只定义了队列的先入先出特性对应的接口，add() remove() element()  offer() poll()  peek() 区别是前三个在越界条件下抛出异常 ，后三个返回特定的值,基于此的实现有很多，如
   * @see LinkedList
   * @see PriorityQueue
   * @see java.util.concurrent.LinkedBlockingQueue
   * @see java.util.concurrent.BlockingQueue
   * @see java.util.concurrent.ArrayBlockingQueue
   * @see java.util.concurrent.LinkedBlockingQueue
  * @see java.util.concurrent.PriorityBlockingQueue
 
 Queue 也是 Java 集合框架中定义的一种接口，直接继承自 Collection 接口。除了基本的 Collection 接口规定测操作外，Queue 接口还定义一组针对队列的特殊操作。通常来说，Queue 是按照先进先出(FIFO)的方式来管理其中的元素的，但是优先队列是一个例外。

Deque 接口继承自 Queue接口，但 Deque 支持同时从两端添加或移除元素，因此又被成为双端队列。鉴于此，Deque 接口的实现可以被当作 FIFO队列使用，也可以当作LIFO队列（栈）来使用。官方也是推荐使用 Deque 的实现来替代 Stack。

ArrayDeque 是 Deque 接口的一种具体实现，是依赖于可变数组来实现的。ArrayDeque 没有容量限制，可根据需求自动进行扩容。ArrayDeque不支持值为 null 的元素。

[ArrayDeque源码分析详见此博客](https://www.cnblogs.com/wxd0108/p/7366234.html)


PriorityQueue
看了java8 中优先级队列的实现是采用二插堆， 对于堆中每个父节点都比子节点要小，设父节点索引为k 子节点为2K+1 和 2k+2;

插入,插入到数组头部，根据需要开始进行siftUp重新建立最小堆
```
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }
    
    private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }
```

siftUpUsingComparator使用自定义比较器，siftUpComparable使用x实现的比较器（因此泛型对象必须实现比较器）
实现方式：最小堆
最小堆定义：
1、完全二叉树
2、任意节点的值小于等于左右孩子的值
3、任意非叶子节点的左右子树也是堆

具体升堆方法
```
@SuppressWarnings("unchecked")
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

```