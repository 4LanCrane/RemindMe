package com.example.remindme;

public class Collections {
    int id;//id of the collection
    String title;//title of the collection


    /**
     * constructor for the collections class
     *
     * @param title
     * @param id
     */
    public Collections(String title, int id) {
        this.title = title;
        this.id = id;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
