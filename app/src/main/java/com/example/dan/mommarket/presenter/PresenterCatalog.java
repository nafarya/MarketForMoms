package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.ViewCatalog;

/**
 * Created by dan on 26.08.16.
 */

public interface PresenterCatalog {
    void onCreateView(Bundle savedIntanceState);
    void updateCatalog();
    void setView(ViewCatalog viewCatalog);
    void removeView();
}
