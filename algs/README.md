#### List:
  - ArrayList
  - LinkedList
  - ArrayQueue
  - Stack
  - CopyOnWriteArrayList
  - Vector
#### Set:
  - ConcurrentSkipListSet
  - CopyOnWriteArraySet
  - HashSet
  - LinkedHashSet
  - TreeSet
#### Map:
  - HashMap
  - LinkedHashMap
  - TreeMap
  - IdentityHashMap
  - WeakHashMap
  - ConcurrentHashMap
  - ConcurrentSkipListMap
  - HashTable
#### Queue:
  - ArrayBlockingQueue
  - ArrayDeque
  - DelayQueue
  - LinkedTransferQueue
  - PriorityQueue
  - PriorityBlockingQueue
  - SynchronousQueue
  - [Concurrent]LinkedQueue
  - [Concurrent]LinkedDeque
  
### Reading Notes:
1. Itr中exceptedModCount值为AbstractList中的modCount值,List每次操作modCount都会加1,如果Itr#next/remove判断modCount和exceptedModCount不相等则会抛出异常.
但如果List修改过后size为0则hasNext循环退出,不会抛出ConcurrentModificationException
2. 每次Itr#remove后lastRet重置为-1,而每次调用lastRet是都会判断是否为负->调用remove前要先调用next