# demo-microservice



# spring boot 配置

## 一、spring boot 介绍

> 以最少的依赖和配置来启动spring配置的应用程序
系统需求：java7 推荐 java8

------------------


name | servlet version | java version |
---|---|---
tomcat 8 | 3.1 | java7+
Jetty 9.3| 3.1 | java8+

## 二、Install
#### > maven 配置 下面的配置适合使用默认配置


```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
<version>1.4.0.BUILD-SNAPSHOT</version>
</parent>
<!-- Add typical dependencies for a web application -->
<dependencies>
    <dependency>
    <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
<!-- Package as an executable jar -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 三、CLI配置

## 四、Annotation配置
1.  RestController相当于在每个Controller方法上加了@ResponseBody
2.  EnableAutoConfiguration
3.  SpringBootApplication = (@Configuration @EnableAutoConfiguration @ComponentScan)

## 五、启动方式
1.  >通过java -jar 启动
2.  >通过mvn spring-boot:run启动

## 六、开发工具
当使用 ``java -jar`` 启动的时候会自动屏蔽此开发工具
1.  相关依赖
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```
2.  属性默认
>   通过使用 `spring.*.cache`来设置cache,支持的属性如下:
```
properties.put("spring.thymeleaf.cache", "false");
properties.put("spring.freemarker.cache", "false");
properties.put("spring.groovy.template.cache", "false");
properties.put("spring.mustache.cache", "false");
properties.put("server.session.persistent", "true");
properties.put("spring.h2.console.enabled", "true");
properties.put("spring.resources.cache-period", "0");
properties.put("spring.template.provider.cache", "false");
properties.put("spring.mvc.log-resolved-exception", "true");
properties.put("server.jsp-servlet.init-parameters.development", "true");
```

3.  自动重启

*   通过改变classpath下的文件会使应用重启  静态文件资源不需要重启
*   指定排除的资源 `spring.devtools.restart.exclude=static/**,public/**`
*   监控额外路径 `spring.devtools.restart.additional-paths`
*   禁止重新启动 `spring.devtools.restart.enabled`
*   配置触发文件 `spring.devtools.restart.trigger-file`
*   配置监听classloader `restart.exclude.companycommonlibs=/mycorp-common-[\\w-]+\.jar`
*   通过反序列化的 `ObjectInputStream` 会导致重新启动操作无法正常工作
*   如果使用此开发依赖每次文件改变(`Build → Make Project`)会导致重新启动项目,没有此依赖则使用热部署
*   自动重启使用两个类加载器进行处理,比冷启动要快很多,如果需要更快需要使用reLoad而不是reStart

4.  启动期加载

*   默认已经启动此功能,禁用使用`spring.devtools.livereload.enabled=false`
*   此功能只适用于加载资源文件

5.  全局设置

*   通过配置文件 `.spring-boot-devtools.properties`进行全局设置,文件放在$HOME文件夹下

6.  远程应用

*   通过配置 `spring.devtools.remote.secret=mysecret`打开远程功能

##  六、事件和监听
标准事件启动顺序如下
-   ApplicationStartedEvent
-   ApplicationEnvironmentPreparedEvent
-   ApplicationPreparedEvent
-   ApplicationReadyEvent
-   ApplicationFailedEvent
--------------------------------
添加方式：
1.  >通过SpringApplication.addListeners(…​)或者SpringApplicationBuilder.listeners(…​)
2.  >添加文件META-INF/spring.factories  org.springframework.context.ApplicationListener=com.example.project.MyListener




##  六、最佳实践
1.  不要使用没有包名的类
2.  主类（含有main方法）放在其他类的根下方便注解扫描
3.  推荐java based 项目配置 使用 @import进行配置分离导入
4.  只能添加一个@EnableAutoConfiguration推荐放在主要的@Configuration上
5.  可以添加开发工具方法开发
6.  这个依赖会添加默认的一些配置的值不需要手动在application.properties中添加
同时支持监控资源文件改动自动重启（classpath更新，在idea中表现为  building the project ）,可以通过指定
```
spring.devtools.restart.exclude=static/**,public/**
```
来避免监控
依赖如下
```
-        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
```

##  七、使用配置文件
1.  支持YAML properties XML的配置方式
2.  配置文件可以通过@Value("${name}")进行引用
3.  @ConfigurationProperties和@TestPropertySource@PropertySource @RandomValuePropertySource
4.  属性调用顺序参考文档24章
5.  使用随机数
6.  @ConfigurationProperties支持validation
7.  使用@Profiles（"dev"）来指定配置文件
```
my.secret=${random.value}
my.number=${random.int}
my.bignumber=${random.long}
my.uuid=${random.uuid}
my.number.less.than.ten=${random.int(10)}
my.number.in.range=${random.int[1024,65536]}
```
8.  通过指定属性指来定制springboot信息 比如

