package zhanghegang.com.bawei.onetime;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.PushReceiver;
import com.mingle.skin.SkinConfig;
import com.mingle.skin.SkinEnable;
import com.mingle.skin.hepler.SkinHelper;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * current package:zhanghegang.com.bawei.onetime
 * Created by Mr.zhang
 * date: 2017/11/13
 * decription:开发
 */

public class MyApp extends Application {
    public static Context app;
    private RefWatcher install;

    /**
     * 获得RefWatcher检测activity的内存泄漏问题
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context){
    MyApp myApp= (MyApp) context.getApplicationContext();
    return myApp.install;

}
    public static void init(Context context) {
        sRes = context.getResources();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
init(this);
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        //自定义接口接收广播发送内容
//GeTuiReceiver.setOnGeTuiLitenter(new GeTuiReceiver.OnGeTuiLitenter() {
//    @Override
//    public void getGeTuiData(String msg) {
//        System.out.println("=======myapp=="+msg);
//    }
//});

        CrashReport.initCrashReport(getApplicationContext(), "e4a123c11b", false);
//        CrashReport.testJavaCrash();
        app = getApplicationContext();

         LeakCanary.install(this);

    }
    public static Resources sRes;
    public static void updateNightMode(boolean on) {


//        System.out.println("======updateNightMode=====on========="+on+defaultNightMode);
        DisplayMetrics dm = sRes.getDisplayMetrics();
        Configuration config = sRes.getConfiguration();

        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        config.uiMode |= on ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
        sRes.updateConfiguration(config, dm);
        int defaultNightMode = AppCompatDelegate.getDefaultNightMode();

        if(defaultNightMode==AppCompatDelegate.MODE_NIGHT_YES)
        {
            System.out.println("======updateNightMode===Yes==on========="+on+defaultNightMode);
        }
        else {
            System.out.println("======updateNightMode==No===on========="+on+defaultNightMode);
        }
    }

}
