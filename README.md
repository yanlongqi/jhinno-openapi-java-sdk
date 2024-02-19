# Jhinno OpenAPI SDK for Java

针对Java的景行API SDK使Java开发人员能够轻松使用景行API接口。您可以在几分钟内开始使用Maven或下载一个zip文件。

## 必要条件

- Java 1.8 or later
- Maven

## SDK与Appform的版本对应情况

<table>
    <thead>
        <tr>
            <td>jhinno-openapi-java-sdk</td>
            <td>下载地址</td>
            <td>Appform</td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan="2">1.0.0</td>
            <td rowspan="2">
                <p>
                    <a href="http://192.168.87.22:8000/job/jhinno-openapi-java-sdk/lastSuccessfulBuild/artifact/target/jhinno-openapi-java-sdk-1.0.0.jar">jhinno-openapi-java-sdk-6.0.0.jar</a>
                </p>
                <p>
                    <a href="http://192.168.87.22:8000/job/jhinno-openapi-java-sdk/lastSuccessfulBuild/artifact/target/jhinno-openapi-java-sdk-1.0.0-SNAPSHOT.jar">jhinno-openapi-java-sdk-6.0.0-source.jar</a>
                </p>
            </td>
            <td>JH_Appform_6.0_Release</td>
        </tr>
        <tr>
            <td>JH_Appform_6.0_SP1_Release</td>
        </tr>
    </tbody>
</table>

## 安装

建议在您的项目中使用Jhinno OpenAPI SDK for Java的方法是从Maven中使用它。导入如下：

```xml

<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

对于没有网络的用户，可以使用以下方法使用：

方法一：通过命令将jar包导入本地Maven仓库

```shell
# 其中<path-to-file-jar>为jar的路径，<path-to-file-source-jar>为源码路径
mvn install:install-file -Dfile=<path-to-file-jar>/jhinno-openapi-java-sdk-1.0.0.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
mvn install:install-file -Dfile=<path-to-file-source-jar>/jhinno-openapi-java-sdk-1.0.0-source.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
# 例如
mvn install:install-file -Dfile=D:/jar/jhinno-openapi-java-sdk-1.0.0.jar -DgroupId=com.jhinno -DartifactId=jhinno-openapi-java-sdk -Dversion=1.0.0 -Dpackaging=jar
```

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
<!-- 其中${path-to-file}为jar的位置 -->
<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${path-to-file}</systemPath>
</dependency>
```

## 使用

```java

public class DemoUserSDK {

    /**
     * JHApiClient 是一个HTTP连接池，开发者需要复用
     * 其中https://192.168.87.25/appform为景行API服务的地址
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
mvn clean install -DskipTests
```

## 代码提交

- 代码必须格式化，使用IDEA自带的格式即可；
- 请求路径必须放在一个单独的const类中统一维护，具体可参考现有代码提交；
- 封装新的接口是需要继承`JHApiExecution`，调用父类的辅助方法辅助封装；

## 作者

- [yanlongqi](https://github.com/yanlongqi)