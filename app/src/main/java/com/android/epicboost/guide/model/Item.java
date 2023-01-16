package com.android.epicboost.guide.model;

import com.android.epicboost.guide.BuildConfig;

public class Item {
    public String poster_path;
    public String overview;
    public int id ;
    public String title;
    public double vote_average ;
    public String name;

    public static String imageLink="https://image.tmdb.org/t/p/";
    public static String imageSize="w342";

    public String getPoster_path() {
        return imageLink+imageSize+poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getName() {
        if (BuildConfig.FLAVOR=="Movie")
            return getTitle();
        else return name;
    }

}
