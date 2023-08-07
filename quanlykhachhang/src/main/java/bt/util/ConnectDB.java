package bt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String drive="com.mysql.cj.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/hai";
    private static final String usename="root";
    private  static final String password="0812706565";

    public  static Connection getconnection(){
        Connection connection=null;
        try {
            Class.forName(drive);
            connection= DriverManager.getConnection(url,usename,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } return connection;
    }
    public  static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
