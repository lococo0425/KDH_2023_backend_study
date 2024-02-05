package Project_solo.SoloProject.controller;

import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.model.dao.Ps_MovieDao;
import Project_solo.SoloProject.model.dao.Ps_memberDao;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

import java.util.HashMap;
import java.util.Scanner;

public class Ps_movieController {

    private Ps_movieController(){}
    private static Ps_movieController psMovieController = new Ps_movieController();
    public static Ps_movieController getInstance(){return psMovieController;}

    Scanner scanner = new Scanner(System.in);

    public String printMovie(Ps_MovieDto psMovieDto){
        String result = "";

        for( int i = 0 ; i< CSVReader.movielist.size(); i++ ){
            result = Ps_MovieDao.getInstance().printMovie(CSVReader.movielist.get(i) );
        }

        return result;
    }

    public String selectGerne(HashMap<Ps_memberDto, Ps_MovieDto> map){
        String result;

        result = Ps_MovieDao.getInstance().selectGerne(map);


        return result;
    }

    public boolean insertLogActive(HashMap<Ps_memberDto, Ps_MovieDto> map){
        boolean result= false;

        result = Ps_MovieDao.getInstance().logActive(map);

        return  result;
    }

    public String recommendMovie(Ps_memberDto ps_memberDto){
        String result = "";

        result = Ps_MovieDao.getInstance().recommendMovie(ps_memberDto);


        return  result;
    }

}