# 1. Jhinno OpenAPI SDK for Java

针对 Java 的景行 API SDK 使 Java 开发人员能够轻松使用景行 API 接口。您可以在几分钟内开始通过 Maven 或 jar 文件使用它。

- [仓库地址：https://github.com/yanlongqi/jhinno-openapi-java-sdk](https://github.com/yanlongqi/jhinno-openapi-java-sdk)
- [jar 包下载：https://github.com/yanlongqi/jhinno-openapi-java-sdk/releases](https://github.com/yanlongqi/jhinno-openapi-java-sdk/releases)

## 1.1 必要条件

- Java 1.8 or later
- Maven

## 1.2 支持的 Appform 的版本

1. JH_Appform_6.0_Release
2. JH_Appform_6.0_SP1_Release
3. JH_Appform_6.1_Release
4. JH_Appform_6.2_Release（使用: release-2.0.3）
5. JH_Appform_6.3_Release（使用: release-2.0.4）
5. JH_Appform_6.3_Release（使用: release-2.0.5）

# 2. 快速开始

## 2.1 SpringBoot

### 2.1.1 安装

建议在您的项目中使用 Jhinno OpenAPI SDK for Java 的方法是从 Maven 中使用它。：

#### 方法一：通过命令将 jar 包导入本地 Maven 仓库

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
>
> - jhinno-openapi-java-sdk-x.x.x.jar 为 SDK 的 jar 包。
> - jhinno-openapi-java-sdk-x.x.x-sources.jar 为源码包，添加可方便查看 SDK 代码的注释。
> - jhinno-openapi-sdk-spring-boot-starter-x.x.x.jar 为 SDK 的 spring-boot-starter 的 jar 包。
> - jhinno-openapi-sdk-spring-boot-starter-x.x.x-sources.jar 为 SDK 的 spring-boot-starter 的源码包。

#### 方法二：通过源码导入

```shell
git clone https://github.com/yanlongqi/jhinno-openapi-java-sdk.git
cd jhinno-openapi-java-sdk
mvn clean install
```

### 2.1.2 引入`jhinno-openapi-sdk-spring-boot-starter`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-sdk-spring-boot-starter</artifactId>
    <version>${最新的版本号}</version>
</dependency>
```

#### 方法三：直接使用 jar 包

如果开发环境没有网络，或者没有使用 maven 的构建工具，则可以使用 jar 包的方式导入。下载`dependency-jar.zip`和`jhinno-openapi-java-sdk-2.0.3-sources.jar`、`jhinno-openapi-java-sdk-2.0.3.jar`导入到你的 java 项目的 lib 里面。如果你的项目是 SpringBoot 项目，则还需要导入`jhinno-openapi-sdk-spring-boot-starter-2.0.3.jar`、`jhinno-openapi-sdk-spring-boot-starter-2.0.3-sources.jar
`这两个 jar 包。

### 2.1.3 配置

在 SpringBoot 的`application.properties`或`application.yml`里面配置

#### 2.1.3.1 application.yaml

```yaml
jhinno:
  openapi:
    server-url: https://172.17.0.5
    access-key: xxxxx
    access-key-secret: xxxx
    auth-type: access_secret_mode
```

#### 2.1.3.2 application.properties

```properties
jhinno.openapi.server-url=https://{appform服务器的地址}
jhinno.openapi.access-key=xxxxx
jhinno.openapi.access-key-secret=xxxx
jhinno.openapi.auth-type=access_secret_mode

```

> 注：
>
> - 其中`jhinno.openapi.server-url`为景行接口服务的 BaseUrl；
> - `auth-type` 认证类型，`token_mode`(Token 认证) 和 `access_secret_mode`（AccessKey 认证）；Appform Release 6.2 `token_mode`

    作为过渡，将会弃用；

> - `jhinno.openapi.used-server-time`是否获取服务器时间来请求 token，关闭可提高获取 token 的时间，但打开有可能因为服务器时间不准确而导致 token 获取失败的问题。
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

建议在您的项目中使用 Jhinno OpenAPI SDK for Java 的方法是从 Maven 中使用它。：

#### 方法一：通过命令将 jar 包导入本地 Maven 仓库（无网络开发额外配置）

```shell

# SDK Client的jar包的导入，其中<path-to-dir>为jar的路径，<path-to-dir>为jar包路径
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="<path-to-dir>/jhinno-openapi-java-sdk-1.0.0.jar" -Dsources="<path-to-dir>/jhinno-openapi-java-sdk-1.0.0-sources.jar"
# 例如
mvn org.apache.maven.plugins:maven-install-plugin:3.1.1:install-file -Dfile="E:/下载/jhinno-openapi-java-sdk-1.0.0.jar" -Dsources="E:/下载/jhinno-openapi-java-sdk-1.0.0-sources.jar"
```

> 注:
>
> - jhinno-openapi-java-sdk-x.x.x.jar 为 SDK 的 jar 包。
> - jhinno-openapi-java-sdk-x.x.x-sources.jar 为源码包，添加可方便查看 SDK 代码的注释。

#### 方法二：通过源码导入（无网络开发额外配置）

```shell
git clone https://github.com/yanlongqi/jhinno-openapi-java-sdk.git
cd jhinno-openapi-java-sdk
mvn clean install
```

### 2.2.2 引入`jhinno-openapi-java-sdk`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>${最新的版本号}</version>
</dependency>
```

### 2.2.3 配置

spring.xml 添加以下内容

```xml
<?xml version="1.0" encoding="utf-8"?>
<beans>
    <bean id="apiClient" class="com.jhinno.sdk.openapi.client.JHApiClient" init-method="initDefaultApiClient">
        <constructor-arg value="https://172.17.0.5"/>
    </bean>

    <bean id="requestExecution" class="com.jhinno.sdk.openapi.api.JHRequestExecution">
        <constructor-arg ref="apiClient" />
    </bean>

    <bean id="appApiExecution" class="com.jhinno.sdk.openapi.api.app.JHAppApiExecution" init-method="init"></bean>
    <bean id="dataApiExecution" class="com.jhinno.sdk.openapi.api.data.JHDataApiExecution" init-method="init"></bean>
    <bean id="fileApiExecution" class="com.jhinno.sdk.openapi.api.file.JHFileApiExecution" init-method="init"></bean>
    <bean id="jhJobApiExecution" class="com.jhinno.sdk.openapi.api.job.JHJobApiExecution" init-method="init"></bean>
    <bean id="departmentApiExecution" class="com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution" init-method="init"></bean>
    <bean id="userApiExecution" class="com.jhinno.sdk.openapi.api.organization.JHUserApiExecution" init-method="init"></bean>
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

## 2.3 普通 java 项目

### 2.3.1 安装

- 同 2.2.1 安装一样

### 2.3.2 引入`jhinno-openapi-java-sdk`坐标

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>${最新的版本号}</version>
</dependency>
```

### 2.3.3 使用

在`com.jhinno.sdk.openapi.api`包下面对应`app`、`data`、`file`、`job`、`organization`这几个子包，分别代表景行`Appform`
的应用、作业数据、文件、作业操作、组织等接口资源。开发者只需要使用接口的执行器（`JHxxxApiExecution`
）即可使用资源，如：`JHAppApiExecution`

具体的使用步骤如下：

1. 创建一个`JHApiClient`客户端，此为 HTTP 连接池，为确保资源浪费，需保证全局唯一，每次创建执行器都使用个客户端。
2. 创建接口的执行器，即：`JHxxxApiExecution`，如：`JHAppApiExecution`。
3. 调用接口执行器的方法，使用景行接口资源，如下代码片段。

```java

public class JHApiUtile {

    /**
     * 创建一个API执行器管理器
     */
    public static final JHApiExecutionManage API_EXECUTION_MANAGE = new JHApiExecutionManage("https://192.168.87.24");

    public static final String ACCESS_KEY = "3f03747f147942bd8debd81b6c9c6a80";

    public static final String ACCESS_KEY_SECRET = "e0681859b91c499eb1d2c8e09cea3242";

    static {
        // 配置API执行器管理器，设置认证信息等。
        API_EXECUTRON_MANAGE.configureApiExecution(t -> {
            // 默认为使用Token模式，如何使用的Token模式，则不需要配置ACCESS_KEY和ACCESS_KEY SECRET
            // t.setAuthType(AuthType.ACCESS_KEY);
            t.setAccessKey(ACCESS_KEY);
            t.setAccessKeySecret(ACCESS_KEY_SECRET);
        });
    }

    public static void main(String[] args) {

        // 从API执行器管理器取出调用应用相关接口的执行器
        JHAppApiExecution jhAppApiExecution = JHClientConfig.API_EXECUTION_MANAGE.getApiExecution(JHAppApiExecution.class);

        // 调用启动会话的接口
        jhAppApiExecution.desktopStart("jhadmin", "linux_desktop");
    }

}

```

# 3. 支持 SDK 的扩展

如果是基于景行定制的接口，本 SDK 没有包含这些方法，因此您可以基于`JHApiExecution`快速进行扩展，具体的扩展步骤如下：

- 新建需要扩展的执行器命名为`JHxxxApiExecution`，并继承`JHApiExecution`；
- 编写基于接口调用的方法；

父类提供了封装好的`get`、`post`、`put`、`delete`方法，可以直接使用，而不考虑 token 的问题

## 3.1 SpringBoot 项目

### 3.1.1 方式一

通过实现`JHApiExecution`接口，实现自定义的`JHDemoApiExecution`，并注册到 Spring 容器中。

```java
/**
 * 注意：一下代码为伪代码，需要根据实际的情况进行修改，其示例代码可参照SDK中JHDemoApiExecution子类的实现
 */
@Component
public class JHDemoApiExecution extends JHApiExecution {

    private JHRequestExecution execution;

    @Override
    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

    public XxxDTO getXXXX(String username, String demoParams) {

        return execution.get("/demo/path", username, new TypeReference<ResponseResult<XxxDTO>>() {
        });
    }
}

```

### 3.1.2 方式二

通过注入 `JHRequestExecution` 的方式来注入

```java
/**
 * 注意：一下代码为伪代码，需要根据实际的情况进行修改，其示例代码可参照SDK中JHDemoApiExecution子类的实现
 */
public class JHDemoApiExecution extends JHApiExecution {

    @Autowired
    private JHRequestExecution execution;

    public XxxDTO getXXXX(String username, String demoParams) {

        // ResponseResult<XxxDTO> 可以参照接口文档定义自己的数据传输对象
        return execution.get("/demo/path", username, new TypeReference<ResponseResult<XxxDTO>>() {
        });
    }
}

```

# 3.2 非 SpringBoot 项目

```java
/**
 * 注意：一下代码为伪代码，需要根据实际的情况进行修改，其示例代码可参照SDK中JHDemoApiExecution子类的实现
 */
public class JHDemoApiExecution extends JHApiExecution {

    private JHRequestExecution execution;

    @Override
    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

    public XxxDTO getXXXX(String username, String demoParams) {

        return execution.get("/demo/path", username, new TypeReference<ResponseResult<XxxDTO>>() {
        });
    }
}


public class JHApiUtile {

    public static final JHApiExecutionManage API_EXECUTION_MANAGE = new JHApiExecutionManage("https://192.168.87.24");

    public static final String ACCESS_KEY = "3f03747f147942bd8debd81b6c9c6a80";

    public static final String ACCESS_KEY_SECRET = "e0681859b91c499eb1d2c8e09cea3242";

    static {
        // 配置API执行器管理器，设置认证信息等。
        API_EXECUTRON_MANAGE.configureApiExecution(t -> {
            // 默认为使用Token模式，如何使用的Token模式，则不需要配置ACCESS_KEY和ACCESS_KEY SECRET
            // t.setAuthType(AuthType.ACCESS_KEY);
            t.setAccessKey(ACCESS_KEY);
            t.setAccessKeySecret(ACCESS_KEY_SECRET);
        });

        // 注册自定义的API执行器，会自动配置你自定义的执行器
        API_EXECUTRON_MANAGE.registerApiExecution(new JHDemoApiExecution());
    }

    public static void main(String[] args) {

        // 从API执行器管理器取出调用应用相关接口的执行器
        JHDemoApiExecution jhAppApiExecution = JHClientConfig.API_EXECUTION_MANAGE.getApiExecution(JHDemoApiExecution.class);

        // 调用启动会话的接口
        jhAppApiExecution.getXXXX("jhadmin", "xxxx");
    }
}

```

# 4. 构建

一旦您检出代码，就可以使用 Maven 构建它。使用以下命令进行构建：

```shell
mvn clean package
```

# 5. 代码贡献

- 代码必须格式化，使用 IDEA 自带的格式即可；
- 请求路径必须放在一个单独的 const 类中统一维护，具体可参考现有代码提交；
- 封装新的接口是需要继承`JHApiExecution`，调用父类的辅助方法辅助封装；

# 6. 作者

- [yanlongqi](https://github.com/yanlongqi)

# 7. 支持

- 电话（同微信）：18794888087
- 邮箱：lqyan@jhinno.com
