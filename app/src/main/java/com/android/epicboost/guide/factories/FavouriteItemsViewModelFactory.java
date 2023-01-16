package com.android.epicboost.guide.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.epicboost.guide.ui.favouriteItems.favouriteViewModel;

import java.util.Set;

public class FavouriteItemsViewModelFactory implements ViewModelProvider.Factory{
    private Set<String> itemsID;


    public FavouriteItemsViewModelFactory(Set<String> movieIds) {
        this.itemsID = movieIds;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new favouriteViewModel(itemsID);
    }
}
