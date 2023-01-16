package com.android.epicboost.guide.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.ui.MainActivity;
import com.android.epicboost.guide.model.JsonResponse;
import com.android.epicboost.guide.model.Item;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Item>> itemList;
    private List<Item> items;
    public HomeViewModel() {
        itemList = new MutableLiveData<>();
        items =new ArrayList<>();
    }
    public void callAPI(String query){
        AndroidNetworking.get(MainActivity.resourceInstance.getString(R.string.apiEndPointSearch))
                .addPathParameter("query", query)
                .addPathParameter("apiKey", MainActivity.API_Key)
                .addPathParameter("pageNo", "1")
                .setPriority(Priority.HIGH)
                .build().getAsObject(JsonResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        JsonResponse JsResponse= (JsonResponse) response;
                        items =JsResponse.results;
                        itemList.setValue(items);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("TAG", "anError: " +anError.getMessage());
                    }
                });
    }

    public void cleanResult(){
        items.clear();
        itemList.setValue(items);
    }


    MutableLiveData<List<Item>> getItemMutableLiveData() {
        return itemList;
    }

}