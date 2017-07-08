# Springboot-Netty-Protostuff-ZooKeeper
Springboot+Netty+Protostuff+ZooKeeper
此项目参考了网上另一个类似的rpc demo，那个demo可以用来熟悉rpc但很多地方不完善。自己慢慢做一个更完善的，正好重新回顾下netty以及zookeeper等
在此项目中解决了
1.zk循环监听
2.springboot启动
3.业务目录自动创建持久，数据目录创建临时
4.本机节点自动添加，不需要主动添加
5.zk节点watch自动更新，且注册中心挂掉后继续提供服务
6.注册中心停止后，server会不断重试，直到注册中心reactive，session过期后会重新新建zk链接
7.client端链接失败后，通过本地保留的list继续提供服务，并且根据zk watch事件进行尝试重连，直到	    链接成功。
8.单次请求超时时间设置
9 tcp请求粘包拆包问题，基于netty解决，可以获取数据长度并根据需要进行处理，不基于定长或者特定字符分割自己基于protobuf开发对应的解码编码器
10 so_backlog 超时设置，超时抛弃
11 在SerializationUtil中用户可以根据自己的需要定制化序列化方式，此处使用protobuf进行序列化

此项目中业务的请求依然基于一连接 请求 断开连接 每次请求都要进行重新链接，新的一版正在完善实现同步链接异步发送请求。
