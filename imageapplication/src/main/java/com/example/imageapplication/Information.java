package com.example.imageapplication;
public class Information  {
    private String  type;
    private String  username;
    private String  title;
    private String  import_datetime;

    public Information(String type, String username, String title, String import_datetime) {
        this.type = type;
        this.username = username;
        this.title = title;
        this.import_datetime = import_datetime;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getImport_datetime() {
        return import_datetime;
    }
}
