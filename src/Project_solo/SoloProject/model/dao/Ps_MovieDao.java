package Project_solo.SoloProject.model.dao;


import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public String selectGerne(HashMap<Ps_memberDto, Ps_MovieDto> map){
        String result = "";
        try{
            String sql = "select * from movies where genre =?";

            String logmessage ="";

            for (Map.Entry<Ps_memberDto, Ps_MovieDto> entry : map.entrySet()) {

                logmessage = entry.getValue().getGenreName();
            }

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,logmessage);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("genre");

                //로그남기기
                Ps_memberDto ps_memberDto = new Ps_memberDto(); // mid를 사용하기 위해 선언
                logActive(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //로그 남기기
    public boolean logActive(HashMap<Ps_memberDto, Ps_MovieDto> map){
        try{
            String sql = "insert into logs(mid,log_message) values(?,?)";
            String logmessage ="";
            String id= "";
            for (Map.Entry<Ps_memberDto, Ps_MovieDto> entry : map.entrySet()) {
                id = entry.getKey().getMemberid();
                logmessage = entry.getValue().getGenreName();
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);

            preparedStatement.setString(2,logmessage);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<String> recommendMovie(Ps_memberDto ps_memberDto){
        List<String> result = null;
        try {

            String sql = "select log_message from logs  where mid = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ps_memberDto.getMemberid());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result.add(ps_memberDto.getMemberid());

            }
            return result;


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
