package com.codeup.springblog.models;

public class Post {

    private String title;
    private String body;
    private long Id;



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

    public Post(){
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, long id) {
        this.title = title;
        this.body = body;
        Id = id;
    }
}
