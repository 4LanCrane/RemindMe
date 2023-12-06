package com.example.remindme;

public class Reminder {
    int  reminderId;
    String reminderTitle;
    String reminderTime;
    String reminderDate;



     /**
      * constructor for the collections class
      * @param reminderId
      * @param reminderTitle
      * @param reminderTime
      * @param reminderDate
      */
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

    public String getReminderTitle() {
        return reminderTitle;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public String getReminderDate() {
        return reminderDate;
    }

}
