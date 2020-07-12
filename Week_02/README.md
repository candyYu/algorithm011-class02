#week02学习笔记
# 哈希、树、图、堆
## 哈希函数
* O(1)的时间复杂度，一般选择好的hash函数不太会有冲突
* Java hashMap源码解析 解读
* Java  Set 和Map接口提供的方法 和基本使用
java Set 接口中的方法 继承类 HashSet  TreeSet
```
bollean add(E e);
bollean addAll(Collection <? extends E> c);
void  clear();
bollean contians(E e);
bollean containsAll(Collection <?> c);
bollean isEmpty();
int  size();
```

java Map接口中的方法 继承实现类 HashMap HashTable TreeMap ConcurrentHashMap

```
void clear();
bollean contiansKey(Object key);
bollean contiansValue(Object value);
V   get(Object key);
default V   getOrDefault(Object key,V defaultValue);
bollean isEmpty();
V	put (K key, V value)
V	remove (Object key)
default V	replace (K key, V value)

```

* TreeMap等基本特性


* java hashMap实现分析
1. java8之前，通过数组指针+链表实现，java8之后做了改进,解决了循环引用问题，链表节点数大于8，会改成通过红黑树实现
2.






## 树
*二叉搜索树  概念 中序遍历时递增的，空树也是树



## 堆
* 堆的实现方式有多种  包括二叉堆 、fibonacii堆
* 二叉堆 可以用数组实现，插入和删除都是O(logN) 取元素O(1)
 二叉堆的性质：
1. 是一个完全二叉树，完全二叉树是指除了最底层的叶子节点，其他根节点的叶子节点都是满的
2. 大顶堆的话  树的任意节点的值> 子节点的值
根据二叉堆的性质，一般用数组实现 java中可以直接用PriorityQueue

堆可以用数组实现，该数组从逻辑上讲就是一个堆结构，我们用简单的公式来描述一下堆的定义就是：
大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]


* 经典算法
二叉堆实现


二叉堆的使用场景（求去Top K问题，通过建立1个大顶堆存储小的元素，1个小顶堆存储较大元素来实现中位数）

堆排序Heap Sort
堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。堆排序可以说是一种利用堆的概念来排序的选择排序。分为两种方法：
大顶堆：每个节点的值都大于或等于其子节点的值，在堆排序算法中用于升序排列；
小顶堆：每个节点的值都小于或等于其子节点的值，在堆排序算法中用于降序排列；
堆排序的平均时间复杂度为 Ο(nlogn)。

算法步骤
1. 创建一个堆 H[0……n-1]；
2. 把堆首（最大值）和堆尾互换；
3. 把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
4. 重复步骤 2，直到堆的尺寸为 1

java 代码实现
```
// Java program for implementation of Heap Sort
public class HeapSort
{
	public void sort(int arr[])
	{
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

		// One by one extract an element from heap
		for (int i=n-1; i>0; i--)
		{
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	void heapify(int arr[], int n, int i)
	{
		int largest = i; // Initialize largest as root
		int l = 2*i + 1; // left = 2*i + 1
		int r = 2*i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// If largest is not root
		if (largest != i)
		{
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[])
	{
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i]+" ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[])
	{
		int arr[] = {12, 11, 13, 5, 6, 7};
		int n = arr.length;

		HeapSort ob = new HeapSort();
		ob.sort(arr);

		System.out.println("Sorted array is");
		printArray(arr);
	}
}


```




## 图
图的表示法
Graph(V,E) Vertex edge 节点和边
V - Vertex 点
1.点入度和出度
2.点与点是否连通

E - Edge 边
1.有向 无向 连接线
2.边长 权重

图的表示
1. 邻接矩阵
2. 邻接链表

图的算法，伪代码
dfs
```
visited = set() # 和树中的DFS最大区别

def dfs():
   if node in visited: #terminator
      #already visited
      return
    visited.add(node)

    # process current node here
    ...
    for next_node in node.children():
       if not next_node in visited:
          dfs(next_node,visited)


```

bfs

```
def bfs(grpah,start,end):
    queue = []
    queue.append([start])
    visited = set() # 和树中的BFS的最大区别

    while queue:
        node = queue.pop()
        visited.add(node)

        process(node)
        nodes = generate_related_nodes(node)
        queue.push(nodes)

```
