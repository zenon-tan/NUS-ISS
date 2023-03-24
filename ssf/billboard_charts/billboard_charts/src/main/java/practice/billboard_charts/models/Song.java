package practice.billboard_charts.models;

import java.io.Serializable;

public class Song implements Serializable {

    private String artist;
    private String title;
    private String image;
    private String rank;
    private String weeksAtNo1;
    private String lastWeek;
    private String change;
    private String peakPosition;
    private String weeksOnChart;

    // #region GettersSetters
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getWeeksAtNo1() {
        return weeksAtNo1;
    }
    public void setWeeksAtNo1(String weeksAtNo1) {
        this.weeksAtNo1 = weeksAtNo1;
    }
    public String getLastWeek() {
        return lastWeek;
    }
    public void setLastWeek(String lastWeek) {
        this.lastWeek = lastWeek;
    }
    public String getChange() {
        return change;
    }
    public void setChange(String change) {
        this.change = change;
    }
    public String getPeakPosition() {
        return peakPosition;
    }
    public void setPeakPosition(String peakPosition) {
        this.peakPosition = peakPosition;
    }
    public String getWeeksOnChart() {
        return weeksOnChart;
    }
    public void setWeeksOnChart(String weeksOnChart) {
        this.weeksOnChart = weeksOnChart;
    }

    // #endregion

    

    


    
}
