# redis_playground


## 项目介绍

本项目之目的：

+ 制作一个简易的用户注册服务系统
+ 学习和使用redis作为缓存
+ 学习和使用mysql作为持久层
+ 学习和掌握redis与mysql数据一致性
+ 学习和规避redis的弊端，如缓存击穿、缓存雪崩、缓存透穿等
+ 学习和使用redis实现分布式锁
+ 学习和使用mysql实现分布式锁
+ more to do



## 项目架构图



## Redis使用方式

+ 启动项目 `mvn spring-boot:run`
+ 启动redis服务 `redis-server.exe`
+ 在浏览器打开 `http://localhost:8080/set` 存储redis
+ 读取redis值 `http://localhost:8080/get`



## Mysql使用方式

+ 启动mysql服务：`mysql -u root -p` 输入密码 （[mysql安装教程](https://www.cnblogs.com/xiaokang01/p/12092160.html))
+ 执行项目`script`目录下的ddl及dml脚本
+ 在浏览器打开：`http://localhost:8080/getId?id=1`即可读取到上面这条数据



# Bonus

---
# mybatis代码自动生成
以上项目中用到的mysql相关entity、mapper（也叫dao）类均通过mybatis代码生成工具实现，在IDEA的Terminal中执行：
`java -jar D:\\env\\apache-maven-3.8.2\\repo\\org\\mybatis\\generator\\mybatis-generator-core\\1.3.2\\mybatis-generator-core-1.3.2.jar  -configfile D:\\code\\Redis\\src\\main\\resources\\generatorConfig.xml -overwrite`
+ 以上路径根据实际自己实际情况变化
+ overwrite表示覆盖式写