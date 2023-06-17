/**
 * 拼接Url
 */
public class JointUrl {
    private final String URL;
    private int size = 20;// 默认一次获取返回20次的数据
    private int page = 1;   // 默认从第一页开始
    private String endId = "0";  // id默认为0，这个id决定了你要从哪里开始获取【重要】
    private String gachaType;
    public JointUrl(String url,String gachaType) {
        this.URL = url;
        this.gachaType = gachaType;
    }

    public String getURL() {
        return URL;
    }
    public String getGachaType(){
        return gachaType;
    }

    // 修改页数
    public void setPage(int page) {
        this.page = page;
    }
    // 修改id
    public void setEndId(String endId) {
        this.endId = endId;
    }
    // 修改每页返回来的个数
    public void setSize(int size) {
        this.size = size;
    }

    public String getJointUrl() {
        String url = "https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?";
        // 截取字符串拿取重要的一部分
        String url2 = URL.split("\\?")[1].split("#")[0];
        String url3 = String.format("&gacha_type=%s&page=%s&size=%s&end_id=%s", gachaType, page, size, endId);
        return url + url2 + url3;
    }
}