```
<properties>
    <java.version>1.8</java.version>
</properties>
```
9.  spring加载属性文件的地方：
    1.  当前目录的/config子目录
    2.  当前目录
    3.  classpath下的/config目录
    4.  classpath根目录

##  八、日志输出
1.  日志格式解释
    1.  Date and Time—Millisecond precision and easily sortable.
    2.  Log Level—ERROR, WARN, INFO, DEBUG or TRACE.
    Process ID.
    3.  A --- separator to distinguish the start of actual log messages.
    4.  Thread name—Enclosed in square brackets (may be truncated for console output).
    5.  Logger name—This is usually the source class name (often abbreviated).
    6.  The log message.

2.  指定日志级别（默认输出级别为INFO)
    1.  logging.level.root=WARN
    2.  logging.level.org.springframework.web=DEBUG
    2.  logging.level.org.hibernate=ERROR
    java -jar myapp.jar --debug
3.  根据日志级别指定颜色
%clr(%5p)
4.  日志文件输出
    1.  logging.file
    2.  logging.path
5.  指定日志系统
> org.springframework.boot.logging.LoggingSystem=日志类全名 参考26.6章
6.  默认使用commons logging进行内部日志输出
7.  如果使用starter会使用logback记录日志
7.  同时支持java logging  log4j2 和logback

##  九、开发web应用
1.  自动配置特性
    1.  包含ContentNegotiatingViewResolver 和 BeanNameViewResolver
    2.  支持静态资源
    3.  自动注册Converter, GenericConverter, Formatter
    4.  支持HttpMessageConverters
    5.  支持静态文件index.html
    6.  支持客户化的Favicon
    7.  自动使用ConfigurableWebBindingInitializer
2.  自定义配置使用 @EnableWebMvc
3.  使用@JsonComponent进行json配置
4.  默认静态资源位置：/static (or /public or /resources or /META-INF/resources)所以写静态资源位置的时候，不要带上映射的目录名（如/static/，/public/ ，/resources/，/META-INF/resources/）！
5.  错误映射实现接口ErrorController可以放置404.html
6.  内嵌servelt @WebServlet @WebFilter  @WebListener
7.  如果没有根目录/映射会访问默认静态资源路径下的index.html资源
8.  项目内引用static目录下的资源 应该使用根应用下访问 而不应该带static目录
9.  支持数据绑定WebBindingInitializer
10.  错误处理
        1.  /error ????

##  十、安全和动作
1.  OAuth2
2.  Actuator


##  十一、数据库配置
1.  mybatis
    1.  依赖如下：
    `````
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
        </dependency>
    `````
    2.  作用
        1.  自动检测数据源
        2.  通过数据源创建SqlSessionFactoryBean
        3.  通过SqlSessionFactoryBean 创建 SqlSessionTemplate
        4.  自动扫描mapper
    3.  配置选项
        在application.properties中使用mybatis.作为前缀 配置选项均可选
        1.  config-location                  mybatis.xml的位置
        2.  mapper-locations                 Mapper.xml的位置
        3.  type-aliases-package             类型别名
        4.  type-handlers-package            处理器别名
        5.  executor-type                    执行类型   SIMPLE, REUSE, BATCH
        6.  configuration                    配置选项和config-location不能同时存在
    4.  注意事项
        1.  如果mapper接收上不使用@Mapper注解 就需要配置一个配置Bean来指定
2.  内嵌数据库
3.  JNDI数据源
4.  使用JdbcTemplate
5.  JPA和Spring Data
6.  H2

##  十二、NoSQL
1.  >   Redis
    1.  配置选项搜索redis选项
    2.  配置spring管理的cache
2.  >   MongoDB
3.  >   Neo4J
4.  >   Gemfire
5.  >   Solr
6.  >   Elasticsearch
7.  >   Cassandra
8.  >   Couchbase

##  十三、缓存
1.  >   Generic
2.  >   JCache (JSR-107)
3.  >   EhCache 2.x
4.  >   Hazelcast
5.  >   Infinispan
6.  >   Couchbase
7.  >   Redis
8.  >   Caffeine
9.  >   Guava
10. >   Simple


##  十四、消息
1.  >ActiveMQ
2.  >Artemis
3.  >HornetQ
4.  >RabbitMQ


##  十五、REST服务
1.  >使用RestTemplate


##  十六、email
1.  >使用JavaMailSender

##  十七、JTA
1.  >Atomikos
2.  >Bitronix
3.  >Narayana
4.  >Java EE
5.  >Mixing XA


##  十八、session
####    存储方式
1.  >JDBC
2.  >MongoDB
3.  >Redis
4.  >Hazelcast
5.  >HashMap
####    配置
1.  >spring.session.store-type=redis
2.  >spring.session.jdbc.table-name=SESSIONS

##  十九、测试

