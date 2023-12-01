package com.example.remindme;

public class Collections {
    int  id;//id of the collection
    String title;//title of the collection


    /**
     * constructor for the collections class
     * @param title
     * @param description
     * @param id
     */
    public Collections(String title, String description,int id){
        this.title = title;
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


}
