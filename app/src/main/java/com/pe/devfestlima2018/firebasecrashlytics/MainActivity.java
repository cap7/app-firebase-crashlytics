package com.pe.devfestlima2018.firebasecrashlytics;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    Button btStartCrash;
    Button btCustom;
    Button btCustomKey;
    Button btIdUser;
    Button btException;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStartCrash = findViewById(R.id.btStartCrash);
        btCustom = findViewById(R.id.btCustom);
        btCustomKey = findViewById(R.id.btCustomKey);
        btIdUser = findViewById(R.id.btIdUser);
        btException = findViewById(R.id.btException);

        btStartCrash.setOnClickListener(this);
        btCustom.setOnClickListener(this);
        btCustomKey.setOnClickListener(this);
        btIdUser.setOnClickListener(this);
        btException.setOnClickListener(this);

        // Inicialización
        Fabric.with(this, new Crashlytics());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btStartCrash:
                // Crash forzado
                Crashlytics.getInstance().crash();
                break;
            case R.id.btCustom:
                // Registro personalizado
                Crashlytics.log("Error en la clase MainActivity");
                Crashlytics.getInstance().crash();
                break;
            case R.id.btCustomKey:
                // Claves personalizadas
                Crashlytics.setString("A", "Android");
                Crashlytics.setBool("B", true);
                Crashlytics.setDouble("C", 3.14);
                Crashlytics.setFloat("D", 6.0f);
                Crashlytics.setInt("E", 2018);
                Crashlytics.getInstance().crash();
                break;
            case R.id.btIdUser:
                // ID de Usuario
                Crashlytics.setUserIdentifier("user123456789A");
                Crashlytics.getInstance().crash();
                break;
            case R.id.btException:
                // Excepciones no fatales
                try {
                    Crashlytics.getInstance().crash();
                }catch (Exception e){
                    Toast.makeText(this,"Excepción no fatal",Toast.LENGTH_LONG).show();
                    Crashlytics.logException(e);
                }
                break;
        }
    }

}

