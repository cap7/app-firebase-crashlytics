package com.pe.devfestlima2018.firebasecrashlytics;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener{

    Button email_sign_in_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_sign_in_button = findViewById(R.id.email_sign_in_button);
        email_sign_in_button.setOnClickListener(this);

        Fabric.with(this, new Crashlytics());

        // Iniciando Crashlytics previamente colocar en false
        // firebase_crashlytics_collection_enabled en el Manifest

        // Tambien funciona si el valor de firebase_crashlytics_collection_enabled
        // se encuentra en true

        /*final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)           // Enables Crashlytics debugger
                .build();
        Fabric.with(fabric);*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_sign_in_button:
                // Personalizacion del reporte de errores
                Crashlytics.setUserIdentifier("user123456789");
                Crashlytics.log(Log.DEBUG, "tag", "message2");
                Crashlytics.setString("last_UI_action", "logged_in");
                Crashlytics.getInstance().crash();

                break;
        }
    }

}

