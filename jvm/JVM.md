1. 数据类型：
   - 原始类型：
     - 数值类型
     - boolean
     - returnAddress(x)
2. 运行时数据区
   - 每条java虚拟机线程都有自己的PC寄存器。方法为native?undefined:字节码指令地址.
   - 每条java虚拟机线程都有自己的虚拟机栈,存储栈帧(local variable table,operand stack...)
   - 堆，可供各个线程共享的运行时内存区域.
   - 方法区:存储类结构信息,如 runtime constant pool , field sigament, method,constructor...
   - 本地方法区
3. 方法调用指令
   - invokevirtual:调用对象的实例方法
   - invokeinterface:调用接口方法
   - invokespecial:调用实例初始化方法、私有方法、父类方法
   - invokestatic:调用static方法
   - invokedynamic://TODO
4. 下载openjdk源码
   - sudo apt install mercurial
   - vim ~/.hgrc
   -     [http_proxy]
         host=127.0.0.1:1080
   - hg clone http://hg.openjdk.java.net/jdk8u/jdk8u/
   - cd jdk8u/ && chmod +x get_source.sh
   - LANG=C
   - ./get_source.sh
5. 垃圾收集器:
   - Serial(单线程)
     使用单线程清理堆的内容，无论是Minor GC或者是Full GC都会STW.进行Full GC时会对老年代进行压缩整理．
   - Throughput(Parallel)(64位JVM默认)
     多线程版本的Serial
   - CMS(Concurrent)    -XX:+UserConcMarkSweepGC  -XX:+UseParNewGC
     CMS在回收新生代时会STW,但使用新的算法多线程回收。但是Full GC时不会STW,而是使用若干个后台线程对老年代扫描并回收(不进行压缩整理).付出高CPU使用率! 如果堆变得过度碎片化,就会发生STW,使用单线程回收整理老年代 
   - G1
     G1收集算法将堆划分成若干个区域。多线程回收新生代,老年代由后台线程完成.老年代回收时将对象从一个区域复制到另外一个区域实现堆的压缩整理.
   并行GC线程数: 8+(N-8) * 5/8
   打印GC日志: -XX:+PrintGCDetails
   cmd查看GC: jstat -gcutil PROCESS_ID  INTERVAL
   
6. GC:

- Minor GC: 新生代填满时，垃圾收集器会暂停所有的线程，回收新生代。
  优势:
  - 新生代仅仅是堆的一部分，回收新生代比回收整个堆停顿的时间更短。
  - 对象首先分配在新生代的Eden区，在GC时对象要么被移走，要么被回收。所有存活的对象回收到另外一个Survivor区或者老年代相当于GC时自动进行了一次压缩整理。
- Full GC: 回收老年代中不再使用的对象。

7. 默认堆大小

         OS        	   初始堆大小(-Xms)    	  最大堆大小(-Xmx)   
   Linux 32位Client 	       16M        	      256M      
   Linux 32位Server 	       64M        	min(1G,1/4物理内存) 
   Linux 64位Server 	min(512M,1/64物理内存)	min(32G,1/4物理内存)
     Mac OS 64位    	       64M        	min(1G,1/4物理内存) 
  WIndows 32位Client	       16M        	      256M      
  Windows 64位Server	       64M        	min(1G,1/4物理内存) 

8. 远程debug参数
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
