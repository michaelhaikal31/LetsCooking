package com.goblog.go_login.model;

import android.os.Parcelable;

/**
 * Created by haikal on 4/3/2016.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int photoId;

    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int photoId) {
        this.showNotify = showNotify;
        this.title = title;
        this.photoId = photoId;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
