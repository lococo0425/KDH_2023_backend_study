package Project_solo.SoloProject.model.dto;

public class Ps_LogDto {
    private int mno;
    private String log_message;
    private String selectge;

    public Ps_LogDto(){}
    public Ps_LogDto(int mno, String log_message, String selectge) {
        this.mno = mno;
        this.log_message = log_message;
        this.selectge = selectge;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getLog_message() {
        return log_message;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public String getSelectge() {
        return selectge;
    }

    public void setSelectge(String selectge) {
        this.selectge = selectge;
    }

    @Override
    public String toString() {
        return "LogDto{" +
                "mno=" + mno +
                ", log_message='" + log_message + '\'' +
                ", selectge='" + selectge + '\'' +
                '}';
    }
}