####    测试依赖
可以使用spring test进行集成测试
1.  >JUnit
2.  >Spring Test & Spring Boot Test
3.  >AssertJ
4.  >Hamcrest
5.  >Mockito
6.  >JSONassert
7.  >JsonPath

####    使用spring一站式测试机制

####    测试spring boot 项目
spring test 会在多次测试间缓存环境
- 使用@SpringBootTest可以替代@ContextConfiguration并会创建ApplicationContext
- 使用@SpringBootTest的webEnvironment创建测试环境
    -   MOCK
    > 加载WebApplicationContext并提供mock servlet环境(如果selvlet-api不可用会创建非web环境)配合@AutoConfigureMockMvc使用

    -   RANDOM_PORT
    > 记载随机端口并启动EmbeddedWebApplicationContext

    -   DEFINED_PORT
    > 记载固定端口并启动EmbeddedWebApplicationContext

    -   NONE
    不加载任何sevlet环境

- 使用@RunWith(SpringRunner.class)才能进行测试
-
- 通过@ContextConfiguration指定配置环境
> spring boot 的 @*Test注解会自动搜索主要的配置,无论你是否指定,搜索的标签为@SpringBootApplication和@SpringBootConfiguration

- 使用@TestConfiguration
进行客户化的测试配置和@Configuration不同的是不会取代原来的配置,而是添加配置

- 使用@TestComponent 和 @TestConfiguration


- 添加配置属性EnvironmentTestUtils.addEnvironment(env, "org=Spring", "name=Boot");

- TestRestTemplate


##  二十、自定义auto-configuration


##  二十一、Spring Integration

##  二十二、jOOQ

##  二十三、Hazelcast
1.  >使用HazelcastInstance


#  Actuator
##  一、production-ready特性
使用依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##  二、Endpoints
##  三、Monitoring  HTTP
##  四、Monitoring  JMX
##  五、Monitoring  remote shell
##  六、Metrics
##  七、Auditing
##  八、Tracing
##  九、Process Monitoring


#  应用部署

#   CLI

#   插件
maven插件的作用
```
 <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
1.  收集所有classpath下的jar然后构建成一个可执行的jar
2.  搜索main方法作为一个可运行的类
3.  提供内建的依赖解析,并设置依赖的版本



















## 其他
1.  替换banners （参考文档配置）
2.  注册监听和事件
3.  指定applicationContext和获取application参数
4.  使用ApplicationRunner和CommandLineRunner
5.  退出时的操作实现接口ExitCodeGenerator
6.  开启管理员特性spring.application.admin.enabled
7.  指定启动端口server.port=8888


























####  starters
1.  >   spring-boot-startertest
2.  >   spring-boot-startermobile
3.  >   spring-boot-startersocial-twitter
4.  >   spring-boot-startercache
5.  >   spring-boot-starteractivemq
6.  >   spring-boot-starterjta-atomikos
7.  >   spring-boot-starter-aop
8.  >   spring-boot-starter-web
9.  >   spring-boot-starterdata-elasticsearch
10.  >   spring-boot-starterjdbc
11.  >   spring-boot-starterbatch
12.  >   spring-boot-startersocial-facebook
13.  >   spring-boot-starterweb-services
14.  >   spring-boot-starterjta-narayana
15.  >   spring-boot-starterthymeleaf
16.  >   spring-boot-startermail
17.  >   spring-boot-starterjta-bitronix
18.  >   spring-boot-starterdata-mongodb
19.  >   spring-boot-startervalidation
20.  >   spring-boot-starterjooq
21.  >   spring-boot-starterredis
22.  >   spring-boot-starterdata-cassandra
23.  >   spring-boot-starterhateoas
24.  >   spring-boot-starterintegration
25.  >   spring-boot-starterdata-solr
26.  >   spring-boot-starterfreemarker
27.  >   spring-boot-starterjersey
28.  >   spring-boot-starter
29.  >   spring-boot-starterdata-couchbase
30.  >   spring-boot-starterartemis
31.  >   spring-boot-startercloud-connectors
32.  >   spring-boot-startersocial-linkedin
33.  >   spring-boot-startervelocity
34.  >   spring-boot-starterdata-rest
35.  >   spring-boot-starterdata-gemfire
36.  >   spring-boot-startergroovy-templates
37.  >   spring-boot-starteramqp
38.  >   spring-boot-starterhornetq
39.  >   spring-boot-starter-ws
40.  >   spring-boot-startersecurity
41.  >   spring-boot-starterdata-redis
42.  >   spring-boot-starterwebsocket
43.  >   spring-boot-startermustache
44.  >   spring-boot-starterdata-neo4j
45.  >   spring-boot-starterdata-jpa
46.  >   spring-boot-starteractuator
47.  >   spring-boot-starterremote-shell
48.  >   spring-boot-starterundertow
49.  >   spring-boot-starterlogging
50.  >   spring-boot-startertomcat
51.  >   spring-boot-starterjetty
52.  >   spring-boot-starterlog4j2