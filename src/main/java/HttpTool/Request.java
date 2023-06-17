package HttpTool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class Request {
    private static String url;
    private static String userAgent;
    static {
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("src/url.properties"));
            url = properties.getProperty("url1");
            userAgent = properties.getProperty("user-Agent");
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static String get() throws IOException {
        Document document = Jsoup.connect(url)
                // 设置请求头
                .header("User-Agent", userAgent)
                // 设置超时时间
                .timeout(10000)
                //忽略对响应内容类型的验证，即使内容类型不是HTML也不会抛出异常。
                .ignoreContentType(true)
                // 请求方法
                .get();
        return document.body().text();

    }
}
