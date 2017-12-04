package com.barley.util;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

    private static final String GOOGLE_API_KEY = "GOOGLE_API_KEY";

    // declare this in a util-class
    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String getGoogleKey() {
        try {
            String key = System.getenv(GOOGLE_API_KEY);
            // if key is null, try system prop
            if(key == null){
                key = System.getProperty(GOOGLE_API_KEY);
            }
            //System.out.println(key);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String endpoint, String jsonRequest) {
        String str = "";
        try {
            URL url = new URL(endpoint);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("connection", "keep-alive");
            // user interaction
            urlConnection.setDoOutput(true);
            //urlConnection.setAllowUserInteraction(false);

            //send query
            sendPostParameters(urlConnection, jsonRequest);

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String l = null;
            while ((l = br.readLine()) != null) {
                str += l;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return str;
    }

    private static void sendPostParameters(URLConnection con, String urlParameters) throws IOException {
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
    }

    public static void halt(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static Date now(){
        return new Date();
    }

    public static Date midnight(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }
}
