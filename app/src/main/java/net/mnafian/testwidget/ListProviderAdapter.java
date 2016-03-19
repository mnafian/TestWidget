package net.mnafian.testwidget;

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

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import net.mnafian.testwidget.data.model.ListDataPrice;
import net.mnafian.testwidget.service.DataPriceService;

import java.util.ArrayList;
import java.util.List;

public class ListProviderAdapter implements RemoteViewsFactory {
    private List<ListDataPrice> listItemList = new ArrayList<>();
    private Context context = null;
    private int appWidgetId;

    public ListProviderAdapter(Context context, Intent intent, int appWidgetId) {
        this.context = context;
        this.appWidgetId = appWidgetId;

        populateListItem();
    }

    private void populateListItem() {
        if (DataPriceService.listItemList != null) {
            listItemList = DataPriceService.listItemList;
            Log.d("size", listItemList.size() + "");
        }
    }

    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.update_harga_item);
        ListDataPrice listItem = listItemList.get(position);
        remoteView.setTextViewText(R.id.tv_tittle, listItem.getName().toUpperCase());
        remoteView.setTextViewText(R.id.tv_price, listItem.getPrice());
        remoteView.setTextViewText(R.id.tv_tipe_satuan, "/" + listItem.getUnit());

        String status = listItem.getStatus();
        if (status.equals("down")) {
            remoteView.setImageViewResource(R.id.img_up_down, R.drawable.ic_1458365676_navigation_down_basic_green);
        } else {
            remoteView.setImageViewResource(R.id.img_up_down, R.drawable.ic_1458365680_navigation_up_basic_red);
        }


        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
        if (listItemList != null)
            listItemList.clear();
    }

}
