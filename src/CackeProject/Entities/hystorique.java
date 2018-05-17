package CackeProject.Entities;


import java.sql.Date;

/**
 *
 * @author AhmedMekni
 */
public class hystorique {
    //userid	dateAjout	Bancount	releasedate
    
    private int userid;
    private String dateAjout;
    private int Bancount;
    private Date releasedate;
    private String releaseTime;
    
    
     public hystorique() {
        
    }

    public hystorique(int userid, String dateAjout, int Bancount, String release) {
        this.userid = userid;
        this.dateAjout = dateAjout;
        this.Bancount = Bancount;
        this.releasedate = new Date(Integer.parseInt(release.substring(0,4)), Integer.parseInt(release.substring(5,7)), Integer.parseInt(release.substring(8,10)));
        this.releaseTime = release.substring(10);
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getBancount() {
        return Bancount;
    }

    public void setBancount(int Bancount) {
        this.Bancount = Bancount;
    }

    public Date getReleasetime() {
        return releasedate;
    }

    public void setReleasetime(Date releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public String toString() {
        return "historique{" + "userid=" + userid + ", dateAjout=" + dateAjout + ", Bancount=" + Bancount + ", releasedate=" + releasedate + '}';
    }
    
}
