package Project_solo.SoloProject.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PsDao {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    protected PsDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/psdb","root","1234");
            System.out.println("<연동성공>");
        }catch (Exception e){
            System.out.println("<연동실패>");
            e.printStackTrace();
        }
    }
}
//com.mysql.cj.jdbc.Driver