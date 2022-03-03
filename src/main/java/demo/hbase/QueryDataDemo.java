package demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * 添加数据
 */
public class QueryDataDemo {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum", "master:2181,slave1:2181,slave2:2181");

        HTable table = new HTable(configuration, "user1");
        Get get = new Get(Bytes.toBytes("rk0001"));
        get.setMaxVersions(5);
        Result result = table.get(get);
        //遍历出result中所有的键值对
        for(KeyValue kv : result.list()){
            String family = new String(kv.getFamily());
            System.out.println(family);
            String qualifier = new String(kv.getQualifier());
            System.out.println(qualifier);
            System.out.println(new String(kv.getValue()));
        }
        table.close();
        System.out.println("## Done ##");
    }
}
