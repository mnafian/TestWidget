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

import android.content.Intent;
import android.widget.RemoteViewsService;

import net.mnafian.testwidget.ListProviderAdapter;

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new ListProviderAdapter(this.getApplicationContext()));
    }

}
