package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.CatalogView;

/**
 * Created by dan on 26.08.16.
 */

public interface CatalogPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateCatalog();
    void setView(CatalogView catalogView);
    void removeView();
}
