# URL设计
## Eureka  
### 启动方式
java -jar eureka-server-1.0.1.jar
### 访问方式
http://localhost:8001

## Zuul
### 启动方式
java -jar target\demo-1.0.1.jar --spring.profiles.active=demo-1
### 访问方式
http://localhost:8002

## Demo
### Demo1
> 启动方式
java -jar target\demo-1.0.1.jar --spring.profiles.active=demo-1
### 访问方式
http://localhost:9001/hello

### Demo2
> 启动方式
java -jar target\demo-1.0.1.jar --spring.profiles.active=demo-2
### 访问方式
http://localhost:9002/hello

### Demo3
> 启动方式
java -jar target\demo-1.0.1.jar --spring.profiles.active=demo-3
### 访问方式
http://localhost:9003/hello