package com.iremipek.AcPisir;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.iremipek.AcPisir.fragments.Frag1;
import com.iremipek.AcPisir.fragments.Frag2;
import com.iremipek.AcPisir.ui.TabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabMenu1 extends AppCompatActivity {
    List<Fragment> fragmentLİstesi = new ArrayList<>();                 //İçinde fragmetn tipinde listeler tutmak için
    List<String> tabbaslikListesi = new ArrayList<>();                  //Tabları tutmak için

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu);

        ViewPager viewPager = findViewById(R.id.view_pager);            //Fragmentler Tek Tek Gösterilecek
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        setData(new Frag1(), "Malzemeler");                     //İlk Tab
        setData(new Frag2(), "Yapılışı");                       //İkinci Tab

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),fragmentLİstesi,tabbaslikListesi);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setData(Fragment yeni, String baslik){
        fragmentLİstesi.add(yeni);              //Frag1 i sayfa 1 e gönderdik
        tabbaslikListesi.add(baslik);
    }
}
