package zhanghegang.com.bawei.onetime;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.leakcanary.RefWatcher;

import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zhanghegang.com.bawei.onetime.base.BaseActivity;
import zhanghegang.com.bawei.onetime.base.BasePresenter;
import zhanghegang.com.bawei.onetime.bean.BaseReg;
import zhanghegang.com.bawei.onetime.bean.RegBean;
import zhanghegang.com.bawei.onetime.comment.UiData;

public class LeadActivity extends BaseActivity {
    private Handler handler=new Handler();

private int index=0;
private ImageView iv;
    private String s;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lead;
    }

    @Override
    public void initView() {
//        RefWatcher refWatcher = MyApp.getRefWatcher(this);
//        refWatcher.watch(this);
        handler.postDelayed(runnable,1000);
//        RegBean regBean=new RegBean();
//
//
//        regBean.account="13716792264";
//        regBean.openid="1111";
//        regBean.password="102677";
//        regBean.unionid="283294309";
//
//        BaseReg<RegBean> beanBaseReg=new BaseReg<>();
//        beanBaseReg.h2y_app_id="1232121";
//        beanBaseReg.pd=regBean;
//        beanBaseReg.token="1283278";
//
//        Gson gson=new Gson();
//        s = gson.toJson(beanBaseReg);
//        System.out.println("gson==========="+ s);
//        initData();

    }

    private void initData() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://cms.51hxe.com/hy_cms/member/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//.client(setCard())
                .build();
        UiData uiData = retrofit.create(UiData.class);
//        MultipartBody.Builder multipart=new MultipartBody.Builder().setType(MediaType.parse(""))
//                .addFormDataPart("memberLoginReq",);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), s);
        uiData.getlogin(requestBody).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody regActivityBaseReg) throws Exception {
                System.out.println("json========data====="+regActivityBaseReg.string());
            }
        });
    }

    @Override
    public void ionDestroy() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public OkHttpClient setCard(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        try {
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X509");
            KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String s = Integer.toString(0);
            keyStore.setCertificateEntry(s,certificateFactory.generateCertificate(getAssets().open("ios_development.cer")));
            SSLContext sslContext=SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            index++;
            if(index==3)
            {
              startActivity(new Intent(LeadActivity.this,RegActivity.class));
              handler.removeCallbacks(runnable);
              finish();

            }
            else {

                handler.postDelayed(runnable, 1000);
            }
        }
    };


}
