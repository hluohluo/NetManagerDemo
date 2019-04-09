package com.netmanagerdemo.net;

import com.google.gson.Gson;

/**
 * Created by hluo on 2019/4/9.
 */
public class GsonProvider {
    private static Gson gson;

    /**
     * 获取gson
     *
     * @return
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


}
