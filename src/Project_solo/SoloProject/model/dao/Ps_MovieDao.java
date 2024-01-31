package Project_solo.SoloProject.model.dao;


import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

import java.sql.PreparedStatement;
import java.util.List;

public class Ps_MovieDao extends PsDao{

    private Ps_MovieDao(){}
    private static Ps_MovieDao psMovieDao = new Ps_MovieDao();
    public static Ps_MovieDao getInstance(){return psMovieDao;}

    public String printMovie(Ps_MovieDto psMovieDto){
        String result = "";
        try{
            result = "INSERT INTO movies (movie_id, movie_title, director, producer, income_company, " +
                    "distribution_company, release_date, movie_type, movie_style, nationality, total_screen_count, " +
                    "sales_price, viewing_number, seoul_sales_price, seoul_viewing_number, genre, grade, movie_subdivision) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("result = " + result);


            try(PreparedStatement preparedStatement = connection.prepareStatement(result)) {
                preparedStatement.setString(1, psMovieDto.getMovieId().toString());
                preparedStatement.setString(2, psMovieDto.getMovieName());
                preparedStatement.setString(3, psMovieDto.getDirectorName());
                preparedStatement.setString(4, psMovieDto.getMakerName());
                preparedStatement.setString(5, psMovieDto.getIncomeCompanyName());
                preparedStatement.setString(6, psMovieDto.getDistributionCompanyName());
                preparedStatement.setString(7, psMovieDto.getOpeningDate());
                preparedStatement.setString(8, psMovieDto.getMovieTypeName());
                preparedStatement.setString(9, psMovieDto.getMovieStyleName());
                preparedStatement.setString(10, psMovieDto.getNationalityName());
                preparedStatement.setInt(11, psMovieDto.getTotalScreenCount());
                preparedStatement.setDouble(12, psMovieDto.getSalesPrice());
                preparedStatement.setInt(13, psMovieDto.getViewingNumber());
                preparedStatement.setDouble(14, psMovieDto.getSeoulSalesPrice());
                preparedStatement.setInt(15, psMovieDto.getSeoulViewingNumber());
                preparedStatement.setString(16, psMovieDto.getGenreName());
                preparedStatement.setString(17, psMovieDto.getGradeName());
                preparedStatement.setString(18, psMovieDto.getMovieSubdivisionName());

                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     return result;
    }


    public String selectGerne(Ps_MovieDto psMovieDto){
        String result = "";
        try{
            String sql = "select * from movies where genre =?";
            System.out.println("쿼리"+sql);
            System.out.println("인풋"+psMovieDto.getGradeName());

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,psMovieDto.getGenreName());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("genre");
                System.out.println("result = " + result);
                //로그남기기
                Ps_memberDto ps_memberDto = new Ps_memberDto(); // mid를 사용하기 위해 선언
                logActive(ps_memberDto.getMemberid(),psMovieDto.getGenreName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //로그 남기기
    private void logActive(String mid, String logMessage){
        try{
            String sql = "insert into logs(mno,log_message) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,mid);
            preparedStatement.setString(2,logMessage);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
