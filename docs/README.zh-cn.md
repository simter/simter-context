# [simter-context](https://github.com/simter/simter-context) [[English]]

一个简单而且强大的在线程生命周期间共享数据的工具类 `tech.simter.Context`。

此工具使用 thread-local 变量来保证各个线程拥有各自的共享数据互不影响。使用此工具的目的是简化上下文数据的传递，
无需在方法中通过参数传递上下文数据，只需在方法体内直接使用 `Context.get(key)` 获取线程内的共享数据（上下文数据）。


## 安装

```xml
<dependency>
  <groupId>tech.simter</groupId>
  <artifactId>simter-context</artifactId>
  <version>1.0.0</version>
</dependency>
```

## 要求

- Java 8+

## 使用

```java
// 设置上下文数据
Context.set("userId", new Long(0));
Context.set("userName", "RJ");
...

void someMethodInOtherClass(){
  // 获取指定的上下文数据
  Long userId = Context.get("userId");
  String userName = Context.get("userName");
  
  // 或者获取全部上下文数据
  Map<String, Object> all = Context.get();
  ...
}
...

// 删除指定的上下文数据
Context.remove("userId");
...
```

注：从上面的代码可以看出获取上下文数据（线程内的共享数据）跟获取一个静态常数值一样简单。
不过必须弄清它们之间的区别：在不同的线程间静态常数的值是固定的，而上下文数据在不同的线程间是隔离的。

## 构建

```bash
mvn clean package
```

## 发布

请先查看 [simter-parent] 的发布配置说明。

### 发布到局域网 Nexus 仓库

```bash
mvn clean deploy -P lan
```

### 发布到 Sonatype 仓库

```bash
mvn clean deploy -P sonatype
```

发布成功后登陆到 <https://oss.sonatype.org>，在 `Staging Repositories` 找到这个包，然后将其 close 和 release。
过几个小时后，就会自动同步到 [Maven 中心仓库](http://repo1.maven.org/maven2/tech/simter/simter-context) 了。

### 发布到 Bintray 仓库

```bash
mvn clean deploy -P bintray
```

发布之前要先在 Bintray 创建 package `https://bintray.com/simter/maven/tech.simter:simter-context`。
发布到的地址为 `https://api.bintray.com/maven/simter/maven/tech.simter:simter-context/;publish=1`。
发布成功后可以到 <https://jcenter.bintray.com/tech/simter/simter-context> 检查一下结果。


[simter-parent]: https://github.com/simter/simter-parent/blob/master/docs/README.zh-cn.md
[English]: https://github.com/simter/simter-context/blob/master/README.md