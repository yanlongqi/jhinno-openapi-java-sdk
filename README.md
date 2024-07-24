# 1. Jhinno OpenAPI SDK for Java

针对Java的景行API SDK使Java开发人员能够轻松使用景行API接口。您可以在几分钟内开始通过Maven或jar文件使用它。

- [仓库地址：https://github.com/yanlongqi/jhinno-openapi-java-sdk](https://github.com/yanlongqi/jhinno-openapi-java-sdk)
- [开发文档：https://jhinno-sdk-doc.yuchat.top/apidocs](https://jhinno-sdk-doc.yuchat.top/apidocs)
- [jar包下载：https://github.com/yanlongqi/jhinno-openapi-java-sdk/releases](https://github.com/yanlongqi/jhinno-openapi-java-sdk/releases)

## 1.1 必要条件

- Java 1.8 or later
- Maven

## 1.2 支持的Appform的版本

1. JH_Appform_6.0_Release
2. JH_Appform_6.0_SP1_Release
3. JH_Appform_6.1_Release
3. JH_Appform_6.2_Release（6.2以前用1.x.x版本）

# 2. 快速开始

## 2.1 SpringBoot

### 2.1.1 安装

建议在您的项目中使用Jhinno OpenAPI SDK for Java的方法是从Maven中使用它。：

#### 方法一：通过Maven仓库安装

> 添加仓库地址（改方案为备用方案，后续会上传至Maven中央仓库，目前不影响正常使用）

```xml

<repositories>
    <repository>
        <id>jhinno-nexus</id>
        <name>jhinno-nexus</name>
        <url>https://jhinno-nexus.yuchat.top/repository/maven-releases</url>
    </repository>
</repositories>
```

#### 方法二：通过命令将jar包导入本地Maven仓库

```shell

# SDK Client的jar包的导入，其中<path-to-dir>为jar的路径，<path-to-dir>为jar包路径
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="<path-to-dir>/jhinno-openapi-java-sdk-x.x.x.jar" -Dsources="<path-to-dir>/jhinno-openapi-java-sdk-x.x.x-sources.jar"
# 例如
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="E:/下载/jhinno-openapi-java-sdk-2.0.0.jar" -Dsources="E:/下载/jhinno-openapi-java-sdk-2.0.0-sources.jar"

# SDK SpringBoot Starter的jar包的导入，其中<path-to-dir>为jar的路径，<path-to-dir>为jar包路径
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="<path-to-dir>/jhinno-openapi-sdk-spring-boot-starter-x.x.x.jar" -Dsources="<path-to-dir>/jhinno-openapi-sdk-spring-boot-starter-x.x.x-sources.jar"
# 例如
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="E:/下载/jhinno-openapi-sdk-spring-boot-starter-2.0.0.jar" -Dsources="E:/下载/jhinno-openapi-sdk-spring-boot-starter-2.0.0-sources.jar"
```

> 注:
> - jhinno-openapi-java-sdk-x.x.x.jar 为SDK的jar包。
> - jhinno-openapi-java-sdk-x.x.x-sources.jar 为源码包，添加可方便查看SDK代码的注释。
> - jhinno-openapi-sdk-spring-boot-starter-x.x.x.jar 为SDK的spring-boot-starter的jar包。
> - jhinno-openapi-sdk-spring-boot-starter-x.x.x-sources.jar 为SDK的spring-boot-starter的源码包。

### 2.1.2 引入`jhinno-openapi-sdk-spring-boot-starter`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-sdk-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 2.1.3 配置

在SpringBoot的`application.properties`或`application.yml`里面配置

#### 2.1.3.1  application.yaml

```yaml
jhinno:
  openapi:
    server-url: https://172.17.0.5/appform
    access-key: xxxxx
    access-key-secret: xxxx
    auth-type: access_secret_mode
```

#### 2.1.3.2  application.properties

```properties
jhinno.openapi.server-url=https://{appform服务器的地址}/appform
jhinno.openapi.access-key=xxxxx
jhinno.openapi.access-key-secret=xxxx
jhinno.openapi.auth-type=access_secret_mode

```

> 注：
> - 其中`jhinno.openapi.server-url`为景行接口服务的BaseUrl；
> - `auth-type` 认证类型，`token_mode`(Token认证) 和 `access_secret_mode`（AccessKey认证）；Appform Release 6.2 `token_mode`作为过渡，将会弃用；
> - `jhinno.openapi.used-server-time`是否获取服务器时间来请求token，关闭可提高获取token的时间，但打开有可能因为服务器时间不准确而导致token获取失败的问题。
> - `access-key` 和 `access-key-secret` 作为访问接口的凭证，需要提供集成商名称、系统名称、负责人姓名、负责电话电话信息申请。
> - 更多配置见`com.jhinno.sdk.openapi.autoconfigure.JHOpenapiProperties`源码。

### 2.1.4 使用

在`com.jhinno.sdk.openapi.api`包下面对应`app`、`data`、`file`、`job`、`organization`这几个子包，分别代表景行`Appform`
的应用、作业数据、文件、作业操作、组织等接口资源。开发者只需要使用接口的执行器（`JHxxxApiExecution`
）即可使用资源，如：`JHFileApiExecution`

```java
public class DemoUserSDK {

    /**
     * 注入要调用的执行器
     */
    @Autowired
    private JHFileApiExecution fileApiExecution;

    void contextLoads() {

        // 调用执行其中想要调用的方法
        List<FileInfo> list = fileApiExecution.getFileList("jhadmin", "$HOME");
        System.out.println(list);
    }
}
```

## 2.2 Spring

### 2.2.1 安装

建议在您的项目中使用Jhinno OpenAPI SDK for Java的方法是从Maven中使用它。：

#### 方法一：通过Maven仓库安装

> 添加仓库地址（改方案为备用方案，后续会上传至Maven中央仓库，目前不影响正常使用）

```xml

<repositories>
    <repository>
        <id>jhinno-nexus</id>
        <name>jhinno-nexus</name>
        <url>https://jhinno-nexus.yuchat.top/repository/maven-releases</url>
    </repository>
</repositories>
```

#### 方法二：通过命令将jar包导入本地Maven仓库

```shell

# SDK Client的jar包的导入，其中<path-to-dir>为jar的路径，<path-to-dir>为jar包路径
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="<path-to-dir>/jhinno-openapi-java-sdk-1.0.0.jar" -Dsources="<path-to-dir>/jhinno-openapi-java-sdk-1.0.0-sources.jar"
# 例如
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="E:/下载/jhinno-openapi-java-sdk-1.0.0.jar" -Dsources="E:/下载/jhinno-openapi-java-sdk-1.0.0-sources.jar"
```

> 注:
> - jhinno-openapi-java-sdk-x.x.x.jar 为SDK的jar包。
> - jhinno-openapi-java-sdk-x.x.x-sources.jar 为源码包，添加可方便查看SDK代码的注释。

### 2.2.2 引入`jhinno-openapi-java-sdk`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 2.2.3 配置

spring.xml添加以下内容

```xml
<?xml version="1.0" encoding="utf-8"?>
<beans>
    <bean id="apiClient" class="com.jhinno.sdk.openapi.client.JHApiClient" init-method="initDefaultApiClient">
        <constructor-arg value="https://172.17.0.5/appform"/>
    </bean>

    <bean id="appApiExecution" class="com.jhinno.sdk.openapi.api.app.JHAppApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
    <bean id="dataApiExecution" class="com.jhinno.sdk.openapi.api.data.JHDataApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
    <bean id="fileApiExecution" class="com.jhinno.sdk.openapi.api.file.JHFileApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
    <bean id="jhJobApiExecution" class="com.jhinno.sdk.openapi.api.job.JHJobApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
    <bean id="departmentApiExecution" class="com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
    <bean id="userApiExecution" class="com.jhinno.sdk.openapi.api.organization.JHUserApiExecution">
        <constructor-arg ref="apiClient"/>
    </bean>
</beans>
```

### 2.2.4 使用

在`com.jhinno.sdk.openapi.api`包下面对应`app`、`data`、`file`、`job`、`organization`这几个子包，分别代表景行`Appform`
的应用、作业数据、文件、作业操作、组织等接口资源。开发者只需要使用接口的执行器（`JHxxxApiExecution`
）即可使用资源，如：`JHAppApiExecution`

```java
public class DemoUserSDK {

    /**
     * 注入要调用的执行器
     */
    @Autowired
    private JHFileApiExecution fileApiExecution;

    void contextLoads() {

        // 调用执行其中想要调用的方法
        List<FileInfo> list = fileApiExecution.getFileList("jhadmin", "$HOME");
        System.out.println(list);
    }
}
```

## 2.3 普通java项目

### 2.3.1 安装

- 同 2.2.1 安装一样

### 2.3.2 引入`jhinno-openapi-java-sdk`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>2.0.0</version>
</dependency>
```

### 2.3.3 使用

在`com.jhinno.sdk.openapi.api`包下面对应`app`、`data`、`file`、`job`、`organization`这几个子包，分别代表景行`Appform`
的应用、作业数据、文件、作业操作、组织等接口资源。开发者只需要使用接口的执行器（`JHxxxApiExecution`
）即可使用资源，如：`JHAppApiExecution`

具体的使用步骤如下：

1. 创建一个`JHApiClient`客户端，此为HTTP连接池，为确保资源浪费，需保证全局唯一，每次创建执行器都使用个客户端。
2. 创建接口的执行器，即：`JHxxxApiExecution`，如：`JHAppApiExecution`。
3. 调用接口执行器的方法，使用景行接口资源，如下代码片段。

```java

public class DemoUserSDK {

    /**
     * JHApiClient 是一个HTTP连接池，开发者需要复用
     * 其中https://172.17.0.5/appform为景行API服务的地址
     * 注意: JHApiClient为内置的http连接池，系统只需要初始化一份即可（单例调用）。
     */
    public static final JHApiClient client = new JHApiClient("https://172.17.0.5/appform");

    public static final Map<Class<? extends JHApiExecution>, JHApiExecution> jhApiClientMap = new HashMap<>();

    public static final String ACCESS_KEY = "3f03747f147942bd8debd81b6c9c6a80";

    public static final String ACCESS_KEY_SECRET = "e0681859b91c499eb1d2c8e09cea3242";

    static {
        client.initDefaultApiClient();
        jhApiClientMap.put(JHAppApiExecution.class, new JHAppApiExecution());
        jhApiClientMap.put(JHDataApiExecution.class, new JHDataApiExecution());
        jhApiClientMap.put(JHFileApiExecution.class, new JHFileApiExecution());
        jhApiClientMap.put(JHJobApiExecution.class, new JHJobApiExecution());
        jhApiClientMap.put(JHDepartmentApiExecution.class, new JHDepartmentApiExecution());
        jhApiClientMap.put(JHUserApiExecution.class, new JHUserApiExecution());

        jhApiClientMap.forEach((k, v) -> {
            v.setJhApiClient(client);
            v.setAuthType(AuthType.ACCESS_SECRET_MODE);
            v.setAccessKey(ACCESS_KEY);
            v.setAccessKeySecret(ACCESS_KEY_SECRET);
            v.setUsedServerTime(true);
        });
    }

    public static void main(String[] args) {

        // 初始化一个调用调用景行会话服务接口执行器
        JHAppApiExecution jhAppApiExecution = (JHAppApiExecution) jhApiClientMap.get(JHAppApiExecution.class);

        // 调用启动会话的接口
        AppStartedInfo appStartedInfo = jhAppApiExecution.desktopStart("jhadmin", "linux_desktop", new AppStartRequest());

        // 打印接口的调用结果
        System.out.println(appStartedInfo);
    }

}

```

# 3. 支持SDK的扩展

如果是基于景行定制的接口，本SDK没有包含这些方法，因此您可以基于`JHApiExecution`快速进行扩展，具体的扩展步骤如下：

- 新建需要扩展的执行器命名为`JHxxxApiExecution`，并继承`JHApiExecution`；
- 编写基于接口调用的方法；

父类提供了封装好的`get`、`post`、`put`、`delete`方法，可以直接使用，而不考虑token的问题

```java
/**
 * 注意：一下代码为伪代码，需要根据实际的情况进行修改，其示例代码可参照SDK中JHApiExecution子类的实现
 */
public class JHAppApiExecution extends JHApiExecution {

    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHAppApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }

    public XxxDTO getXXXX(String username, String demoParams) {

        return get("/demo/path", username, new TypeReference<ResponseResult<XxxDTO>>() {
        });
    }
}

```

# 4. 构建

一旦您检出代码，就可以使用Maven构建它。使用以下命令进行构建：

```shell
mvn clean package -DskipTests -P product
```

# 5. 代码贡献

- 代码必须格式化，使用IDEA自带的格式即可；
- 请求路径必须放在一个单独的const类中统一维护，具体可参考现有代码提交；
- 封装新的接口是需要继承`JHApiExecution`，调用父类的辅助方法辅助封装；

# 6. 作者

- [yanlongqi](https://github.com/yanlongqi)

# 7. 支持

- 电话（同微信）：18794888087
- 邮箱：lqyan@jhinno.com