package net.mnafian.testwidget.controller;

import android.os.Bundle;

import timber.log.Timber;

/**
 * Created on : February 20, 2016
 * Author     : mnafian
 * Name       : M. Nafian
 * Email      : mnafian@icloud.com
 * GitHub     : https://github.com/mnafian
 * LinkedIn   : https://id.linkedin.com/in/mnafian
 * Company    : Inagata Technosmith
 * Project    : Info UIN Malang
 */
public abstract class BenihController<P extends BenihController.Presenter> {
    protected P presenter;

    public BenihController(P presenter) {
        this.presenter = presenter;
        Timber.tag(getClass().getSimpleName());
    }

    public abstract void saveState(Bundle bundle);

    public abstract void loadState(Bundle bundle);

    public interface Presenter {
        void showError(Throwable throwable);
    }
}
