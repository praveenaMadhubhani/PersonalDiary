package com.example.personaldiary;

public class ModelClass {
    private int id;
    private String title, description;
    private String date, time;
    private String imagePath;

    public ModelClass(int id, String title, String description, String date, String time,String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.imagePath=imagePath;
    }

    public ModelClass(String title, String description, String date, String time,String imagePath) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.imagePath=imagePath;
    }

    public ModelClass(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageDirectory() {
        return imagePath;
    }

    public void setImageDirectory(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
