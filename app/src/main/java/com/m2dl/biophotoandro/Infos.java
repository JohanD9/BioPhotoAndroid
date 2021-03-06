package com.m2dl.biophotoandro;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jdebat on 21/01/15.
 */
public class Infos implements Parcelable {

    private float coordX;
    private float coordY;

    private float coordXgps;
    private float coordYgps;

    private String commentaire;
    private Uri pictureUri;
    private String date;
    private String dest;

    public void setPictureUri(Uri pictureUri) {
        this.pictureUri = pictureUri;
    }

    public Uri getPictureUri() {

        return pictureUri;
    }

    public void setCoordY(float coordY) {

        this.coordY = coordY;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public void setCoordYgps(float coordYgps) {
        this.coordYgps = coordYgps;
    }

    public void setCoordXgps(float coordXgps) {
        this.coordXgps = coordXgps;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getCoordY() {

        return coordY;
    }

    public float getCoordXgps() {
        return coordXgps;
    }

    public float getCoordYgps() {
        return coordYgps;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getDate() {
        return date;
    }

    public String getDest() {
        return dest;
    }

    public float getCoordX() {

        return coordX;
    }

    public Infos() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(coordX));
        dest.writeString(String.valueOf(coordY));
        dest.writeString(String.valueOf(coordXgps));
        dest.writeString(String.valueOf(coordYgps));
        dest.writeString(commentaire);
        dest.writeString(pictureUri.toString());
        dest.writeString(date);
    }

    public static final Parcelable.Creator<Infos> CREATOR = new Parcelable.Creator<Infos>() {

        @Override
        public Infos createFromParcel(Parcel source) {
            return new Infos(source);
        }

        @Override
        public Infos[] newArray(int size) {
            return new Infos[size];
        }
    };

    public Infos(Parcel in) {
        this.coordX = Float.parseFloat(in.readString());
        this.coordY = Float.parseFloat(in.readString());
        this.coordXgps = Float.parseFloat(in.readString());
        this.coordYgps = Float.parseFloat(in.readString());
        this.commentaire = in.readString();
        this.pictureUri = Uri.parse(in.readString());
        this.date = in.readString();
    }
}
