import calso.ConnectionClass;
import excel.BuildExcel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class UserOutputController {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionClass c = new ConnectionClass();
        Connection con = c.getConnection();
        BuildExcel.create(con);
        con.close();

//        }
    }
}
