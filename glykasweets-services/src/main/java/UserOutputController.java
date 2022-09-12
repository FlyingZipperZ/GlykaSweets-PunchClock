import com.excel.BuildExcel;
import com.glykasweets.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;

public class UserOutputController {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionClass c = new ConnectionClass();
        Connection con = c.getConnection();

        BuildExcel.create(con);
        con.close();
    }
}
