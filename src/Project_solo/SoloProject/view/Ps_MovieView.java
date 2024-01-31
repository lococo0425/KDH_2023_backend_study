package Project_solo.SoloProject.view;

import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.controller.Ps_movieController;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Ps_MovieView {

    private Ps_MovieView(){}
    private static Ps_MovieView psMovieView = new Ps_MovieView();
    public static Ps_MovieView getInstance(){return psMovieView;}
    static Scanner scanner = new Scanner(System.in);
    public static void printMovie(){

            System.out.println("-------------------영화-------------------");
            System.out.println("1.전체영화보기, 2.선택장르영화보기");
            int ch = scanner.nextInt();
            scanner.nextLine();


            if(ch==1){
                String[] args = new String[0];
                CSVReader.main(args);
                Ps_MovieDto psmovieDto = new Ps_MovieDto();
                String result = Ps_movieController.getInstance().printMovie(psmovieDto);

            }else if(ch==2){
                Ps_MovieView.selectGerne();
            }

    }

    public static void selectGerne(){
        System.out.print("영화 장르를 입력하세요 : ");
        String genre = scanner.nextLine();

        //객체화
        Ps_MovieDto psMovieDto = new Ps_MovieDto();
        psMovieDto.setGenreName(genre);

        //결과 출력
        String result = Ps_movieController.getInstance().selectGerne();

        if(!result.isEmpty() && result.equals(psMovieDto.getGenreName())){
            System.out.println(psMovieDto.getGenreName()+"에 해당 하는 영화입니다.");
        } else {
            System.out.println("해당 장르가 존재하지 않습니다.");
        }
    }
}
