package Project_solo;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class log {
    private static final List<String> inputList = new ArrayList<>();



    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            while (true){
                System.out.print("저장할 로그 : ");
                String savelog = scanner.nextLine();

                if(savelog.equalsIgnoreCase("exit")){
                    break;
                }
                inputList.add(savelog);

                logInput("입력 값 : " +savelog);
                logIntoSql("입력값 : " +savelog);
            }
            System.out.println(inputList);


        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private static void logInput(String logMessage) {
        try (FileWriter writer = new FileWriter("inputLog.txt", true)) {
            // 로그 파일에 메시지 추가
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logIntoSql(String logMessage) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/maindb";
        String username = "root";
        String userpassword = "1234";

        try(Connection connection = DriverManager.getConnection(url, username, userpassword)){

            String sql = "INSERT INTO logs(log_message) VALUES (?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1,logMessage);
                preparedStatement.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


