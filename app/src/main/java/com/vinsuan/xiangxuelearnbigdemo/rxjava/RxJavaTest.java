package com.vinsuan.xiangxuelearnbigdemo.rxjava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vinsuan.xiangxuelearnbigdemo.R;
import com.vinsuan.xiangxuelearnbigdemo.inject.InjectUtils;
import com.vinsuan.xiangxuelearnbigdemo.inject.injectView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * create by 高 (｡◕‿◕｡) 磊
 * 2020/8/3
 * desc :
 */
public class RxJavaTest extends Activity {
    public String url = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg";

    @injectView(R.id.iv)
    ImageView imageView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_java_layout);
        InjectUtils.injectView(this);
    }

    /**
     * 封装线程调度操作
     * upstream 上游
     * downstream 下游
     */
    private final static <UD> ObservableTransformer<UD, UD> rxud() {
        return new ObservableTransformer<UD, UD>() {
            @Override
            public ObservableSource<UD> apply(Observable<UD> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public void rxJavaDownLoadImage(View view) {
        Observable.just(url)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws IOException {
                        URL url1 = new URL(url);
                        Bitmap bitmap = null;
                        HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                        urlConnection.setConnectTimeout(5000);
                        int responseCode = urlConnection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream inputStream = urlConnection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
                        }
                        return bitmap;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //订阅开始
                        progressDialog = new ProgressDialog(RxJavaTest.this);
                        progressDialog.setTitle("download");
                        progressDialog.show();
                    }

                    @Override
                    public void onNext(@NonNull Bitmap s) {
                        //拿到事件
                        imageView.setImageBitmap(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //错误事件
                        Toast.makeText(RxJavaTest.this, "onerror", Toast.LENGTH_SHORT).show();
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onComplete() {
                        //完成事件
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}
