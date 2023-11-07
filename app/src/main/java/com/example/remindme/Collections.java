package com.example.remindme;

public class Collections {
    int  id;
    String title;
    String description;



    public Collections(String title, String description,int id){
        this.title = title;
        this.description = description;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
