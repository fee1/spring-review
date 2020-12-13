### spring框架review

#### 依赖
```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId> <!--实际上已经包含所有spring核心-->
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>  <!--容器，包含core和beans依赖-->
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId> <!--事务-->
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId> <!--aop核心-->
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
```
#### 随便创建一个bean
```java
package com.zxf.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {

    private String name;

    private Integer age;

}
```
#### 随便创建一个配置类， 配置加载配置类的包路径与加载bean的包路径。
```java
package com.zxf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.zxf.config", "com.zxf.bean"})
public class StartConfigrantion {
}
```
#### 随便创建一个spring容器加载和启动的类
```java
package com.zxf.context;

import com.zxf.bean.Student;
import com.zxf.config.StartConfigrantion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StartConfigrantion.class);
        Student student = applicationContext.getBean("student", Student.class); //bean的Id默认为类名，不加也可以
        System.out.println(student);
    }

}
```
#### 启动查看是否配置正确加载到需要的bean
```text
D:\develment\java8\bin\java.exe "-javaagent:D:\devSoft\IDEA\IntelliJ IDEA 2020.2.3\lib\idea_rt.jar=54643:D:\devSoft\IDEA\IntelliJ IDEA 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath D:\develment\java8\jre\lib\charsets.jar;D:\develment\java8\jre\lib\deploy.jar;D:\develment\java8\jre\lib\ext\access-bridge-64.jar;D:\develment\java8\jre\lib\ext\cldrdata.jar;D:\develment\java8\jre\lib\ext\dnsns.jar;D:\develment\java8\jre\lib\ext\jaccess.jar;D:\develment\java8\jre\lib\ext\jfxrt.jar;D:\develment\java8\jre\lib\ext\localedata.jar;D:\develment\java8\jre\lib\ext\nashorn.jar;D:\develment\java8\jre\lib\ext\sunec.jar;D:\develment\java8\jre\lib\ext\sunjce_provider.jar;D:\develment\java8\jre\lib\ext\sunmscapi.jar;D:\develment\java8\jre\lib\ext\sunpkcs11.jar;D:\develment\java8\jre\lib\ext\zipfs.jar;D:\develment\java8\jre\lib\javaws.jar;D:\develment\java8\jre\lib\jce.jar;D:\develment\java8\jre\lib\jfr.jar;D:\develment\java8\jre\lib\jfxswt.jar;D:\develment\java8\jre\lib\jsse.jar;D:\develment\java8\jre\lib\management-agent.jar;D:\develment\java8\jre\lib\plugin.jar;D:\develment\java8\jre\lib\resources.jar;D:\develment\java8\jre\lib\rt.jar;D:\project\spring-review\target\classes;D:\devOther\maven\repo\org\springframework\spring-web\5.2.7.RELEASE\spring-web-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-beans\5.2.7.RELEASE\spring-beans-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-core\5.2.7.RELEASE\spring-core-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-jcl\5.2.7.RELEASE\spring-jcl-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-webmvc\5.2.7.RELEASE\spring-webmvc-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-aop\5.2.7.RELEASE\spring-aop-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-expression\5.2.7.RELEASE\spring-expression-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-context\5.2.7.RELEASE\spring-context-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-tx\5.2.7.RELEASE\spring-tx-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\springframework\spring-aspects\5.2.7.RELEASE\spring-aspects-5.2.7.RELEASE.jar;D:\devOther\maven\repo\org\aspectj\aspectjweaver\1.9.5\aspectjweaver-1.9.5.jar;D:\devOther\maven\repo\org\projectlombok\lombok\1.18.12\lombok-1.18.12.jar com.zxf.context.SpringStarter
Student(name=null, age=null)

Process finished with exit code 0
```
