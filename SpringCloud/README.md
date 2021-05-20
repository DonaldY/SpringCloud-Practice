## 一、`Eureka` 注册中心集群

两个 `eureka` 服务，互相注册，组成一个集群。


在 `eureka-server` 模块下，启动两个服务，配置如下：

1. `peer1` 服务
> 启动，会尝试向本机的8762端口的 `eureka` 服务注册自己
```yaml
server:
 port: 8761
eureka:
 instance:
  hostname: peer1
 client:
  serviceUrl:
   defaultZone: http://peer2:8762/eureka/ 
```

2. `peer2` 服务
> 启动，会尝试向本机的8761端口的 `eureka` 服务注册自己
```yaml
server:
 port: 8762
eureka:
 instance:
  hostname: peer2
 client:
  serviceUrl:
   defaultZone: http://peer1:8761/eureka/ 
```



