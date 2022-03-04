- HBase版本：`1.2.0`
- JDK版本：`1.8`

连接HBase服务器：

```shell
ssh root@192.168.1.130
```

启动HBase的shell命令行工具：

```shell
/bin/hbase shell
```

查询所有表：

```shell
list
```

查询表数据：

```shell
scan 'user1'
```
