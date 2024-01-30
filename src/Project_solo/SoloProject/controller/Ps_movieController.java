package Project_solo.SoloProject.controller;

import Project_solo.SoloProject.model.dao.Ps_MovieDao;
import Project_solo.SoloProject.model.dao.Ps_memberDao;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;

import java.util.Scanner;

public class Ps_movieController {

    private Ps_movieController(){}
    private static Ps_movieController psMovieController = new Ps_movieController();
    public static Ps_movieController getInstance(){return psMovieController;}

    Scanner scanner = new Scanner(System.in);

    public String printMovie(Ps_MovieDto psMovieDto){
        String result = "";


        result = Ps_MovieDao.getInstance().printMovie(psMovieDto);

        return result;
    }

    public String selectGerne(){
        String result;

        result = Ps_MovieDao.getInstance().selectGerne(new Ps_MovieDto());

        return result;
    }
}
