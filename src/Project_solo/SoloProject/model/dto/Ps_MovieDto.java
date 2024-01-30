package Project_solo.SoloProject.model.dto;

public class Ps_MovieDto {
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

    public Ps_MovieDto(){}
    public Ps_MovieDto(int no, String movieName, String directorName, String makerName, String incomeCompanyName, String distributionCompanyName, String openingDate, String movieTypeName, String movieStyleName, String nationalityName, int totalScreenCount, double salesPrice, int viewingNumber, double seoulSalesPrice, int seoulViewingNumber, String genreName, String gradeName, String movieSubdivisionName) {
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
        this.genreName = genreName;
        this.gradeName = gradeName;
        this.movieSubdivisionName = movieSubdivisionName;
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

    @Override
    public String toString() {
        return "Ps_MovieDto{" +
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


    public Object getMovieId() {
        return 0;
    }
}
