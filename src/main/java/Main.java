import HttpTool.Request;

import java.io.IOException;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;




public class Main {
    public static void main(String[] args) throws IOException {

        JSONObject jsonObject = JSON.parseObject(Request.get());
        /*
           从解析后的 JSONObject 对象 jsonObject 中获取名为 "data" 的键对应的值，并将其转换为一个新的 JSONObject 对象。
           getJSONObject() 方法用于获取指定键的值，并将其转换为一个 JSONObject 对象。
         */
        JSONObject dataObject = jsonObject.getJSONObject("data");

        /*
          从上一步中获取到的 JSONObject 对象 dataObject 中获取名为 "list" 的键对应的值，并将其转换为一个 JSON 数组 (JSONArray) 对象。
          使用 toJavaList() 方法将该 JSON 数组对象转换为一个 Java 对象列表，其中每个元素都是 ItemRecord 类型的对象。
          toJavaList() 方法是 fastjson 库提供的方法，它将 JSON 数组转换为 Java 对象列表。
         */
        List<ItemRecord> itemRecords = dataObject.getJSONArray("list").toJavaList(ItemRecord.class);

        // 打印每个元素
        itemRecords.forEach(System.out::println);
    }
}