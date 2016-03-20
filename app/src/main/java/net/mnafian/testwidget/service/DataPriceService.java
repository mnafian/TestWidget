package net.mnafian.testwidget.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import net.mnafian.testwidget.controller.ServiceController;
import net.mnafian.testwidget.data.model.DataPrice;
import net.mnafian.testwidget.data.model.ListDataPrice;
import net.mnafian.testwidget.UpdateHargaView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on : February 3/18/16, 2016
 * Author     : mnafian
 * Name       : M. Nafian
 * Email      : mnafian@icloud.com
 * GitHub     : https://github.com/mnafian
 * LinkedIn   : https://id.linkedin.com/in/mnafian
 * Company    : Inagata Technosmith
 * Project    : TestWidget
 */
public class DataPriceService extends Service implements ServiceController.Presenter {

    public static List<ListDataPrice> listItemList;
    public static String storeName;
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private ServiceController serviceController;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.hasExtra(AppWidgetManager.EXTRA_APPWIDGET_ID))
                appWidgetId = intent.getIntExtra(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
            serviceController = new ServiceController(this);
            fetchDataPrice();
            Log.i("Data Fetched", "true");
        }
        return START_STICKY;
    }

    private void fetchDataPrice() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                serviceController.loadDataPrice();
            }
        }, 0, 50000);
    }

    @Override
    public void showLoading() {
        Log.d("load data", "process");
    }

    @Override
    public void dismissLoading() {
        Log.d("finish load data", "finish");
    }

    @Override
    public void getDataPrice(DataPrice dataPrice) {
        listItemList = dataPrice.getData();
        storeName = dataPrice.getMarket();
        populateWidget();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error on received", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("PleaseDontKillMe"));
    }

    private void populateWidget() {
        Intent widgetUpdateIntent = new Intent();
        widgetUpdateIntent.setAction(UpdateHargaView.DATA_FETCHED);
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                appWidgetId);
        sendBroadcast(widgetUpdateIntent);
    }

}
