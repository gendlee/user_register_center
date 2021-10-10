# redis_playground
## 使用方式
+ 启动项目 `mvn spring-boot:run`
+ 启动redis服务 `redis-server.exe`
+ 在浏览器打开 `http://localhost:8080/set` 存储redis
+ 读取redis值 `http://localhost:8080/get`
---
# mybatis代码自动生成
在IDEA的Terminal中执行：
`java -jar D:\\env\\apache-maven-3.8.2\\repo\\org\\mybatis\\generator\\mybatis-generator-core\\1.3.2\\mybatis-generator-core-1.3.2.jar  -configfile D:\\code\\Redis\\src\\main\\resources\\generatorConfig.xml -overwrite`
+ 以上路径根据实际自己实际情况变化
+ overwrite表示覆盖式写