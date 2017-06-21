package com.gildemodule.utils;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.gildemodule.R;
import java.io.InputStream;


/**
 * Created by yangchaojiang on 2016/8/14.
 * GlideModule 图片加载缓自定义配置
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {
    public static int cacheSize100MegaBytes = 504857600;
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(5)
                .build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        // 磁盘高速缓存的大小:
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));

    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        super.registerComponents(context, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}

