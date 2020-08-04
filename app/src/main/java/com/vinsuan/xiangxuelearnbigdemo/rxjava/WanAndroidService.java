package com.vinsuan.xiangxuelearnbigdemo.rxjava;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/3
 * desc :
 */
public interface WanAndroidService {
    @GET("project/tree/json")
    Observable<Bean> getProject();


    @GET("project/list/{pageIndex}/json")
    Observable<ItemBean> getItemProject(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

}
