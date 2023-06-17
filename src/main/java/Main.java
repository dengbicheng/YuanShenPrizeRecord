import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/url.properties"));
        String url = properties.getProperty("url");

        long start = System.currentTimeMillis();
        GetStart getStart1 = new GetStart(url, "301");
        getStart1.start();
        GetStart getStart2 = new GetStart(url, "302");
        getStart2.start();
        GetStart getStart3 = new GetStart(url, "200");
        getStart3.start();
        GetStart getStart4 = new GetStart(url, "100");
        getStart4.start();
        System.out.println("程序耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
        // 分析概率
        GetStart.analysisProb();
        // 上传数据库
        GetStart.uploadDatabases();

    }
}

