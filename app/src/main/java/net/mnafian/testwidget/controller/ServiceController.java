package net.mnafian.testwidget.controller;

import android.os.Bundle;
import android.util.Log;

import net.mnafian.testwidget.data.api.RimbunesiaApi;
import net.mnafian.testwidget.data.model.DataPrice;

import id.zelory.benih.util.BenihScheduler;

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
public class ServiceController extends BenihController<ServiceController.Presenter> {

    private DataPrice dataPrice;

    public ServiceController(Presenter presenter) {
        super(presenter);
        Log.d("Service created", "success");
    }

    public void loadDataPrice() {
        presenter.showLoading();
        RimbunesiaApi.getData()
                .getAPI()
                .getDataPrice()
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                .subscribe(dataPrice -> {
                    this.dataPrice = dataPrice;
                    presenter.getDataPrice(dataPrice);
                    presenter.dismissLoading();
                }, throwable -> {
                    presenter.showError(throwable);
                    presenter.dismissLoading();
                });
    }

    @Override
    public void saveState(Bundle bundle) {

    }

    @Override
    public void loadState(Bundle bundle) {

    }

    public interface Presenter extends BenihController.Presenter {
        void showLoading();

        void dismissLoading();

        void getDataPrice(DataPrice dataPrice);
    }
}
