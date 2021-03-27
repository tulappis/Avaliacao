package com.example.buscaapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClienteGeoip {

    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl) throws MalformedURLException {
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }catch (IOException e){
            e.printStackTrace();

        }

        finally {
            urlConnection.disconnect();
        }
        return "";
    }
    public static Localização localizar(String ip) throws JSONException, MalformedURLException {
        String resposta = request("http://freegeoip.net/json/" + ip);
        JSONObject obj = new JSONObject(resposta);
        String ccode = obj.getString("country_code");
        String cname = obj.getString("country_name");
        String rcode = obj.getString("region_code");
        String rname = obj.getString("region_name");
        String city = obj.getString("city");
        return new Localização(ccode, cname, rcode, rname, city);
    }
}
