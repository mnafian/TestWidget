package net.mnafian.testwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import net.mnafian.testwidget.data.model.ListDataPrice;
import net.mnafian.testwidget.service.DataPriceService;
import net.mnafian.testwidget.service.PhoneBootCompleteReceiver;
import net.mnafian.testwidget.service.WidgetService;
import net.mnafian.testwidget.ui.ViewEmptyActivity;

import java.util.List;
import java.util.Random;


public class UpdateHargaView extends AppWidgetProvider {

    public static final String DATA_FETCHED = "net.mnafian.testwidget.DATA_FETCHED";
    public static Integer randomNumber;
    private Random myRandom;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        if (PhoneBootCompleteReceiver.wasPhoneBootSucessful) {
            for (int appWidgetId : appWidgetIds) {
                Intent serviceIntent = new Intent(context, DataPriceService.class);
                serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        appWidgetId);
                context.startService(serviceIntent);
            }
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.update_harga);

        if (DataPriceService.storeName != null && DataPriceService.listItemList != null) {
            remoteViews.setTextViewText(R.id.tx_upd_market_tittle, DataPriceService.storeName.toUpperCase());
            List<ListDataPrice> listItemList = DataPriceService.listItemList;
            Log.e("jumlah size", listItemList.size() + "");
            if (listItemList.size() == 0) {
                remoteViews.setViewVisibility(R.id.view_no_available, View.VISIBLE);
                remoteViews.setViewVisibility(R.id.btn_upd_lihat_lengkap, View.GONE);
                remoteViews.setViewVisibility(R.id.list_price, View.INVISIBLE);
            } else if (listItemList.size() < 3) {
                remoteViews.setViewVisibility(R.id.list_price, View.VISIBLE);
                remoteViews.setViewVisibility(R.id.btn_upd_lihat_lengkap, View.VISIBLE);
                remoteViews.setViewVisibility(R.id.view_no_available, View.GONE);

                Intent configIntent = new Intent(context, ViewEmptyActivity.class);

                PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

                remoteViews.setOnClickPendingIntent(R.id.btn_upd_lihat_lengkap, configPendingIntent);
            } else {
                remoteViews.setViewVisibility(R.id.list_price, View.VISIBLE);
                remoteViews.setViewVisibility(R.id.btn_upd_lihat_lengkap, View.GONE);
                remoteViews.setViewVisibility(R.id.view_no_available, View.GONE);
            }
        }

        myRandom = new Random();
        randomNumber = myRandom.nextInt();

        Intent svcIntent = new Intent(context, WidgetService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.fromParts("content", String.valueOf(appWidgetId + randomNumber), null));
        remoteViews.setRemoteAdapter(appWidgetId, R.id.list_price,
                svcIntent);
        return remoteViews;
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(DATA_FETCHED)) {
            Log.d("data fetched", "true");
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager appWidgetManager = AppWidgetManager
                    .getInstance(context);
            RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
            ComponentName component = new ComponentName(context, UpdateHargaView.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_price);
            appWidgetManager.updateAppWidget(component, remoteViews);
        }
    }

}

