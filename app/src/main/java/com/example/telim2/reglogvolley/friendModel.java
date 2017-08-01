package com.example.telim2.reglogvolley;

/**
 * Created by telim2 on 31.07.2017.
 */

public class friendModel {

    private String friend;
    private String  id;
    private String imageLink;

    public friendModel(String id, String friend, String imageLink) {

        this.id = id;
        this.friend = friend;
        this.imageLink = imageLink;
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
