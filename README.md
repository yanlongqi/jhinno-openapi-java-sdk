# Jhinno OpenAPI SDK for Java

针对Java的景行API SDK使Java开发人员能够轻松使用景行API接口。您可以在几分钟内开始使用Maven或下载一个zip文件。

## 必要条件

- Java 1.8 or later
- Maven
- JH_Appform_6.0_Release

## 安装

建议在您的项目中使用Jhinno OpenAPI SDK for Java的方法是从Maven中使用它。导入如下：

```xml
<dependency>
    <groupId>com.jhinno</groupId>
    <artifactId>jhinno-openapi-java-sdk</artifactId>
    <version>6.0.0</version>
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

## 作者
 - [yanlongqi](https://github.com/yanlongqi)