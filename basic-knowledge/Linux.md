1. 目录:
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
2. tty: 查看当前终端
3. export -p: 列出所有环境变量
4. history n: 打印最近n条历史命令
5. shutdown ... TIME(分钟)   (==halt  init 0)
   - -r 重启
   - -c 取消关机
6. ls 
   - -R: 递归显示目录文件
   - -l: 显示目录详细属性
   - -ld: 显示指定目录的详细属性
7. sort： 排序
   - -n 数字
   - -r 倒序
   - -f 忽略大小写
   - -u 去除重复项
8. uniq: 去除连续的重复行
9. grep: ```grep -n 'a' a.txt```
   - -n: 显示行号
   - ​