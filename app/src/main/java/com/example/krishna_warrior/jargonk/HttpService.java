package com.example.krishna_warrior.jargonk;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Ravi on 04/04/15.
 */
public class HttpService extends IntentService {

    private static String TAG = HttpService.class.getSimpleName();

    public HttpService() {
        super(HttpService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i("chandan", "service has received msg from something.");
            String otp = intent.getStringExtra("otp");
            verifyOtp(otp);
           // new votp().execute(otp) ;
        }
    }

    /**
     * Posting the OTP to server and activating the user
     *
     *
     */



    private void verifyOtp(final String otp) {


        //Toast.makeText(getApplicationContext(), "otp is := " + otp, Toast.LENGTH_SHORT).show();
        Log.i("chandan", "msg httpservice1");

//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                Config.URL_VERIFY_OTP, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, response.toString());
//
//                try {
//
//                    JSONObject responseObj = new JSONObject(response);
//
//                    // Parsing json object response
//                    // response will be a json object
//                    boolean error = responseObj.getBoolean("error");
//                    String message = responseObj.getString("message");
//
//                    if (!error) {
//                        // parsing the user profile information
//                        JSONObject profileObj = responseObj.getJSONObject("profile");
//
//                        String name = profileObj.getString("name");
//                        String email = profileObj.getString("email");
//                        String mobile = profileObj.getString("mobile");
//
//                        PrefManager pref = new PrefManager(getApplicationContext());
//                        pref.createLogin(name, email, mobile);


                       Intent intent = new Intent(HttpService.this, MainActivity.class);
                       intent.putExtra("otp", otp);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);
//*/
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("otp", otp);
//
//                Log.e(TAG, "Posting params: " + params.toString());
//                return params;
//            }
//
//        };
//
//        // Adding request to request queue
//        MyApplication.getInstance().addToRequestQueue(strReq);
    }


}
