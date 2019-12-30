package com.iremipek.AcPisir;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.iremipek.AcPisir.Broadcast.RuntimePermissionActivity;

public class MainActivity extends RuntimePermissionActivity implements NavigationView.OnNavigationItemSelectedListener{

    IntentFilter intentFilter;
    Toolbar toolbar;
    DrawerLayout mDriwerLayout;
    NavigationView navigationView;
    Anasayfa anasayfa;
    private static final int ARAMA_İZNİ = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AÇPİŞİR");


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDriwerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this, mDriwerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDriwerLayout.addDrawerListener(drawerToggle);      //icon değişiyor
        drawerToggle.syncState();

        anasayfa = new Anasayfa();                  //Uygulama ilk açıldığında anasayfa fragmentinden başlamsı için.
        addByFragment(anasayfa);

        String[] istenilenIzinler = {Manifest.permission.READ_PHONE_STATE};             //Oluşturulan broadcast için manifestte oluşturulan izin
        MainActivity.super.izinIste(istenilenIzinler, ARAMA_İZNİ);
    }

    @Override
    public void izinVerildi(int requestCode) {
        Toast.makeText(this,"GEREKEN İZİN VERİLDİ.", Toast.LENGTH_LONG).show();         //Broadcast çalışırsa ekrana yazan mesaj.
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String itemAdi =(String) item.getTitle();
        toolbar.setTitle(itemAdi);                          //basılan kategorinin başlığını değiştirir.

        navigationViewKapat();

        switch (item.getItemId()){
            case R.id.item_anasayfa:                            //Menülerde tıklanılan yere fragment bağlandı
                addByFragment(anasayfa);
                break;
            case R.id.item_corbalar:
                Corba corba = new Corba();
                addByFragment(corba);
                break;
            case R.id.item_arasicaklar:
                Arasicaklar arasicaklar = new Arasicaklar();
                addByFragment(arasicaklar);
                break;
            case R.id.item_anayemekler:
                Anayemekler anayemekler = new Anayemekler();
                addByFragment(anayemekler);
                break;
            case R.id.item_tatlilar:
                Tatlilar tatlilar = new Tatlilar();
                addByFragment(tatlilar);
                break;
            case R.id.item_salatalar:
                Salatalar salatalar = new Salatalar();
                addByFragment(salatalar);
                break;
            case R.id.item_icecekler:
                Icecekler icecekler = new Icecekler();
                addByFragment(icecekler);
                break;
            case R.id.item_cikis:                                   //Menüde cıkıs yerine tıklanıldığında oturumu kapatır.
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
        }

        return true;
    }
    private void navigationViewKapat() {
        mDriwerLayout.closeDrawer(GravityCompat.START);
    }
    private void navigationViewAc() {

        mDriwerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {                                       //Geri Tuşuna basınca uygulama kapanmaması için
        if(mDriwerLayout.isDrawerOpen(GravityCompat.START))
            navigationViewKapat();
        else
        super.onBackPressed();
    }

    public void addByFragment(Fragment fragment){                       //yeni fragment oluşturma metodu
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.relativeLayout, fragment);
        transaction.commit();
    }



}
