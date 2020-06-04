## socket

java socket 模拟 计算机网络TCP/IP 编程

## 运行方式

目录下：

linux and Mac:
> 客户端： bash client.sh

> 服务端： bash server.sh

windows:
socket目录下：
> 客户端： jre/bin/java -jar socket-client/target/socket-client-0.0.1-SNAPSHOT.jar

> 服务器端: jre/bin/java -jar socket-server/target/socket-server-0.0.1-SNAPSHOT.jar

## 使用

首先服务器端开始运行并配置好，开始等待连接

然后运行客户端，配置好后建立连接，然后就可以发送信息，或者传文件

注意：客户端传一个文件后socket会断开连接，所以每传送完一个文件后需要重新建立连接

## 简介

TCP协议两个端点之间进行通信，每个端点又叫做socket

socket组成：IP:端口号

实现的功能：客户端向服务端发送信息，发送文件


