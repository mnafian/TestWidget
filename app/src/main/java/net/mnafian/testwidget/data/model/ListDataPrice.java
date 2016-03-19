package net.mnafian.testwidget.data.model;

import android.os.Parcel;
import android.os.Parcelable;

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
public class ListDataPrice implements Parcelable {

    public static final Creator<ListDataPrice> CREATOR = new Creator<ListDataPrice>() {
        @Override
        public ListDataPrice createFromParcel(Parcel in) {
            return new ListDataPrice(in);
        }

        @Override
        public ListDataPrice[] newArray(int size) {
            return new ListDataPrice[size];
        }
    };
    private String name, price, status, unit;

    protected ListDataPrice(Parcel in) {
        name = in.readString();
        price = in.readString();
        status = in.readString();
        unit = in.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(status);
        dest.writeString(unit);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
