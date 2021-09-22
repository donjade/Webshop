package bean;


import java.sql.Date;

public class Review {
    private int id;
    private String content;
    private Date createddate;
    private int rate;
    private String custuname;
    private int pid;

    public String getCustuname() {
        return custuname;
    }

    public void setCustuname(String custuname) {
        this.custuname = custuname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
