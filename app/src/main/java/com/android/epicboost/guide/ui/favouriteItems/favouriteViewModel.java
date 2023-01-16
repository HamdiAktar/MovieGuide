package com.android.epicboost.guide.ui.favouriteItems;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.model.ItemDetails;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class favouriteViewModel extends ViewModel {

    private MutableLiveData<List<ItemDetails>> listMutableLiveData;
    private List<ItemDetails> items;
    private Set<String> itemIds;

    public favouriteViewModel(Set<String> itemIds) {
        listMutableLiveData = new MutableLiveData<>();
        items =new ArrayList<>();
        this.itemIds =itemIds;
        updateList();
    }

    public void updateList(){
        items.clear();
        for (String itemId : itemIds){
            queryMovie(Integer.parseInt(itemId));
        }
    }

    boolean isExist(int itemId){
        for (ItemDetails item: items)
            if (itemId== item.id)
                return true;
        return false;
    }
    public void queryMovie(int movieId){
        AndroidNetworking.get(MainActivity.resourceInstance.getString(R.string.apiEndPointSearchDetails))
                .addPathParameter("item_id", Integer.toString(movieId))
                .addPathParameter("apiKey", MainActivity.API_Key)
                .setPriority(Priority.HIGH)
                .build().getAsObject(ItemDetails.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                ItemDetails itemDetails= (ItemDetails) response;
                if (!isExist(itemDetails.id))
                {
                    items.add(itemDetails);
                    listMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onError(ANError anError) {
                Log.e("TAG", "anError: " +anError.getMessage());
            }
        });
    }


    MutableLiveData<List<ItemDetails>> getMovieListMutableLiveData() {
        return listMutableLiveData;
    }
}