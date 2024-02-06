package Project_solo.SoloProject.CSV;

import Project_solo.SoloProject.model.dto.Ps_MovieDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    // CSVReader 클래스의 movielist를 한 번만 초기화되도록 static으로 선언
    public static List<Ps_MovieDto> movielist = null;

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        // movielist가 null이면 CSV 파일을 읽어 초기화
        if (movielist == null) {
            movielist = csvReader.readCSV();
        }
        // movielist가 null이 아니면 이미 초기화되어 있으므로 다시 초기화할 필요 없음
        // 따라서 main 메서드에서는 movielist를 출력하는 코드만 포함
        for (int i = 0; i < movielist.size(); i++) {
            System.out.println(movielist.get(i));
        }
    }

    public List<Ps_MovieDto> readCSV() {
        List<Ps_MovieDto> movieList = new ArrayList<>();
        File csv = new File("src/Project_solo/KC_KOBIS_BOX_OFFIC_MOVIE_INFO_202309.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                Ps_MovieDto ps_MovieDto = createMovieInfo(lineArr);
                movieList.add(ps_MovieDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movieList;
    }

    private Ps_MovieDto createMovieInfo(String[] lineArr) {
        int no = parseInteger(lineArr[0]);
        String movieName = lineArr.length > 1 ? lineArr[1] : "";
        String directorName = lineArr.length > 2 ? lineArr[2] : "";
        String makerName = lineArr.length > 3 ? lineArr[3] : "";
        String incomeCompanyName = lineArr.length > 4 ? lineArr[4] : "";
        String distributionCompanyName = lineArr.length > 5 ? lineArr[5] : "";
        String openingDate = lineArr.length > 6 ? lineArr[6] : "";
        String movieTypeName = lineArr.length > 7 ? lineArr[7] : "";
        String movieStyleName = lineArr.length > 8 ? lineArr[8] : "";
        String nationalityName = lineArr.length > 9 ? lineArr[9] : "";
        int totalScreenCount = lineArr.length > 10 ? parseInteger(lineArr[10]) : 0;
        double salesPrice = lineArr.length > 11 ? parseDouble(lineArr[11]) : 0.0;
        int viewingNumber = lineArr.length > 12 ? parseInteger(lineArr[12]) : 0;
        double seoulSalesPrice = lineArr.length > 13 ? parseDouble(lineArr[13]) : 0.0;
        int seoulViewingNumber = lineArr.length > 14 ? parseInteger(lineArr[14]) : 0;
        String genreName = lineArr.length > 15 ? lineArr[15] : "";
        String gradeName = lineArr.length > 16 ? lineArr[16] : "";
        String movieSubdivisionName = lineArr.length > 17 ? lineArr[17] : "";

        return new Ps_MovieDto(no, movieName, directorName, makerName, incomeCompanyName, distributionCompanyName,
                openingDate, movieTypeName, movieStyleName, nationalityName, totalScreenCount, salesPrice,
                viewingNumber, seoulSalesPrice, seoulViewingNumber, genreName, gradeName, movieSubdivisionName);
    }

    private int parseInteger(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}