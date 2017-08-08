package com.example.telim2.reglogvolley;

/**
 * Created by telim2 on 08.08.2017.
 */

public class RequestModel {
    private String friend;
    private String  id;
    private String imageLink;
    private  String Gname;

    public RequestModel(String id, String friend, String imageLink, String Gname) {

        this.id = id;
        this.friend = friend;
        this.imageLink = imageLink;
        this.Gname=Gname;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public void setId(String id){

        this.id=id;
    }
}
