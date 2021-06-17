package com.example.imageapplication;
public class Information  {

    private String  url;
    private String  type;
    private String  username;
    private String  title;
    private String  import_datetime;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImport_datetime(String import_datetime) {
        this.import_datetime = import_datetime;
    }

    public String getUrl() {
        return url;
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
