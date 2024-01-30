package Project_solo.SoloProject.view;

import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.controller.Ps_movieController;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;

import java.util.Scanner;

public class Ps_MovieView {

    private Ps_MovieView(){}
    private static Ps_MovieView psMovieView = new Ps_MovieView();
    public static Ps_MovieView getInstance(){return psMovieView;}

    public static void printMovie(){
            Scanner scanner = new Scanner(System.in);
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
                System.out.println("영화 장르를 입력하세요");
                String genre = scanner.nextLine();





            }

    }
}
