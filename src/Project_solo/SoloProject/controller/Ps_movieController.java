package Project_solo.SoloProject.controller;

import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.model.dao.Ps_MovieDao;
import Project_solo.SoloProject.model.dao.Ps_memberDao;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
        String result = "";

        // Ps_memberDto 객체 생성
        Ps_memberDto psMemberDto = new Ps_memberDto();
        // 현재 로그인한 회원의 정보를 가져와서 설정
        psMemberDto.setMemberid(Ps_memberController.nowlogin.getMemberid());

        map.put(psMemberDto, map.get(psMemberDto));

        result = Ps_MovieDao.getInstance().selectGerne(map);

        return result;
    }

    public boolean LogActive(HashMap<Ps_memberDto, Ps_MovieDto> map){
        boolean result= false;

        result = Ps_MovieDao.getInstance().logActive(map);

        return  result;
    }

    public List<String> recommendMovie(Ps_memberDto ps_memberDto){
        List<String>result = null;
        ps_memberDto = Ps_memberController.nowlogin;
        result = Ps_MovieDao.getInstance().recommendMovie(ps_memberDto);


        return  result;
    }

    public String todaylog(Ps_memberDto ps_memberDto){
        String result = Ps_MovieDao.getInstance().todaylog(ps_memberDto);

        return result;
    }

}