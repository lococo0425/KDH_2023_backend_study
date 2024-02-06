package Project_solo.SoloProject.view;

import Project_solo.SoloProject.controller.Ps_memberController;
import Project_solo.SoloProject.model.dao.Ps_memberDao;
import Project_solo.SoloProject.model.dto.Ps_memberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ps_MemberView {
    private Ps_MemberView(){}
    private static Ps_MemberView psMemberView = new Ps_MemberView();
    public static Ps_MemberView getInstance(){return psMemberView;}
    public static List<String>loginState = new ArrayList<>();



    Scanner scanner = new Scanner(System.in);


    //회원가입
    public void signUp(){
        System.out.print("아이디 : ");   String id = scanner.nextLine();
        System.out.print("비밀번호 : ");  String pw = scanner.nextLine();
        System.out.print("전화번호 : ");  String phone = scanner.nextLine();

        //객체화
        Ps_memberDto ps_memberDto = new Ps_memberDto();
        ps_memberDto.setMemberid(id); ps_memberDto.setMemberpw(pw); ps_memberDto.setMemberPhone(phone);

        //컨트롤러 전달
        int result = Ps_memberController.getInstance().signUp(ps_memberDto);
    }

    //로그인
    public void logIn(){

        System.out.print("아이디 : "); String id = scanner.nextLine();
        System.out.print("비밀번호 : ");    String pw = scanner.nextLine();

        //객체화
        Ps_memberDto ps_memberDto = new Ps_memberDto();
        ps_memberDto.setMemberid(id);   ps_memberDto.setMemberpw(pw);

        //컨트롤러 전달 후 결과 값 받기
        boolean result = Ps_memberController.getInstance().logIn(ps_memberDto);
        //결과출력(보여주기)
        if(result){
            System.out.println("<로그인 성공>");
            Ps_MovieView.printMovie();


        }else {
            System.out.println("<로그인 실패>");
        }

    }








}
