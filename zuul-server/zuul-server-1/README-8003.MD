# Demo 访问方式
## demo 1
>之前的访问方式也可以访问http://localhost:8003/demo-1/hello  
> url符合/demo-8003-1/**规则的就被转发到demo-1实例中了， 所以这种方式http://localhost:8003/demo-8003-1/hello也可以访问

## demo 2
>之前的访问方式也可以访问http://localhost:8003/demo-2/hello  
> url符合/demo-8003-2/**规则的就被转发到demo-2实例中了， 所以这种方式http://localhost:8003/demo-8003-2/hello也可以访问

## demo 3
>之前的访问方式也可以访问http://localhost:8003/demo-3/hello  
> url符合/demo-8003-3/**规则的就被转发到demo-3实例中了， 所以这种方式http://localhost:8003/demo-8003-3/hello也可以访问

# 原理
 url符合```zuul.routes.[route-id].path```规则的就被转发到eureka注册的```zuul.routes.[route-id].service-id```对应实例中了， 所以这种方式http://localhost:8003/demo-8003-3/hello也可以访问