package Project_solo.SoloProject.view;

import Project_solo.SoloProject.CSV.CSVReader;
import Project_solo.SoloProject.controller.Ps_memberController;
import Project_solo.SoloProject.controller.Ps_movieController;
import Project_solo.SoloProject.model.dto.Ps_MovieDto;
import Project_solo.SoloProject.model.dto.Ps_memberDto;
import Project_solo.가중치부여.DynamicWeight;

import java.sql.PreparedStatement;
import java.util.*;

public class Ps_MovieView {
    // 가중치를 위한 list 선언
    public static List<String> list = new ArrayList<>();
    public static List<Double> weights = new ArrayList<>();
    private static boolean isInitialized = false; // CSVReader의 movielist 초기화 여부를 나타내는 변수

    private Ps_MovieView() {}
    private static Ps_MovieView psMovieView = new Ps_MovieView();
    public static Ps_MovieView getInstance() { return psMovieView; }
    static Scanner scanner = new Scanner(System.in);

    public static void printMovie() {
        // movielist가 초기화되지 않은 경우에만 초기화하도록 수정
        if (!isInitialized) {
            CSVReader.main(new String[0]);
            isInitialized = true;
        }

        while (true) {
            System.out.println("-------------------영화-------------------");
            System.out.println("1.전체영화보기, 2.선택장르영화보기 3.추천영화");
            int ch = scanner.nextInt();
            scanner.nextLine();

            if (ch == 1) {
                Ps_MovieDto psmovieDto = new Ps_MovieDto();
                String result = Ps_movieController.getInstance().printMovie(psmovieDto);
            } else if (ch == 2) {
                Ps_MovieView.selectGerne();
            } else if (ch == 3) {
                System.out.println("영화 추천합니다 ");
                Ps_MovieView.recommendMovie();
            }
        }
    }

    public static void selectGerne() {
        System.out.print("영화 장르를 입력하세요 : ");
        String genre = scanner.nextLine();

        List<String> availableGenres = getAvailableGenres();

        if (!availableGenres.contains(genre)) {
            System.out.println("해당 장르가 존재하지 않습니다.");
            return;
        }


        // 객체화
        Ps_MovieDto psMovieDto = new Ps_MovieDto();
        psMovieDto.setGenreName(genre);

        // Ps_memberDto 객체 생성
        Ps_memberDto psMemberDto = new Ps_memberDto();
        // 현재 로그인한 회원의 정보를 가져와서 설정
        psMemberDto.setMemberid(Ps_memberController.nowlogin.getMemberid());
        HashMap<Ps_memberDto, Ps_MovieDto> map = new HashMap<>();
        map.put(psMemberDto, psMovieDto);

        // 결과 출력
        String result = Ps_movieController.getInstance().selectGerne(map);

        if (!result.isEmpty() && result.equals(psMovieDto.getGenreName())) {
            System.out.println(psMovieDto.getGenreName() + "에 해당하는 영화입니다.");
            List<String> MovieListge = new ArrayList<>();

            // 장르에 맞는 영화 출력 구문
            for (int i = 0; i < CSVReader.movielist.size(); i++) {
                String currentSelectge = CSVReader.movielist.get(i).getGenreName();
                if (currentSelectge.equals(result)) {
                    MovieListge.add(String.valueOf(CSVReader.movielist.get(i)));
                }
            }
            System.out.println(MovieListge + "\n");
        } else {
            System.out.println("해당 장르가 존재하지 않습니다.");
        }
    }

    public static void recommendMovie() {
        // 추천 영화 보여주기 + 가중치 설정
        System.out.println("추천 영화 입니다. ");

        Ps_memberDto ps_memberDto = new Ps_memberDto();
        List<String> result = Ps_movieController.getInstance().recommendMovie(ps_memberDto);

        System.out.println(result);//디버깅
        if (!result.isEmpty()) {
            list.add(result.toString());
            System.out.println(result);//디버깅용
            System.out.println(list);

            // 가중치 부분
            for (int i = 0; i < list.size(); i++) {
                weights.add(0.1);
            }

            int count = 0;
            Random random = new Random();
            for (int j = 0; j < 100; j++) {
                String selectValue = dynamicWeightValues(list, weights, count, random);

                if (selectValue.equals(list.get(j))) {
                    count++;
                }
                System.out.println("추천된 영화 장르 : " + selectValue);
            }
            System.out.println("해당 영화장르가 나온 횟수 : " + count);

            List<String> MovieListge = new ArrayList<>();
            for (int i = 0; i < CSVReader.movielist.size(); i++) {
                String currentSelectge = CSVReader.movielist.get(i).getGenreName();
                if (currentSelectge.equals(result)) {
                    MovieListge.add(String.valueOf(CSVReader.movielist.get(i)));
                }
            }
            System.out.println(MovieListge);
        }
    }

    public static String dynamicWeightValues(List<String> list, List<Double> weights, int count, Random random) {
        double totalWeight = 0.0;
        // 선택된 횟수에 따른 가중치 값 조절 하기
        weights.set(0, weights.get(0) + count * 0.01);

        // 전체 가중치 값 더하기
        for (double w : weights) {
            totalWeight += w;
        }
        double randomValue = random.nextDouble();
        double cumulativeWeight = 0.0;
        for (int i = 0; i < list.size(); i++) {
            cumulativeWeight += weights.get(i);
            if (randomValue < cumulativeWeight) {
                return list.get(i);
            }
        }
        return list.toString();
    }


    private static List<String> getAvailableGenres() {
        List<String> availableGenres = new ArrayList<>();
        for (Ps_MovieDto movie : CSVReader.movielist) {
            String genreName = movie.getGenreName();
            if (!availableGenres.contains(genreName)) {
                availableGenres.add(genreName);
            }
        }
        return availableGenres;
    }
}