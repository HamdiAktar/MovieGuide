package com.android.epicboost.guide.model;

import java.util.List;

public class Credits {
    public int id;
    public List<Cast> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

}
