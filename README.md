# Jhinno OpenAPI SDK for Java

针对Java的景行API SDK使Java开发人员能够轻松使用景行API接口。您可以在几分钟内开始使用Maven或jar文件使用它。

- [仓库地址：https://github.com/yanlongqi/jhinno-openapi-java-sdk](https://github.com/yanlongqi/jhinno-openapi-java-sdk)
- [开发文档：https://jhinno-sdk-doc.yuchat.top/apidocs](https://jhinno-sdk-doc.yuchat.top/apidocs)
- [最新jar包：jhinno-openapi-java-sdk-1.0.0.jar](https://jhinno-jenkins.yuchat.top/job/jhinno-openapi-java-sdk/lastSuccessfulBuild/artifact/target/jhinno-openapi-java-sdk-1.0.0.jar)
- [最新源码包：jhinno-openapi-java-sdk-1.0.0-sources.jar](https://jhinno-jenkins.yuchat.top/job/jhinno-openapi-java-sdk/lastSuccessfulBuild/artifact/target/jhinno-openapi-java-sdk-1.0.0-sources.jar)

## 必要条件

- Java 1.8 or later
- Maven

## 支持的Appform的版本

1. JH_Appform_6.0_Release
2. JH_Appform_6.0_SP1_Release

## 安装

建议在您的项目中使用Jhinno OpenAPI SDK for Java的方法是从Maven中使用它。导入方法如下：

添加仓库地址（改方案为备用方案，后续会上传至Maven中央仓库，目前不影响正常使用）

```xml

<repositories>
    <repository>
        <id>jhinno-nexus</id>
        <name>jhinno-nexus</name>
        <url>https://jhinno-nexus.yuchat.top/repository/maven-releases</url>
    </repository>
</repositories>
```

添加依赖

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

如果您没有网络，则可以使用以下方法：

方法一：通过命令将jar包导入本地Maven仓库

```shell
# 其中<path-to-file-jar>为jar的路径，<path-to-file-source-jar>为源码路径
mvn install:install-file -Dfile=<path-to-file-jar>/jhinno-openapi-java-sdk-1.0.0.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=<path-to-file-source-jar>/jhinno-openapi-java-sdk-1.0.0-source.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
# 例如
mvn install:install-file -Dfile=D:/jar/jhinno-openapi-java-sdk-1.0.0.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=D:/jar/jhinno-openapi-java-sdk-1.0.0-source.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
```

> 注: 其中 jhinno-openapi-java-sdk-x.x.x-sources.jar 为源码包，添加可方便查看SDK代码的注释。

然后在`pom.xml`添加

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

方法二：直接在`pom.xml`引用jar

```xml
<!-- 其中{path-to-file}为jar的位置 -->
<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>{path-to-file}</systemPath>
</dependency>
```

例如

```xml
<!-- 其中{path-to-file}为jar的位置 -->
<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>D:/jar/jhinno-openapi-java-sdk-1.0.0.jar</systemPath>
</dependency>
```

## 使用

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
     * 其中https://192.168.87.25/appform为景行API服务的地址
     * 注意: JHApiClient为内置的http连接池，系统只需要初始化一份即可（单例调用）。
     */
    private static final JHApiClient client = JHApiClient.build("https://192.168.87.25/appform");


    public static void main(String[] args) {

        // 初始化一个调用调用景行会话服务接口执行器
        JHAppApiExecution jhAppApiExecution = new JHAppApiExecution(client);

        // 调用启动会话的接口
        AppStartedInfo appStartedInfo = jhAppApiExecution.desktopStart("jhadmin", "linux_desktop", new AppStartRequest());

        // 打印接口的调用结果
        System.out.println(appStartedInfo);
    }

}

```

## 构建

一旦您检出代码，就可以使用Maven构建它。使用以下命令进行构建：

```shell
mvn clean install -DskipTests -P product
```

## 代码贡献

- 代码必须格式化，使用IDEA自带的格式即可；
- 请求路径必须放在一个单独的const类中统一维护，具体可参考现有代码提交；
- 封装新的接口是需要继承`JHApiExecution`，调用父类的辅助方法辅助封装；

## 作者

- [yanlongqi](https://github.com/yanlongqi)

## 支持

- 电话（同微信）：18794888087
- 邮箱：lqyan@jhinno.com