package com.android.epicboost.guide.utils;

import android.content.Context;

public class ResourceUtil {
    private Context mContext;

    public ResourceUtil(Context mContext) {
        this.mContext = mContext;
    }

    public String getString(int resId) {
        return mContext.getString(resId);
    }

}


