###  Spring Boot v2.0.0.RC1 启动流程

#### 1. 初始化SpringApplication:

1. SpringApplication.run(MainClass.class,args)。
2. 构造SpringApplication实例:
   - 确定ApplicationType: None-Web/Servlet/Reactive。
   - 获取META-INF/spring.factories中 ```ApplicationContextInitializer```并设置到成员变量中。
   - 获取META-INF/spring.factories中 ```ApplicationListener```并设置到成员变量中。
   - 设置启动类class属性。
3. SpringApplication#run。

#### 2. 获取配置:

1. 从META-INF/spring.factories中获取SpringApplicationRunListener(EventPublishRunListener)。

2. 发布**ApplicationStartingEvent**:

   - LoggingApplicationListener: 设置log system,默认logback。
   - BackgroundPreInitializer: 触发内置bean的构造函数。

3. 创建Environment对象(StandardServletEnvironment):

   - 添加系统属性:
     - springApplicationCommandLineArgs(启动命令行参数)
     - servletConfigInitParams
     - servletContextInitParams
     - systemProperties
     - systemEnvironment
     - defaultProperties(SpringApplication#defaultProperties)


   - 根据命令行参数设置active profiles

4. 发布**ApplicationEnvironmentPreparedEvent**:

   - ConfigFileApplicationListener: 调用EnvironmentPostProcessor#postProcessEnvironment。

     - SystemEnvironmentPropertySourceEnvironmentPostProcessor:

       ```把systemEnvironment包装成OriginAwareSystemEnvironmentPropertySource.```

     - SpringApplicationJsonEnvironmentPostProcessor:

       ```解析spring.application.json中的配置, 比systemProperties优先级高。```

     - CloudFoundaryVcapEnvironmentPostProcessor:

       ```设置vcap属性配置。```

     - ConfigFileApplicationListener:

       ```从META-INF/spring.factories中获取ProperySourceLoader(properties/xml/yaml)并读取配置文件。Environment#propertySources中active profile在前,default profile在最后。```

   - AnsiOutputApplicationListener: 设置 ```spring.output.ansi.enabled``` 和 ```spring.output.ansi.console-available```。

   - LoggingApplicationListener: 初始化Log properties/system。

   - ClassPathLoggingApplicationListener: 打印classpath日志。

   - DelegatingApplicationListener: 调用 ```context.listener.classes``` 中listener。

   - FileEncodingApplicationListener。

5. 包装Environment的propertySources为ConfigurationPropertySourcesPropertySource。

6. 打印banner日志。

#### 3. 创建ApplicationContext:

1. 根据ApplicationType创建对应的context:

   - Servlet: ```AnnotationConfigServletWebServerApplicationContext```
   - Reactive: ```AnnotationConfigReactiveWebServerApplicationContext```
   - None-Web: ```AnnotationConfigApplicationContext```

2. BeanUtils#instantiateClass。

3. 初始化AnnotatedBeanDefinitionReader:

   1. 设置BeanFactory属性:
      - AnnotationAwareOrderComparator
      - ContextAnnotationAutowireCandidateResolver
   2. 注册spring内置BeanFactoryPostProcessor:
      - ConfigurationClassPostProcessor
      - AutowiredAnnotationBeanPostProcessor
      - RequiredAnnoationBeanPostProcessor
      - CommonAnnotationBeanPostProcessor
      - PersistenceAnnotationBeanPostProcessor
      - EventListenerMethodProcessor
      - DefaultEventListenerFactory

4. 初始化ClassPathBeanDefinitionReader:

   ```将@Component @Named @ManagedBean识别为bean.```

5. 调用ApplicationContextInitializer#initialize:

   - DelegatingApplicationContextInitializer: 调用 ```context.initializer.classes```#initialize。
   - ContextIdApplicationContextInitializer: 设置ApplicationContext的Id为 ```spring.application.name || "application"```。
   - ConfigurationWarningsApplicationContextInitializer: 注册 ```ConfigurationWarningsPostProcessor.```
   - ServerPortInfoApplicationContextInitializer: 将自己添加到context的ApplicationListener中。
   - SharedMetadataReaderFactoryContextInitializer: 注册```CachingMetadataReaderFactoryPostProcessor.```
   - ConditionEvalutionReportLoggingListener: 添加 ```ConfidtionEvalutionReportListener ```。

6. 打印启动日志。

7. 注册启动类BeanDefinition到BeanFactory中。

8. 将SpringApplication中的listener添加到ApplicationContext中。

9. 发布**ApplicationPreparedEvent:**

   - ConfigFileApplicationListener: 注册 ```PropertySourceOrderingPostProcessor.```
   - LoggingApplicationListener: 注册 ```LoggingSystem.```

#### 4. Refresh Context: