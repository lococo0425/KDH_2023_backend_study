package Project_solo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;



public class CSVReaderWithPagination {
    private static final int ITEMS_PER_PAGE = 5; // 페이지 설정
    //DB 연동
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/maindb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";


    public static void main(String[] args) throws SQLException {//ms
        Scanner scanner = new Scanner(System.in);
        CSVReaderWithPagination csvReader = new CSVReaderWithPagination();
        List<MovieInfo> movieList = csvReader.readCSV();

        List<MovieInfo> selectedGenreMovies = new ArrayList<>();

       // printAllMovies(movieList);  // 시작시 전체 페이지 출력

        //DB 구성 부분
        CSVReaderWithPagination csvReaders = new CSVReaderWithPagination();
        List<MovieInfo> movieInfoList = csvReaders.readCSV();

        try{
            Connection connection=DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("connection = " + connection);


            Class.forName("com.mysql.cj.jdbc.Driver");


            String sqld = "delete from movie";
            connection.prepareStatement(sqld).executeUpdate();



            String sql = "INSERT INTO movie (movie_id, movie_title, director, producer, income_company, " +
                    "distribution_company, release_date, movie_type, movie_style, nationality, total_screen_count, " +
                    "sales_price, viewing_number, seoul_sales_price, seoul_viewing_number, genre, grade, movie_subdivision) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println("sql = " + sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (MovieInfo movieInfo : movieInfoList) {
                System.out.println( movieInfo);

                preparedStatement.setString(1, movieInfo.getMovieId().toString());
                preparedStatement.setString(2, movieInfo.getMovieName());
                preparedStatement.setString(3, movieInfo.getDirectorName());
                preparedStatement.setString(4, movieInfo.getMakerName());
                preparedStatement.setString(5, movieInfo.getIncomeCompanyName());
                preparedStatement.setString(6, movieInfo.getDistributionCompanyName());
                preparedStatement.setString(7, movieInfo.getOpeningDate());
                preparedStatement.setString(8, movieInfo.getMovieTypeName());
                preparedStatement.setString(9, movieInfo.getMovieStyleName());
                preparedStatement.setString(10, movieInfo.getNationalityName());
                preparedStatement.setInt(11, movieInfo.getTotalScreenCount());
                preparedStatement.setDouble(12, movieInfo.getSalesPrice());
                preparedStatement.setInt(13, movieInfo.getViewingNumber());
                preparedStatement.setDouble(14, movieInfo.getSeoulSalesPrice());
                preparedStatement.setInt(15, movieInfo.getSeoulViewingNumber());
                preparedStatement.setString(16, movieInfo.getGenreName());
                preparedStatement.setString(17, movieInfo.getGradeName());
                preparedStatement.setString(18, movieInfo.getMovieSubdivisionName());

                // 쿼리 실행
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
            //오류 체크 구문
            System.out.println("오류발견 "+e);
        }



        //페이지별 5개씩 보여주기
        int page = 0;
        while (true) {
            System.out.println("현재 페이지: " + page);
            printMovies(selectedGenreMovies, page);

            System.out.println("이전 페이지: -1, 다음 페이지: 0, 특정 장르 보기: 1, 내가 선택한 장르 전체보기: 2, 종료: 3, 영화 선택 : 4");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 3) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (userInput == -1) {
                if (page > 0) {
                    page--;
                } else {
                    System.out.println("이전 페이지가 없습니다.");
                }
            } else if (userInput == 0) {
                int totalPages = (int) Math.ceil((double) selectedGenreMovies.size() / ITEMS_PER_PAGE);
                if (page < totalPages - 1) {
                    page++;
                } else {
                    System.out.println("다음 페이지가 없습니다.");
                }
            } else if (userInput == 1) {
                System.out.print("장르를 입력하세요: ");
                String selectedGenre = scanner.nextLine();
                selectedGenreMovies = filterMoviesByGenre(movieList, selectedGenre);
                page = 0; // 새로운 장르를 선택할 때 페이지를 초기화
                log.logIntoSql(selectedGenre);

            } else if (userInput == 2) {
                printAllMovies(selectedGenreMovies);
            } else if (userInput ==4 ){
                System.out.println("영화 정보를 입력하세요 (영화제목)");
                String mn = scanner.nextLine();
                System.out.println("해당 영화의 장르를 입력하세요 ");
                String ge = scanner.nextLine();
                try{
                    Connection connection=DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    String sql = "insert into logs (moviename, moviegerne) VALUES (?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,mn);
                    preparedStatement.setString(2,ge);
                    preparedStatement.executeUpdate();


                }catch (Exception e){
                    e.printStackTrace();
                }

            }else {
                System.out.println("올바르지 않은 입력입니다. 다시 시도하세요.");
            }
        }

