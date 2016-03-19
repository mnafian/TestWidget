package net.mnafian.testwidget.data.api;

import net.mnafian.testwidget.data.model.DataPrice;

import id.zelory.benih.util.BenihServiceGenerator;
import retrofit.http.GET;
import rx.Observable;

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
public enum RimbunesiaApi {

    RIMBUN_API;
    private final API api;

    RimbunesiaApi() {
        api = BenihServiceGenerator.createService(API.class, API.ENDPOINT);
    }

    public static RimbunesiaApi getData() {
        return RIMBUN_API;
    }

    public API getAPI() {
        return api;
    }

    public interface API {
        String ENDPOINT = "https://jakartanesia.rimbunesia.com";
        String ENDPOINTTEST = "http://infouin.mnafian.net";

        @GET("/api/test")
        Observable<DataPrice> getDataPrice();

        @GET("/testdua.json")
        Observable<DataPrice> getDataPriceTest();
    }
}
