package com.android.epicboost.guide.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.epicboost.guide.ui.itemDetails.ItemDetailsViewModel;

public class ItemDetailsViewModelFactory implements ViewModelProvider.Factory{
    private int itemId;


    public ItemDetailsViewModelFactory(int movieId) {
        this.itemId = movieId;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ItemDetailsViewModel(itemId);
    }
}
