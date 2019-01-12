import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestManageDBResource {


    public static void main(String [] args) throws SQLException{
        Connection conn = null;
        try{
            //conn = DriverManager.getConnection(dbUrl,username,password);
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            System.out.println("Connection established to MySQL Database");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally{
            conn.close();
        }
    }
}
