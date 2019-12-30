package com.iremipek.AcPisir.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentLİstesi = new ArrayList<>();                 //İçinde fragmetn tipinde listeler tutmak için
    List<String> tabbaslikListesi = new ArrayList<>();//Tabları tutmak için


    public TabAdapter(FragmentManager fm, List<Fragment> fralist, List<String> baslikList) {
        super(fm);
        this.fragmentLİstesi = fralist;
        this.tabbaslikListesi = baslikList;

    }

    @Override
    public Fragment getItem(int position) {                             //verilen pozisyona göre istenilen fragmenti döndür.

        return fragmentLİstesi.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLİstesi.size();                                  //Fragment listesinin sayısını tutmak için
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabbaslikListesi.get(position);
    }
}
