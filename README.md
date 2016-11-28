#example
### 展示Spring boot 的例子
### 应用了spring-data-jpa
    所以没有应用 mybatis 或 hibernate 的方式
    测试db相关需要在application.properties中修改
### 应用了spring-security
    对应的鉴权方式为 JWT
### 唯一的rest接口路由在 com.chinaventure.rest下
    设置好db相关 可以用postman 之类进行 测试

### Dockerfile 的内容只是一个最简单的将我们编译好的jar进行镜像打包的例子