package com.iremipek.AcPisir.Broadcast;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public abstract class RuntimePermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void izinIste(final String[] istenilenIzinler, final int requestCode) {


        int izinKontrol = PackageManager.PERMISSION_GRANTED;
        boolean mazeretGoster = false;


        //izinkontrol=0 ise izin/izinler verilmiştir
        //aksi durumda izin/izinler verilmemiştir.
        //mazeret göster = false ise ilk defa izin sorulmustur
        //mazeret goster= true ise kullanıcı izni reddetmiştir, ona bir mazeret sunabiliriz.
        for (String izin : istenilenIzinler) {

            izinKontrol = izinKontrol + ContextCompat.checkSelfPermission(this, izin);
            mazeretGoster = mazeretGoster || ActivityCompat.shouldShowRequestPermissionRationale(this, izin);
        }

        if (izinKontrol != PackageManager.PERMISSION_GRANTED) {

            if (mazeretGoster) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Neden İzin Vermelisin?");
                builder.setMessage("Arama yapmak istiyorsan bu izni vermen gerekiyor");
                builder.setNegativeButton("IZIN YOK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("İZİN VERMEK ISTIYORUM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(RuntimePermissionActivity.this, istenilenIzinler, requestCode);
                    }
                });

                builder.show();

            } else {
                ActivityCompat.requestPermissions(RuntimePermissionActivity.this, istenilenIzinler, requestCode);
            }

        } else {

            izinVerildi(requestCode);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int izinKontrol = PackageManager.PERMISSION_GRANTED;


        //izinkontrol=0 ise tüm izinler verilmiştir
        for (int izinDurumu : grantResults) {

            izinKontrol = izinKontrol + izinDurumu;

        }

        if ((grantResults.length > 0) && izinKontrol == PackageManager.PERMISSION_GRANTED) {

            izinVerildi(requestCode);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("İzin vermeniz gerekiyor.");
            builder.setMessage("Ayarladan tüm izinleri vermen gerekiyor");
            builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);

                }
            });
            builder.show();
        }
    }
    public abstract void izinVerildi(int requestCode);


}
