
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)// 过滤不用的字段
public class ItemRecord {
    private String uid; // Uid

    private String name;    // 奖品名

    @JsonProperty("gacha_type") // 通过注解类更改映射名
    private String gachaType;  // 卡池信息 武器池302/up池301/常驻池200/新手池100

    @JsonProperty("item_type")
    private String itemType;    // 奖品类型  角色/人物

    @JsonProperty("rank_type")
    private String rankType;    // 奖品等级 5,4,3
    private String id;  // 唯一Id
    private String time;    // 获得奖品的时间

    public ItemRecord(String uid, String name, String gachaType, String itemType, String rankType, String id, String time) {
        this.uid = uid;
        this.name = name;
        this.gachaType = gachaType;
        this.itemType = itemType;
        this.rankType = rankType;
        this.id = id;
        this.time = time;
    }
    public ItemRecord() {
        // 默认无参构造函数
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGachaType() {
        return gachaType;
    }

    public void setGachaType(String gachaType) {
        this.gachaType = gachaType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemRecord{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gachaType='" + gachaType + '\'' +
                ", itemType='" + itemType + '\'' +
                ", rankType='" + rankType + '\'' +
                ", id='" + id + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
