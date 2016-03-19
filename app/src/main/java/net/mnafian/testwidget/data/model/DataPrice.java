package net.mnafian.testwidget.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

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
public class DataPrice implements Parcelable {

    public static final Creator<DataPrice> CREATOR = new Creator<DataPrice>() {
        @Override
        public DataPrice createFromParcel(Parcel in) {
            return new DataPrice(in);
        }

        @Override
        public DataPrice[] newArray(int size) {
            return new DataPrice[size];
        }
    };
    private String market;
    private List<ListDataPrice> data;

    protected DataPrice(Parcel in) {
        market = in.readString();
        data = in.createTypedArrayList(ListDataPrice.CREATOR);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(market);
        dest.writeTypedList(data);
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public List<ListDataPrice> getData() {
        return data;
    }

    public void setData(List<ListDataPrice> data) {
        this.data = data;
    }
}
