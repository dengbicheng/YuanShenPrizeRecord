import HttpTool.Request;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 继承JointUrl 获取父类方法成员变量使用更简单只需要子类给父类两个参数并启动就能直接获取数据
 */
public class GetStart extends JointUrl{

    // 将map集合用静态变量修饰可以共享里面的数据
    private static Map<String, List<ItemRecord>> map = new HashMap<>();
    {
        // 初始化每一个卡池的集合列表
        // key命名为卡池信息
        map.put(getGachaType(),new ArrayList<>());
    }

    public GetStart(String url,String gachaType) {
        super(url,gachaType);
    }

    public void start() throws IOException, InterruptedException {
        // 初始化页数
        int page = 1;
        while (true){
            String json = Request.get(getJointUrl());
            List<ItemRecord> arr = JsonAnalysis.analysis(json);
            if (arr.size() == 0){
                System.out.println("=======结束=======");
                break;
            }
            for (ItemRecord itemRecord: arr) {
                // 将每个对象添加到arraylist集合中
                map.get(getGachaType()).add(itemRecord);
            }
            // 每次循环后页数加一
            setPage(page++);
            // 每次拿取最后一次的id，这个id决定了你在哪里开始爬取
            setEndId(arr.get(arr.size()-1).getId());
            // 这里阻塞200毫秒不然爬太快会出错的
            Thread.sleep(300);
        }
    }

    // 调用该方法返回Map集合
    public static Map<String, List<ItemRecord>> getMap() {
        return map;
    }

    public static void uploadDatabases() throws SQLException {
        Connect.uploadData(map);
    }

    public static void analysisProb(){
        LotteryAnalysis.oddsAnalysis(map);
    }
}
