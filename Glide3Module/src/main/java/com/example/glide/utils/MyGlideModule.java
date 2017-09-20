package com.example.glide.utils;

import android.content.Context;


import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.glide.R;


/**
 * Created by yangchaojiang on 2016/8/14.
 * GlideModule 图片加载缓自定义配置
 */
public class MyGlideModule extends OkHttpGlideModule {
    public static int cacheSize100MegaBytes = 504857600;
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        // 磁盘高速缓存的大小:
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));

    }


}

