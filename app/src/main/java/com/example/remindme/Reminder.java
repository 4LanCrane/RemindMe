package com.example.remindme;

public class Reminder {
    int  reminderId;
    String reminderTitle;

    String reminderTime;

    String reminderDate;




    public Reminder(int reminderId,String reminderTitle,String reminderTime,String reminderDate){
        this.reminderId =reminderId;
        this.reminderTitle = reminderTitle;
        this.reminderTime = reminderTime;
        this.reminderDate = reminderDate;

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
