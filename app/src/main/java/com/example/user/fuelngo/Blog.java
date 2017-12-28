package com.example.user.fuelngo;

public class Blog {
    private String title,description,location,address,startdate,enddate,starttime,endtime;

    public Blog() {
    }

    public Blog(String title, String description, String location, String address, String startdate,String enddate,String starttime, String endtime) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.address = address;
        this.startdate = startdate;
        this.enddate = enddate;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}