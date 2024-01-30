package Project_solo.SoloProject.model.dao;

import Project_solo.SoloProject.model.dto.Ps_memberDto;

public class Ps_memberDao extends PsDao{
    private Ps_memberDao(){}
    private static  Ps_memberDao ps_memberDao = new Ps_memberDao();
    public  static  Ps_memberDao getInstance(){return ps_memberDao;}


    public int signUp(Ps_memberDto ps_memberDto){
        //로그인시 DB 값 넣기 .
        try{
            String sql = "insert into psmember(mid,mpw,mphone) values (?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ps_memberDto.getMemberid());
            preparedStatement.setString(2,ps_memberDto.getMemberpw());
            preparedStatement.setString(3, ps_memberDto.getMemberPhone());

            int count = preparedStatement.executeUpdate(); // SQL 실행후 insert된 레코드 개수 반환하기
            if(count==1){return 0;} // 레코드가 1개이면 회원가입 성공.

        }catch (Exception e){
            e.printStackTrace();
        }

        return 1;
    }

    public boolean logIn(Ps_memberDto ps_memberDto){
        try {
            String sql = "select mno from psmember where mid=? and mpw =?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ps_memberDto.getMemberid());
            preparedStatement.setString(2, ps_memberDto.getMemberpw());


            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
