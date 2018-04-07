
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