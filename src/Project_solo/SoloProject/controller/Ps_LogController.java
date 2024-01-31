package Project_solo.SoloProject.controller;

public class Ps_LogController {
    private Ps_LogController(){}
    public static Ps_LogController logController = new Ps_LogController();
    public static Ps_LogController getInstance(){return  logController;}
}
