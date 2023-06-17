import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 连接数据库，上传操作
 */
public class Connect {
    private static DataSource dataSource = null;
    private static Connection connection;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/Druid.properties"));
            // 创建连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 获取连接
            connection = dataSource.getConnection();
            System.out.println("以获取连接");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadData(Map<String, List<ItemRecord>> map) throws SQLException {
        String[] arrays = {"301", "302", "200", "100"};
        PreparedStatement preparedStatement = null;
        for (String item : arrays) {
            if (map.get(item) != null) {
                for (int i = 0; i < map.get(item).size(); i++) {
                    ItemRecord itemRecord = map.get(item).get(i);
                    String uid = itemRecord.getUid();
                    String name = itemRecord.getName();
                    String gacha_type = itemRecord.getGachaType();
                    String item_type = itemRecord.getItemType();
                    String rank_type = itemRecord.getRankType();
                    String id = itemRecord.getId();
                    String time = itemRecord.getTime();
                    String sql =
                            "insert into data_recod(uid, name, gacha_type, item_type, rank_type, data_id, gain_time) " +
                                    "VALUES (?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, uid);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, gacha_type);
                    preparedStatement.setString(4, item_type);
                    preparedStatement.setString(5, rank_type);
                    preparedStatement.setString(6, id);
                    preparedStatement.setString(7, time);
                    try {
                        System.out.println(preparedStatement.executeUpdate() >= 1 ? "成功" : "失败");
                    } catch (SQLException e) {
                        // 错误码 1062 表示唯一键冲突
                        if (e.getErrorCode() == 1062) {
                            System.out.println("记录" + itemRecord + "已存在");
                        }
                    }

                }
            }
        }

        if (preparedStatement != null) {
            preparedStatement.close();
            System.out.println();
        }
        if (connection != null) {
            connection.close();
            System.out.println("连接已关闭");
        }
    }
}