        scanner.close();

    }//me


    public static void printMovies(List<MovieInfo> movieList, int page) {
        int start = page * ITEMS_PER_PAGE;
        int end = Math.min((page + 1) * ITEMS_PER_PAGE, movieList.size());

        for (int i = start; i < end; i++) {
            System.out.println(movieList.get(i));
        }
    }

    public static void printAllMovies(List<MovieInfo> movieList) {
        for (MovieInfo movie : movieList) {
            System.out.println(movie);
        }
    }

    public static List<MovieInfo> filterMoviesByGenre(List<MovieInfo> movieList, String genre) {
        List<MovieInfo> filteredMovies = new ArrayList<>();
        for (MovieInfo movie : movieList) {
            if (movie.genreName.equals(genre)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    public List<MovieInfo> readCSV() { //CSV 읽어오기
        List<MovieInfo> movieList = new ArrayList<>();
        File csv = new File("C:\\Users\\504\\Desktop\\ezen_2023B_backend\\KDH_2023B_backend\\src\\Project_solo\\KC_KOBIS_BOX_OFFIC_MOVIE_INFO_202309.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            // Read the header to skip it
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                MovieInfo movieInfo = createMovieInfo(lineArr);
                movieList.add(movieInfo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return movieList;
    }

    private MovieInfo createMovieInfo(String[] lineArr) {

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
        UUID movieId = UUID.randomUUID();

        return new MovieInfo(no, movieName, directorName, makerName, incomeCompanyName, distributionCompanyName,
                openingDate, movieTypeName, movieStyleName, nationalityName, totalScreenCount, salesPrice,
                viewingNumber, seoulSalesPrice, seoulViewingNumber, genreName, gradeName, movieSubdivisionName, movieId);
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

    private static class MovieInfo {
        private int no;
        private String movieName;
        private String directorName;
        private String makerName;
        private String incomeCompanyName;
        private String distributionCompanyName;
        private String openingDate;
        private String movieTypeName;
        private String movieStyleName;
        private String nationalityName;
        private int totalScreenCount;
        private double salesPrice;
        private int viewingNumber;
        private double seoulSalesPrice;
        private int seoulViewingNumber;
        private String genreName;
        private String gradeName;
        private String movieSubdivisionName;

        private UUID movieId;

        
        public MovieInfo(int no, String movieName, String directorName, String makerName, String incomeCompanyName,
                         String distributionCompanyName, String openingDate, String movieTypeName, String movieStyleName,
                         String nationalityName, int totalScreenCount, double salesPrice, int viewingNumber,
                         double seoulSalesPrice, int seoulViewingNumber, String genreName, String gradeName,
                         String movieSubdivisionName, UUID movieId) {

            this.no = no;
            this.movieName = movieName;
            this.directorName = directorName;
            this.makerName = makerName;
            this.incomeCompanyName = incomeCompanyName;
            this.distributionCompanyName = distributionCompanyName;
            this.openingDate = openingDate;
            this.movieTypeName = movieTypeName;
            this.movieStyleName = movieStyleName;
            this.nationalityName = nationalityName;
            this.totalScreenCount = totalScreenCount;
            this.salesPrice = salesPrice;
            this.viewingNumber = viewingNumber;
            this.seoulSalesPrice = seoulSalesPrice;
            this.seoulViewingNumber = seoulViewingNumber;
            this.genreName = genreName == null ? "미정" : genreName;
            this.gradeName = gradeName;
            this.movieSubdivisionName = movieSubdivisionName;
            this.movieId = UUID.randomUUID();

        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getDirectorName() {
            return directorName;
        }

        public void setDirectorName(String directorName) {
            this.directorName = directorName;
        }

        public String getMakerName() {
            return makerName;
        }

        public void setMakerName(String makerName) {
            this.makerName = makerName;
        }

        public String getIncomeCompanyName() {
            return incomeCompanyName;
        }

        public void setIncomeCompanyName(String incomeCompanyName) {
            this.incomeCompanyName = incomeCompanyName;
        }

        public String getDistributionCompanyName() {
            return distributionCompanyName;
        }

        public void setDistributionCompanyName(String distributionCompanyName) {
            this.distributionCompanyName = distributionCompanyName;
        }

        public String getOpeningDate() {
            return openingDate;
        }

        public void setOpeningDate(String openingDate) {
            this.openingDate = openingDate;
        }

        public String getMovieTypeName() {
            return movieTypeName;
        }

        public void setMovieTypeName(String movieTypeName) {
            this.movieTypeName = movieTypeName;
        }

        public String getMovieStyleName() {
            return movieStyleName;
        }

        public void setMovieStyleName(String movieStyleName) {
            this.movieStyleName = movieStyleName;
        }

        public String getNationalityName() {
            return nationalityName;
        }

        public void setNationalityName(String nationalityName) {
            this.nationalityName = nationalityName;
        }

        public int getTotalScreenCount() {
            return totalScreenCount;
        }

        public void setTotalScreenCount(int totalScreenCount) {
            this.totalScreenCount = totalScreenCount;
        }

        public double getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(double salesPrice) {
            this.salesPrice = salesPrice;
        }

        public int getViewingNumber() {
            return viewingNumber;
        }

        public void setViewingNumber(int viewingNumber) {
            this.viewingNumber = viewingNumber;
        }

        public double getSeoulSalesPrice() {
            return seoulSalesPrice;
        }

        public void setSeoulSalesPrice(double seoulSalesPrice) {
            this.seoulSalesPrice = seoulSalesPrice;
        }

        public int getSeoulViewingNumber() {
            return seoulViewingNumber;
        }

        public void setSeoulViewingNumber(int seoulViewingNumber) {
            this.seoulViewingNumber = seoulViewingNumber;
        }

        public String getGenreName() {
            return genreName;
        }

        public void setGenreName(String genreName) {
            this.genreName = genreName;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getMovieSubdivisionName() {
            return movieSubdivisionName;
        }

        public void setMovieSubdivisionName(String movieSubdivisionName) {
            this.movieSubdivisionName = movieSubdivisionName;
        }

        public UUID getMovieId() {
            return movieId;
        }

        public void setMovieId(UUID movieId) {
            this.movieId = movieId;
        }


        @Override
        public String toString() {
            return "MovieInfo{" +
                    "no=" + no +
                    ", movieName='" + movieName + '\'' +
                    ", directorName='" + directorName + '\'' +
                    ", makerName='" + makerName + '\'' +
                    ", incomeCompanyName='" + incomeCompanyName + '\'' +
                    ", distributionCompanyName='" + distributionCompanyName + '\'' +
                    ", openingDate='" + openingDate + '\'' +
                    ", movieTypeName='" + movieTypeName + '\'' +
                    ", movieStyleName='" + movieStyleName + '\'' +
                    ", nationalityName='" + nationalityName + '\'' +
                    ", totalScreenCount=" + totalScreenCount +
                    ", salesPrice=" + salesPrice +
                    ", viewingNumber=" + viewingNumber +
                    ", seoulSalesPrice=" + seoulSalesPrice +
                    ", seoulViewingNumber=" + seoulViewingNumber +
                    ", genreName='" + genreName + '\'' +
                    ", gradeName='" + gradeName + '\'' +
                    ", movieSubdivisionName='" + movieSubdivisionName + '\'' +
                    '}';
        }



    }
}