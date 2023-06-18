# YuanShenPrizeRecord

YuanShenPrizeRecord 是一个用于获取原神游戏的抽卡记录数据的项目。它提供了一个简单的方式来获取指定卡池类型的抽卡记录数据，并支持将数据上传到数据库和分析概率。

## 项目地址

[Github](https://github.com/dengbicheng/YuanShenPrizeRecord)
[Gitee](https://gitee.com/deng-bicheng/YuanShenPrizeRecord)

## 项目结构

```
YuanShenPrizeRecord
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── HttpTool                      # HTTP 工具包
│   │   │   ├── Connect                       # 数据库连接类
│   │   │   ├── GetStart                      # 启动类
│   │   │   ├── ItemRecord                    # 抽卡记录数据模型
│   │   │   ├── JointUrl                      # URL 拼接类
│   │   │   ├── JsonAnalysis                  # JSON 数据解析类
│   │   │   ├── LotteryAnalysis               # 抽卡记录概率分析类
│   │   │   └── Main                          # 主类
│   │   └── resources
│   │       ├── Druid.properties              # 数据库连接配置文件
│   │       ├── url.properties                # URL 配置文件         
├── .gitignore                                # Git 忽略文件配置
├── pom.xml                                   # Maven 项目配置文件
└── README.md                                 # 项目说明文档
```
## 功能特性

- 连接指定的 URL 获取原神游戏的抽卡记录数据
- 支持指定卡池类型进行数据获取
- 将获取的数据上传到数据库
- 分析抽卡概率

## 使用方法

### 1. 配置 文件

在 `src/main/url.properties` 文件中配置要连接的 URL。请根据实际情况修改文件中的 `url` 属性，指定正确的 URL。



在`src/main/druid.properties`文件中配置你要连接数据库的url，用户名和密码

### 2. 运行主程序

运行 `Main` 类的 `main` 方法来启动程序。程序将按照配置的 URL 和卡池类型进行数据获取、分析和上传到数据库。

```Java
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        // 加载 URL 配置
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/url.properties"));
        String url = properties.getProperty("url");

        // 创建 GetStart 实例并启动数据获取
        GetStart getStart1 = new GetStart(url, "301");
        getStart1.start();
        GetStart getStart2 = new GetStart(url, "302");
        getStart2.start();
        GetStart getStart3 = new GetStart(url, "200");
        getStart3.start();
        GetStart getStart4 = new GetStart(url, "100");
        getStart4.start();

        // 分析概率
        GetStart.analysisProb();

        // 上传数据库
        GetStart.uploadDatabases();
    }
}
```

### 3. 数据获取

通过创建 `GetStart` 实例并传入 URL 和卡池类型，可以调用 `start` 方法开始获取数据。

```
GetStart getStart = new GetStart(url, gachaType);
getStart.start();
```

### 4. 分析概率

调用 `analysisProb` 方法可以对获取的抽卡记录数据进行概率分析。

```
GetStart.analysisProb();
```

### 5. 上传数据库

调用 `uploadDatabases` 方法可以将获取的抽卡记录数据上传到数据库。

```java
GetStart.uploadDatabases();
```

## 依赖项

此项目使用以下依赖项：

```xml
<dependencies>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.3</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId> <!-- 修改为你要使用的数据库驱动的groupId -->
            <artifactId>mysql-connector-java</artifactId> <!-- 修改为你要使用的数据库驱动的artifactId -->
            <version>8.0.26</version> <!-- 修改为你要使用的数据库驱动的版本号 -->
        </dependency>

        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.14.3</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.8</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>
```

请确保在运行程序之前已正确配置和引入所需的依赖项。

## 建表sql
```sql
create table data_recod
(
    `id`         int primary key auto_increment comment 'id',
    `uid`        char(12) comment '用户uid',
    `name`       varchar(10) comment '奖品名',
    `gacha_type` char(3) comment '卡池信息',
    `item_type`  char(2) comment '奖品类型[武器/角色]',
    `rank_type`  char(1) comment '奖品等级[3/4/5]',
    `data_id`    char(30) unique comment '原神服务器返回的id',
    `gain_time`  datetime comment '抽到奖品的时间',
    `up_time` timestamp default current_timestamp comment '上传到我的数据库的时间'
)CHARACTER SET utf8;
```