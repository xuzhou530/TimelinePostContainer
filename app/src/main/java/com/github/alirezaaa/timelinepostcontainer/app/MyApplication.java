/*
 * Copyright 2016 Alireza Eskandarpour Shoferi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.alirezaaa.timelinepostcontainer.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.danikula.videocache.HttpProxyCacheServer;

public class MyApplication extends Application {

    public static volatile Handler applicationHandler;
    private static MyApplication mInstance;
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return (app.proxy == null) ? ((app.proxy = app.newProxy())) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        applicationHandler = new Handler(getInstance().getMainLooper());
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
