package com.example.imageapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Information implements Parcelable {

    private String url;
    private String type;
    private String username;
    private String title;
    private String import_datetime;

    public Information(String url, String type, String username, String title, String import_datetime) {
        this.url = url;
        this.type = type;
        this.username = username;
        this.title = title;
        this.import_datetime = import_datetime;
    }

    private Information(Parcel in) {
        url = in.readString();
        type = in.readString();
        username = in.readString();
        title = in.readString();
        import_datetime = in.readString();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(type);
        dest.writeString(username);
        dest.writeString(title);
        dest.writeString(import_datetime);

    }





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
