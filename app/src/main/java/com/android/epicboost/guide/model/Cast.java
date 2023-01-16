package com.android.epicboost.guide.model;

public class Cast{

    public int id;
    public String name;
    public String profile_path;


    public static String imageLink="https://image.tmdb.org/t/p/";
    public static String imageSize="w342";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return imageLink+imageSize+profile_path;
    }

}
