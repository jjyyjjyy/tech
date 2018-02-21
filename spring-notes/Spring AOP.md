#### Spring AOP

1. pointcut
   - 匹配方法
     - execution
   - 匹配注解
     - @target: RetentionPolicy为class
     - @args
     - @within: RetentionPolicy为runtime
     - @annotation
   - 匹配包/类型
     - within
   - 匹配对象
     - this
     - bean
     - target
   - 匹配参数
     - args