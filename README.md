# SimpleProxy

## Prerequisite

- Java

```cmd
scoop bucket add java https://gitee.com/zizhujy/Java
scoop install openjdk
```

## Compile

```cmd
cd src&&javac -Xlint:deprecation -encoding utf8  ProxyCache.java
```

## Run

```cmd
cd src&&java ProxyCache 8080
```

## Test

```cmd
set http_proxy=localhost:8080&&curl http://www.baidu.com
```
