# 
spring-cloud-starter-netflix-zuul依赖包含spring-cloud-starter-netflix-ribbon和spring-cloud-starter-netflix-hystrix的依赖，所以zuul本身就拥有线程隔离和断路器的自我保护功能，以及对服务调用的客户端负载均衡功能

# 重要的filter
```Route {@link ZuulFilter} that uses Ribbon, Hystrix and pluggable http clients to send requests. ServiceIds are found in the {@link RequestContext} attribute {@link org.springframework.cloud.netflix.zuul.filters.support.FilterConstants#SERVICE_ID_KEY}```  
org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter  
```Route {@link ZuulFilter} that sends requests to predetermined URLs via apache {@link HttpClient}. URLs are found in {@link RequestContext#getRouteHost()}```  
org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter **没有使用Hystrix命令进行包装，这类(```zuul.routes.demo-1.url=http://localhost:9001/```)请求没有线程隔离和断路器的保护**  
```Route {@link ZuulFilter} that forwards requests using the {@link RequestDispatcher}. Forwarding location is located in the {@link RequestContext} attribute {@link org.springframework.cloud.netflix.zuul.filters.support.FilterConstants#FORWARD_TO_KEY}. Useful for forwarding to endpoints in the current application.```  
org.springframework.cloud.netflix.zuul.filters.route.SendForwardFilter   
执行forward类请求  

# 重要的processor
```This the the core class to execute filters```
com.netflix.zuul.FilterProcessor

# 时间计算
zuul默认情况下重试机制(```zuul.retryable=true```或```zuul.[route.id].retryable=true```)是开启的, 在开启时的默认重试机制是如果不成功并且不超时的情况下会在当前代理节点再重试一次， 所以建议对应的
```hystrix.command.default.execution.isolation.thread.timeoutInMillseconds```整个时间为 2 * (```ribbon.ConnectTimeout + ribbon.ReadTimeout```)

# 注意:
## 1. ```ribbon.ConnectTimeout```与```hystrix.command.default.execution.isolation.thread.timeoutInMillseconds``` 超时设置
```ribbon.ConnectTimeout```大于```hystrix.command.default.execution.isolation.thread.timeoutInMillseconds```时，当出现路由请求连接超时，此时对于路由转发的执行命令已经超时，所以不会启动路由重试机制，而是直接按请求命令超时处理，返回超时错误。  
```ribbon.ConnectTimeout```小于```hystrix.command.default.execution.isolation.thread.timeoutInMillseconds```时，当出现路由请求连接超时，此时会启动路由重试机制，如果请求依然失败，zuul会返回NUMBEROF_RETRIES_NEXTSERVER_EXCEEDED。
## 2. ```ribbon.ReadTimeout```与```hystrix.command.default.execution.isolation.thread.timeoutInMillseconds``` 超时设置
与1相同