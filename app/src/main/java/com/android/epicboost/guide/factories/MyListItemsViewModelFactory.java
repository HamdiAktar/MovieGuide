package com.android.epicboost.guide.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.epicboost.guide.ui.watchList.myListViewModel;

import java.util.Set;

public class MyListItemsViewModelFactory implements ViewModelProvider.Factory{
    private Set<String> itemIds;


    public MyListItemsViewModelFactory(Set<String> movieIds) {
        this.itemIds = movieIds;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new myListViewModel(itemIds);
    }
}
