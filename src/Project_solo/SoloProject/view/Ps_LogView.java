package Project_solo.SoloProject.view;

import Project_solo.SoloProject.model.dto.Ps_LogDto;

public class Ps_LogView {
    //1.싱글톤 객체 생성
    private Ps_LogView(){}
    public static Ps_LogView logView = new Ps_LogView();
    public static Ps_LogView getInstance(){return  logView;}

    public void saveLog(){
        System.out.println("로그를 저장합니다.");

        Ps_LogDto psLogDto = new Ps_LogDto();

    }

}
