package Project_solo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;



public class Login {//class s

    static String id = "";
    static String pw = "";
    static List<String> memberNoList = new ArrayList<>();
    static List<String> idList = new ArrayList<>();
    static List<String> pwList = new ArrayList<>();
    static boolean loginSuccess = false;


    public static void main(String[] args) { //main s
        Scanner scanner = new Scanner(System.in);


        while(true) {//while s
            startText();
            int ch = scanner.nextInt();
            scanner.nextLine();

            if(ch==1){//로그인

                System.out.print("아이디 : ");
                id = scanner.nextLine();
                System.out.print("비밀번호 : ");
                pw = scanner.nextLine();


                loginSuccessM();

            while(true) {
                if (loginSuccess) {

                    afterLoginText();
                    int afterch = scanner.nextInt();
                    scanner.nextLine();
                    if(afterch==1){
                        System.out.println("내가 본 영화 목록입니다..");
                    }else if(afterch==2){
                        System.out.println("추천하는 영화들 입니다.");
                    }else if(afterch==3){
                        break;

                    }

                } else {
                    System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
                    break;
                }
            }

            }else if(ch==2){//회원가입 아이디 비밀번호
                System.out.print("회원가입을 위한 아이디를 입력하세요 : ");
                id = scanner.nextLine();


                if(idList.contains(id)){
                    System.out.println("중복된 아이디가 존재합니다.");
                }else{
                    idList.add(id);
                    System.out.println("비밀번호를 입력하세요");
                    pw = scanner.nextLine();
                    pwList.add(pw);

                    memberNoList.add(String.valueOf(idList.size()));
                }
                System.out.println(memberNoList);
                System.out.println(idList);
                System.out.println(pwList);

            }


        }//while e
    }//main e




    public static void loginSuccessM(){
        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i).equals(id) && pwList.get(i).equals(pw)) {
                loginSuccess = true;
                break;
            }
        }
    }

    public static void startText(){
        System.out.println("-------------------------------");
        System.out.println("1. 로그인  | 2. 회원가입");
        System.out.println("-------------------------------");
    }
    public static void afterLoginText(){
        System.out.println("---------------------------------");
        System.out.println("1. 내영화 | 2. 추천영화 | 3. 로그아웃");
        System.out.println("---------------------------------");
    }
}// class e



