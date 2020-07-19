# DFS和BFS
深度优先搜索和广度优先搜索都有些常见的代码模板

DFS(递归写法)

```
#Python
visited = set()

def dfs(node, visited):
        if node in visited: # terminator
        	# already visited
    	    return

	visited.add(node)

	# process current node here.
	...
	for next_node in node.children():
		if next_node not in visited:
			dfs(next_node, visited)

```

DFS (迭代写法)

```
#Python
def DFS(self, tree):

	if tree.root is None:
		return []

	visited, stack = [], [tree.root]

	while stack:
		node = stack.pop()
		visited.add(node)

		process (node)
		nodes = generate_related_nodes(node)
		stack.push(nodes)

	# other processing work
	...
```

BFS

```
# Python
def BFS(graph, start, end):
    visited = set()
	queue = []
	queue.append([start])
	while queue:
		node = queue.pop()
		visited.add(node)
		process(node)
		nodes = generate_related_nodes(node)
		queue.push(nodes)
	# other processing work
	...
```
# 贪心算法

贪心算法是一种在每一步选择中都采取在当前状态下最好或最优(即最有
利)的选择，从而希望导致结果是全局最好或最优的算法。

贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不
能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行
选择，有回退功能。

贪心法可以解决一些最优化问题，如:求图中的最小生成树、求哈夫曼编码
等。然而对于工程和生活中的问题，贪心法一般不能得到我们所要求的答
案。
一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最
好办法。由于贪心法的高效性以及其所求得的答案比较接近最优结果，贪心
法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。


## 贪心算法适用场景
1. 具有最优子结构
  问题能够分解成子问题来解决，子问题的最优解能递推到最终
问题的最优解。这种子问题最优解称为最优子结构。
2. 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，
不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前
进行选择，有回退功能。


# 二分查找

二分查找模板框架

```
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}
```
这里分享一篇优秀的 [二分查找优秀总结](https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E8%AF%A6%E8%A7%A3.md)

查找旋转数组中最小值方法

```
public int findMin(int[] nums) {
          int lo=0,hi=nums.length-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(nums[mid]>nums[hi]) lo=mid+1;
            else hi=mid;
        }
        return nums[lo];
    }
```
