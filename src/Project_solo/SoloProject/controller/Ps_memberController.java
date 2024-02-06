package Project_solo.SoloProject.controller;

import Project_solo.SoloProject.model.dao.Ps_memberDao;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

public class Ps_memberController {

    private Ps_memberController(){}
    private static Ps_memberController ps_memberController = new Ps_memberController();
    public static Ps_memberController getInstance(){return ps_memberController;}


    public int signUp(Ps_memberDto ps_memberDto){
        int result=0;

        result = Ps_memberDao.getInstance().signUp(ps_memberDto);

        return result;
    }

    public boolean logIn(Ps_memberDto ps_memberDto){
        boolean result = false;
        result = Ps_memberDao.getInstance().logIn(ps_memberDto);

        return result;
    }

    public static Ps_memberDto nowlogin;
    public String loginId(){
        return Ps_memberDao.getInstance().loginId(nowlogin);
    }

//    public boolean insertLogActive(Ps_memberDto ps_memberDto){
//        boolean result = false;
//        result = Ps_memberDao.getInstance().insertLogActive(ps_memberDto);
//
//        return result;
//    }
}
