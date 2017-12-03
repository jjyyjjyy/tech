### 注解
1. 重复注解使用

   @Repeatable(xxx.class) & getAnnotationsByType(xxx.class);

2. Retention

   |         |  源码  | 字节码文件 | 运行时获取 |
   | :-----: | :--: | :---: | :---: |
   | Source  |  √   |       |       |
   |  Class  |  √   |   √   |       |
   | Runtime |  √   |   √   |   √   |

3. Target

   - Type: 类、接口、注解、枚举
   - Field: 字段
   - Method: 方法
   - Parameter: 方法参数
   - Constructor: 构造函数
   - Local_Variable: 局部变量
   - Annotation_Type: 只能用在注解上
   - Package: 包声明上
   - Type_Parameter: 类参数, 尚未参透
   - Type_Use: 用在任何声明&定义的类型处

### 枚举

1. API
   1. values() 返回所有枚举常量
   2. valueOf(String) 返回名字对应的枚举常量
   3. name() 返回枚举常量名字
   4. oridinal() 返回枚举常量的序号
   5. compareTo() 比较枚举常量的序号
2. EnumMap, EnumSet
   1. EnumSet.allOf(Enum.class) 返回包含所有枚举常量的集合
   2. EnumSet.rangOf(EnumA,EnumB) 返回A与B之间所有的枚举常量, 包括边界
   3. EnumMap#entrySet 为空,forearch用不了, 只能手动put   ==to fix==

### 序列化

1. 序列化时会调用readObject方法,反序列化时会调用writeObject方法
2. serialVersionUID标识类的序列化的版本号,如果反序列化时当前类的版本号与序列化的二进制流对象的版本号不一致则会抛出异常
3. 静态内部类序列化再反序列化后字段值丢失   ==to fix==


### 接口

1. default方法继承判定
   - Class优先
   - 子接口优先
   - 无法确定需用 类名.super 显示调用
2. Class#getDeclaredMethods不计算继承的方法

### 泛型

1. 上界通配符只能get
2. 下界通配符get出Object,add时不能add声明的泛型边界的父类
3. 取不到一个类定义的泛型类型(T),只能取其子类或父类具体的泛型类型(Long,String...)

### 线程

1. 线程状态
   - NEW
   - RUNNABLE
   - BLOCKED
   - WAITING
   - TIMED_WAITING
   - TERMINATED
2. 线程内异常无法在外层try-catch,只能设置Thread的UncaughtExceptionHandler
3. 每一条线程都有自己的栈空间,拥有一份方法参数、局部变量和返回值的拷贝.每一个线程都有自己的一份标识信息,包括线程名、线程优先级、线程是否存活、线程执行状态、守护线程标识等.
4. wait()释放对象锁,sleep()不释放.