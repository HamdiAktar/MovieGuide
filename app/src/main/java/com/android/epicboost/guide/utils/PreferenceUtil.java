package com.android.epicboost.guide.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;


public class PreferenceUtil {

    public static final String Favourite_SharedPref = "favourite_Movies";
    public static final String MyList_SharedPref = "myList_Movies";
    public static final String MySharedPref = "MySharedPref";
    private static PreferenceUtil sInstance;
    private SharedPreferences mPref;

    private PreferenceUtil(Context context) {
        mPref = context.getSharedPreferences(MySharedPref,Context.MODE_PRIVATE);
    }
    public static PreferenceUtil getInstance(Context context) {
        if (sInstance == null)
            sInstance = new PreferenceUtil(context.getApplicationContext());
        return sInstance;
    }

    public Set<String> getFavouriteMovieList() {
        return mPref.getStringSet(Favourite_SharedPref, new HashSet<>());
    }

    public Set<String> getMyList() {
        return mPref.getStringSet(MyList_SharedPref, new HashSet<>());
    }

    public void addMovieToFavourite(int movieId){
        Set<String >temp= new HashSet<>(getFavouriteMovieList());
        temp.add(String.valueOf(movieId));
        SharedPreferences.Editor editor = mPref.edit();
        editor.putStringSet(Favourite_SharedPref, new HashSet<>(temp)).commit();
    }

    public void addMovieToMyList(int movieId){
        Set<String >temp= new HashSet<>(getMyList());
        temp.add(String.valueOf(movieId));
        SharedPreferences.Editor editor = mPref.edit();
        editor.putStringSet(MyList_SharedPref, new HashSet<>(temp)).commit();
    }

    public void removeFavSharedPref(int movieId){
        Set<String >temp= new HashSet<>(getFavouriteMovieList());
        temp.remove(String.valueOf(movieId));
        SharedPreferences.Editor editor = mPref.edit();
        editor.putStringSet(Favourite_SharedPref, new HashSet<>(temp)).commit();
    }

    public void removeListSharedPref(int movieId){

        Set<String >temp= new HashSet<>(getMyList());
        temp.remove(String.valueOf(movieId));
        SharedPreferences.Editor editor = mPref.edit();
        editor.putStringSet(MyList_SharedPref, new HashSet<>(temp)).commit();
    }

    public boolean isFavouriteMovie(int movieId){
        return getFavouriteMovieList().contains(String.valueOf(movieId));
    }
    public boolean isMyListMovie(int movieId){
        return getMyList().contains(String.valueOf(movieId));
    }

}
