package net.mnafian.testwidget.service;

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

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

import net.mnafian.testwidget.ListProviderAdapter;
import net.mnafian.testwidget.UpdateHargaView;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetId = Integer.valueOf(intent.getData().getSchemeSpecificPart())
                - UpdateHargaView.randomNumber;

        return (new ListProviderAdapter(this.getApplicationContext(), intent, appWidgetId));
    }

}
