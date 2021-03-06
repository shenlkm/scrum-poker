package com.buildreams.scrumpoker.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Card extends BaseObservable implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
    private String id;
    private String point;
    private byte image;

    public Card(String id, String point, byte image) {
        this.id = id;
        this.point = point;
        this.image = image;
    }

    // Parcelling part
    public Card(Parcel in) {
        this.id = in.readString();
        this.point = in.readString();
        this.image = in.readByte();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public byte getImage() {
        return image;
    }

    public void setImage(byte image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(this.id);
        dest.writeString(this.point);
        dest.writeByte(this.image);
    }
}
