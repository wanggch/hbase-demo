package demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * 创建表
 */
public class CreateTableDemo {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum", "master:2181,slave1:2181,slave2:2181");
        //创建连接对象
        Connection connection = ConnectionFactory.createConnection(configuration);
        // DDL操作对象
        Admin admin = connection.getAdmin();
        //表名
        TableName name = TableName.valueOf("user1");
        HTableDescriptor desc = new HTableDescriptor(name);
        //列族
        HColumnDescriptor base_info = new HColumnDescriptor("base_info");
        HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");
        //最大保存的历史版本个数
        base_info.setMaxVersions(5);
        desc.addFamily(base_info);
        desc.addFamily(extra_info);
        //创建表
        System.out.println("## 创建表 ##");
        admin.createTable(desc);
        System.out.println("## close admin ##");
        admin.close();
        System.out.println("## Done ##");
    }
}
