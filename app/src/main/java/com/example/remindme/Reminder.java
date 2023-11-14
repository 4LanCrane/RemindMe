package com.example.remindme;

public class Reminder {
    int  reminderId;
    String reminderTitle;




    public Reminder(int reminderId,String reminderTitle){
        this.reminderId =reminderId;
        this.reminderTitle = reminderTitle;
    }


    //getters and setters
    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }



}
