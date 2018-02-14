### Spring MVC处理流程

#### 1. 初始化组件(第一次收到请求时):

1. HttpServletBean#init
2. FrameworkServlet#initWebApplicationContext:refresh context并设置dispatcherServlet的组件

### 2. 处理请求:
1. FrameworkServlet#service: 添加patch请求方法支持
2. FrameworkServlet#processRequest:
    - 设置LocaleContext和ServletRequestAttributes
    - 调用doService处理请求
    - 发布RequestHandlerEvent(处理有异常则不发,如果处理成功但视图渲染失败则照样发)
3. DispatcherServlet#doService:
    - 设置SpringMVC组件到request属性中:
        - ApplicationContext
        - LocaleResolver
        - ThemeResolver
        - ThemeSource
        - FlashMap
        - FlashManager
    - doDispatch
3. DispatcherServlet#doDispatch:
    - 包装上传文件request
    - 根据request从HandlerMapping中找到对应的HandlerExecutionChain
    - 根据Handler找到HandlerAdapter
    - 检查get请求的Last-Modified头是否不过期
    - 调用HandlerExecutionChain中的interceptors#preHandle
    - 调用HandlerAdapter#handle
    - 渲染视图
    - 调用HandlerExecutionChain中的interceptors#postHandle.如果出异常了则调用ExceptionResolver,如果ExceptionResolver解析抛异常则调用HandlerExecutionChain中的interceptors#afterCompletion
