package Project_solo.SoloProject.model.dao;

public class Ps_LogDao extends PsDao{
    private Ps_LogDao(){}
    public static Ps_LogDao logDao = new Ps_LogDao();
    public static Ps_LogDao getInstance(){return logDao;}

}
