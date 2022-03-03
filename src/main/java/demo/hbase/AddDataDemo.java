package demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;

/**
 * 添加数据
 */
public class AddDataDemo {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum", "master:2181,slave1:2181,slave2:2181");
        //获取dml客户端对象
        HTable user1 = new HTable(configuration, "user1");
        //给列族中放列以及列值  k_v      hbase存储的是bytes,而字节的数组
        Put name = new Put(Bytes.toBytes("rk0001"));
        name.add(Bytes.toBytes("base_info"), Bytes.toBytes("name"), Bytes.toBytes("songlj"));

        Put age = new Put(Bytes.toBytes("rk0001"));
        age.add(Bytes.toBytes("base_info"), Bytes.toBytes("age"), Bytes.toBytes(18));

        ArrayList<Put> puts = new ArrayList<>();
        puts.add(name);
        puts.add(age);

        user1.put(puts);
        user1.close();
        System.out.println("## Done ##");
    }
}
