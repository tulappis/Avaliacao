package com.example.buscaapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //permite conexão com a Internet na Thread principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    private void carregarLocalizacao(String ip) throws JSONException, IOException {
        Localização localização = ClienteGeoip.localizar(ip);
        TextView siglaPais = (TextView) findViewById(R.id.siglaPais);
        TextView nomePais = (TextView) findViewById(R.id.nomePais);
        TextView siglaEstado = (TextView) findViewById(R.id.siglaEstado);
        TextView nomeEstado = (TextView) findViewById(R.id.nomeEstado);
        TextView nomeMunicipio = (TextView) findViewById(R.id.nomeMunicipio);
        siglaPais.setText(localização.getCountry_code());
        nomePais.setText(localização.getCountry_name());
        siglaEstado.setText(localização.getRegion_code());
        nomeEstado.setText(localização.getRegion_name());
        nomeMunicipio.setText(localização.getCity());
    }

    public void btnLocalizarOnClick(View v) throws JSONException {
        EditText txtIP = findViewById(R.id.txtID);
        String ip = txtIP.getText().toString();

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            else {
                carregarLocalizacao(ip);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    EditText txtIP = findViewById(R.id.txtID);
                    String ip = txtIP.getText().toString();
                    try {
                        carregarLocalizacao(ip);
                    } catch (IOException | JSONException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}