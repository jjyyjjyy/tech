
- 普通命令可执行文件:
  - /bin
  - /usr/bin
  - /usr/local/bin
- 管理命令可执行文件:
  - /sbin
  - /usr/sbin
  - /usr/local/sbin
- 32位库:
  - /lib
  - usr/lib
  - usr/local/lib
- 64位库:
  - /lib64
  - /usr/lib64
  - /usr/local/lib64

```tty```: 查看当前终端

 终端类型:

- 物理终端: console
- 虚拟终端: tty
- 图形终端
- 串行终端: ttyS
- 伪终端: pty

终端符号:

- \# root
- $ 普通用户

目录: /PATH/TO/FILE

- basename: FILE
- dirname: /PATH/TO
命令
1. export -p: 列出所有环境变量

2. history n: 打印最近n条历史命令

3. shutdown ... TIME(分钟)   (==halt  init 0)
   - -r 重启
   - -c 取消关机

4. ls 

   - -a 显示所有文件,包括隐藏文件
   - -A: 除. .. 外所有文件


   - -R: 递归显示目录文件
   - -l: 显示目录详细属性
   - -ld: 显示指定目录的详细属性
   - -r: 逆序显示

5. cat: 查看文件内容

   - -n: 显示行号
   - -E: 显示行结束符
   - ​

6. sort： 排序
   - -n 数字
   - -r 倒序
   - -f 忽略大小写
   - -u 去除重复项

7. uniq: 去除连续的重复行

8. grep: ```grep -n 'a' a.txt```
   - -n: 显示行号

9. 列出服务: ss -tnl

POSIX:

POSIX采用信号量、共享内存、消息队列 进行线程间通信

内核的职责:

1. 进程管理: 抢占式任务调度
2. 内存管理: 虚拟内存管理机制
   - 进程与进程之间彼此隔离, 一个进程无法直接读取或修改其他进程的内容
   - 只将进程的一部分保持在内存中
3. 文件系统
4. 设备访问(I/O)
5. 网络管理
6. 提供API



进程的内存布局:

-   文本
-   数据
-   堆
-   栈

每一个进程都有一个唯一的整数型进程标识符PID和父进程标识符PPID

进程间通信方式:

-   信号
-   管道
-   套接字
-   文件锁定
-   消息队列
-   信号量
-   共享内存

