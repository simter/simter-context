# [simter-context](https://github.com/simter/simter-context) [[中文]]

A simple and powerful utils class `tech.simter.Context` for share data during the same thread lifecycle.

This tools use thread-local variables to make each thread has its own initialized share data.
You don't need to transfer context data through method arguments, just use `Context.get(key)` to get 
its value inside the method.

## Installation

```xml
<dependency>
  <groupId>tech.simter</groupId>
  <artifactId>simter-context</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Requirement

- Java 8+

## Usage

```java
// set share data anywhere
Context.set("userId", new Long(0));
Context.set("userName", "RJ");
...

void someMethodInOtherClass(){
  // get shared data by key anywhere
  Long userId = Context.get("userId");
  String userName = Context.get("userName");
  
  // or get all shared data
  Map<String, Object> all = Context.get();
  ...
}
...

// delete shared data anywhere
Context.remove("userId");
...
```

You can see that it just like to get a static constant value. But you need to know the difference: 
A static constant always has the same value even in different thread. But the context data is isolated between each thread.

## Build

```bash
mvn clean package
```


## Deploy

First take a look at [simter-parent] deploy config.

### Deploy to LAN Nexus Repository

```bash
mvn clean deploy -P lan
```

### Deploy to Sonatype Repository

```bash
mvn clean deploy -P sonatype
```

After deployed, login into <https://oss.sonatype.org>. Through `Staging Repositories`, search this package,
then close and release it. After couple hours, it will be synced
to [Maven Central Repository](http://repo1.maven.org/maven2/tech/simter/simter-context).

### Deploy to Bintray Repository

```bash
mvn clean deploy -P bintray
```

Will deploy to `https://api.bintray.com/maven/simter/maven/tech.simter:simter-context/;publish=1`.
So first create a package `https://bintray.com/simter/maven/tech.simter:simter-context` on Bintray.
After deployed, check it from <https://jcenter.bintray.com/tech/simter/simter-context>.


[simter-parent]: https://github.com/simter/simter-parent
[中文]: https://github.com/simter/simter-context/blob/master/docs/README.zh-cn.md