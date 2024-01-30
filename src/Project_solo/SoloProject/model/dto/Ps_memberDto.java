package Project_solo.SoloProject.model.dto;

public class Ps_memberDto {
    //1.필드
    private int memberno;
    private String memberid;
    private String memberpw;
    private String memberPhone;

    //2.생성자
    public Ps_memberDto(){}
    public Ps_memberDto(int memberno, String memberid, String memberpw, String memberPhone) {
        this.memberno = memberno;
        this.memberid = memberid;
        this.memberpw = memberpw;
        this.memberPhone = memberPhone;
    }

    //3. getter/setter 메소드
    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMemberpw() {
        return memberpw;
    }

    public void setMemberpw(String memberpw) {
        this.memberpw = memberpw;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    @Override
    public String toString() {
        return "ps_memberDto{" +
                "memberno=" + memberno +
                ", memberid='" + memberid + '\'' +
                ", memberpw='" + memberpw + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                '}';
    }
}
