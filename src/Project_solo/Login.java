
package Project_solo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    static int memberNum =1;
    static String id = "";
    static String pw = "";
    static List<Member1> memberList = new ArrayList<Member1>();
    static boolean loginSuccess = false;
    static boolean remove = false;
    static Scanner scanner = new Scanner(System.in);
    static Connection connection = null;
    public static void main(String[] args) throws SQLException {
        try { // db 관련 선언 s
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maindb", "root", "1234");
        }catch (Exception e){
            e.printStackTrace();
        } // db관련 선언 e


        while (true){
            startText();
            int ch = scanner.nextInt();
            scanner.nextLine();
            if(ch==1){
                System.out.println("회원가입을 시작합니다.");
                System.out.print("아이디를 입력하세요 : ");
                id = scanner.nextLine();
                System.out.println("비밀번호를 입력하세요 : ");
                pw = scanner.nextLine();
                if(idExists(id)) {
                    System.out.println("아이디가 존재합니다. 다른아이디를 사용하세요");
                }else  {

                    Member1 newMember1 = new Member1(memberNum,id, pw);
                    memberList.add(newMember1);

                    try{
                        System.out.println("연동성공");
                        String sql = "insert into clients values(?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,id);
                        preparedStatement.setString(2,pw);
                        preparedStatement.executeUpdate();
                    }catch (Exception e){
                        System.out.println("e = " + e);
                    }



                }
                System.out.println("회원가입이 완료 되었습니다.");
                memberNum++;
                for(int i=0; i< memberList.size();i++) {
                    System.out.println(memberList.get(i));
                } // 회원가입된 memberList 출력


            }else if(ch==2){
                System.out.println("로그인합니다.");
                System.out.print("로그인 할 아이디를 입력하세요 : ");
                String logInid = scanner.nextLine();
                System.out.print("로그인 할 비밀번호를 입력하세요 : ");
                String logInpw = scanner.nextLine();

                loginSuccess = false;
                for (int i = 0; i < memberList.size(); i++) {
                    Member1 member = memberList.get(i);
                    if (logInid.equals(member.getId()) && logInpw.equals(member.getPw())) {
                        loginSuccess = true;
                        break;
                    }
                }

                if (loginSuccess) {
                    System.out.println("로그인 성공!");
                    while (true){
                        int i=0;
                        Member1 loginMember = memberList.get(i);
                        String logInId = loginMember.getId().toString();
                    try{
                        System.out.println("연동성공");
                        String sql = "insert into logs (id) values (?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,logInId);
                        preparedStatement.executeUpdate();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    //CSVReaderWithPagination.main(args);  // csv를 활용한 영화 리스트 가져오기

                    afterLoginText();
                    int afterch = scanner.nextInt();
                    scanner.nextLine();

                    if (afterch == 1) {
                        System.out.println("내가본 영화");
                        try{
                            System.out.println("연동성공");
                            String sql = "select * from logs where id = ?";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1,id);
                            preparedStatement.executeQuery();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (afterch == 2) {
                        System.out.println("추천영화입니다.");
                    } else if (afterch == 3) {
                        break;
                    }

                }
                } else {
                    System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
                }


            }else if(ch==3){
                System.out.println("회원을 탈퇴하시겠습니까? : Y/N");
                String yn = scanner.nextLine();
                if(yn.equalsIgnoreCase("Y")){
                    System.out.print("탈퇴할 아이디를 입력하세요");
                    id=scanner.nextLine();
                    System.out.print("탈퇴할 비밀번호를 입력하세요");
                    pw=scanner.nextLine();

                   deleteM();

                    if(remove){
                        System.out.println("회원 탈퇴 성공");
                        try{
                            System.out.println("연동성공");
                            String sql = "delete from clients where 아이디 = ?";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1,id);
                            preparedStatement.executeUpdate();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("아이디 비밀번호가 다릅니다.");
                    }
                }

            }else if(ch==4) {
                System.out.println("회원을 출력합니다.");
                try{
                    System.out.println("연동성공");
                    String sql = "select * from clients";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.executeQuery();
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(memberList);


            }else{
                System.out.println("잘못된 입력입니다.");
            }



        }
    }



    public static void startText() {
        System.out.println("---------------------------------------------");
        System.out.println("1. 회원가입  | 2. 로그인 |3. 회원탈퇴 | 4.회원출력");
        System.out.println("---------------------------------------------");
    }
    public static void afterLoginText() {
        System.out.println("---------------------------------");
        System.out.println("1. 내영화 | 2. 추천영화 | 3. 로그아웃");
        System.out.println("---------------------------------");
    }
    public static boolean idExists(String id){
        for(int i=0; i< memberList.size();i++){
            Member1 member1 = memberList.get(i);
            if(id.equals(member1.getId())){
                return true;
            }
        }
        return false;
    }
    public static void deleteM(){
        for (int i = 0; i < memberList.size(); i++) {
            Member1 member = memberList.get(i);
            if (id.equals(member.getId()) && pw.equals(member.getPw())) {
                memberList.remove(member);
                remove = true;
                break;
            }
        }
    }

}

class Member1 {
    private String id;
    private String pw;

    private int memberNum;
    public Member1(int memberNum, String id, String pw) {
        this.memberNum = memberNum;
        this.id = id;
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "Member1{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", memberNum=" + memberNum +
                '}';
    }

    public Object getId() {
        return id;
    }
    public Object getPw(){
        return pw;
    }
    public Object getmemberNum(){
        return memberNum;
    }
}

