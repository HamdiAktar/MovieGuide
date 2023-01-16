package com.android.epicboost.guide.model;

import com.android.epicboost.guide.BuildConfig;

import java.util.List;

public class ItemDetails {

    public List<Genre> genres;
    public int id;
    public String overview;
    public String poster_path;
    public String release_date;
    public long revenue;
    public int runtime;
    public String title;
    public double vote_average;
    public List<Cast> cast;
    public String name;
    public int number_of_episodes;
    public int number_of_seasons;
    public String last_air_date;
    public String first_air_date;


    public final String imageLink="https://image.tmdb.org/t/p/";
    public final String imageSize="w342";

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }



    public List<Genre> getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }


    public String getPoster_path() {
        return imageLink+imageSize+poster_path;
    }

    public String getNumberOfSeasons() {
        return number_of_seasons+" season(s)";
    }

    public String getNumberOfEpisodes(){
        return number_of_episodes + " episode(s) ";
    }

    public String getAirTime(){return first_air_date.substring(0,4) +" - "+last_air_date.substring(0,4);}

    public String getRevenue() {
        return String.format("%.2fM", revenue/ 1000000.0);
    }

    public String getRuntime() {
        return runtime+" Min";
    }

    public String getName() {
        if (BuildConfig.FLAVOR=="Movie")
        return title;
        else return name;
    }
    public String getDetailedInfo1(){
        if (BuildConfig.FLAVOR=="Movie") return  release_date;
        else return getAirTime();
    }
    public String getDetailedInfo2(){
        if (BuildConfig.FLAVOR=="Movie") return  getRuntime();
        else return getNumberOfSeasons();
    }

    public String getDetailedInfo3(){
        if (BuildConfig.FLAVOR=="Movie") return  getRevenue();
        else return getNumberOfEpisodes();
    }


}