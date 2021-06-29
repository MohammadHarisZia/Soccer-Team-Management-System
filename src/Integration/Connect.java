package Integration;

import java.sql.*;

public class Connect {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
            PreparedStatement P2=conn.prepareStatement("select teamid from team where teamname like ?");
            P2.setString(1,"%"+"Madrid"+"%");
            ResultSet resultSet2=P2.executeQuery();

            if(!resultSet2.isBeforeFirst())
                System.out.println("NULL");

            while (resultSet2.next()) {
                System.out.println(resultSet2.getString(1));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
