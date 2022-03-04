package demo.hbase;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
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
        HTable user1 = new HTable(configuration, "t_real_time_data");
        String id = IdUtil.getSnowflakeNextIdStr();
        //给列族中放列以及列值  k_v      hbase存储的是bytes,而字节的数组
        Put variantId = new Put(Bytes.toBytes(id));
        variantId.add(Bytes.toBytes("default_family"), Bytes.toBytes("variant_id"), Bytes.toBytes("1"));

        Put boxId = new Put(Bytes.toBytes(id));
        boxId.add(Bytes.toBytes("default_family"), Bytes.toBytes("box_id"), Bytes.toBytes("1"));

        Put value = new Put(Bytes.toBytes(id));
        value.add(Bytes.toBytes("default_family"), Bytes.toBytes("value"), Bytes.toBytes("1"));

        Put createTime = new Put(Bytes.toBytes(id));
        createTime.add(Bytes.toBytes("default_family"), Bytes.toBytes("create_time"), Bytes.toBytes(String.valueOf(System.currentTimeMillis())));
        Put status = new Put(Bytes.toBytes(id));
        status.add(Bytes.toBytes("default_family"), Bytes.toBytes("status"), Bytes.toBytes("1"));

        ArrayList<Put> puts = new ArrayList<>();
        puts.add(variantId);
        puts.add(boxId);
        puts.add(value);
        puts.add(createTime);
        puts.add(status);

        System.out.println(new Gson().toJson(puts));

        user1.put(puts);
        user1.close();
        System.out.println("## Done ##");
    }
}
