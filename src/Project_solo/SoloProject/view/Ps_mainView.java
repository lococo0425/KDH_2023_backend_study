package Project_solo.SoloProject.view;

import java.util.Scanner;

public class Ps_mainView {

    private Ps_mainView(){}
    private static Ps_mainView ps_mainView = new Ps_mainView();
    public static Ps_mainView getInstance(){return ps_mainView;}

    Scanner scanner = new Scanner(System.in);

    public void run(){
        while (true){
            System.out.println("-----------------프로그램을 시작합니다------------------");
            System.out.println("1,회원가입  2,로그인");
            int ch = scanner.nextInt();
            scanner.nextLine();

            if(ch==1){
                Ps_MemberView.getInstance().signUp();
            }else if(ch==2){
                Ps_MemberView.getInstance().logIn();
            }else{
                System.out.println("<잘못된 기능 번호 입니다. 다시 입력하세요>");
            }
        }
    }

}
