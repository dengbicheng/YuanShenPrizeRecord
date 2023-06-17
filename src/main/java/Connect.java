package Connect;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 上传到数据库
 */
public class Connect {
    private static final DataSource dataSource;
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/Druid.properties"));
            // 创建连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadData() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql =
                "insert into data_recod(uid, name, gacha_type, item_type, rank_type, data_id, gain_time) " +
                        "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"");
        preparedStatement.setString(2,"");
        preparedStatement.setString(3,"");
        preparedStatement.setString(4,"");
        preparedStatement.setString(5,"");
        preparedStatement.setString(6,"");
        preparedStatement.setString(7,"");
        preparedStatement.execute();
    }
}
