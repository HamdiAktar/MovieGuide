package com.android.epicboost.guide.ui.itemDetails;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.model.Credits;
import com.android.epicboost.guide.model.ItemDetails;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;


public class ItemDetailsViewModel extends ViewModel {
    private int itemId;
    private MutableLiveData<ItemDetails> itemDetailsMutableLiveData;
    private ItemDetails itemDetails;

    public ItemDetailsViewModel(int movieId) {
        itemDetailsMutableLiveData = new MutableLiveData<>();
        this.itemId =movieId;
        getMovieDetails();
    }
    public void getMovieDetails(){
        AndroidNetworking.get(MainActivity.resourceInstance.getString(R.string.apiEndPointSearchDetails))
                .addPathParameter("item_id", Integer.toString(itemId))
                .addPathParameter("apiKey", MainActivity.API_Key)
                .setPriority(Priority.HIGH)
                .build().getAsObject(ItemDetails.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                itemDetails = (ItemDetails) response;
                itemDetails.setCast(new ArrayList<>());
                itemDetailsMutableLiveData.setValue(itemDetails);
                getCreditDetails();
            }

            @Override
            public void onError(ANError anError) {
                Log.e("TAG", "anError: " +anError.getMessage());
            }
        });
    }
    public void getCreditDetails(){
        AndroidNetworking.get(MainActivity.resourceInstance.getString(R.string.apiEndPointSearchCredit))
                .addPathParameter("item_id", Integer.toString(itemId))
                .addPathParameter("apiKey", MainActivity.API_Key)
                .setPriority(Priority.HIGH)
                .build().getAsObject(Credits.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                Credits credits= (Credits) response;
                itemDetails.setCast(credits.getCast());
                itemDetailsMutableLiveData.setValue(itemDetails);
            }

            @Override
            public void onError(ANError anError) {
                Log.e("TAG", "anError: " +anError.getMessage());
            }
        });
    }

    public MutableLiveData<ItemDetails> getItemDetails() {
        return itemDetailsMutableLiveData;
    }
}