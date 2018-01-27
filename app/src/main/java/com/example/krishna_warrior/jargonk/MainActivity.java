package com.example.krishna_warrior.jargonk;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private ViewPager viewPager;
    //private ViewPagerAdapter adapter;
    private Button btnRequestSms, btnVerifyOtp;
    private EditText inputName, inputEmail, inputMobile, inputOtp;
    private ProgressBar progressBar;
    //private PrefManager pref;
    private ImageButton btnEditMobile;
    private TextView message;
    private LinearLayout layoutEditMobile;
    private DatabaseReference databaseReference,demoref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //SmsManager sms = SmsManager.getDefault();
        //sms.sendTextMessage("8210874017", null, "hello", null, null);
        demoref=databaseReference.child("demo");


        if (getIntent().getExtras() != null) {
            message.setText(getIntent().getExtras().getString("otp"));
            //new PostDataAsyncTask().execute();
            //new votp().execute(getIntent().getExtras().getString("otp"));
           // new SendPostRequest().execute();
            demoref.push().setValue(getIntent().getExtras().getString("otp"));

        }

    }

//    public class PostDataAsyncTask extends AsyncTask<String, String, String> {
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // do stuff before posting data
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//
//                // 1 = post text data, 2 = post file
//                int actionChoice = 1;
//
//                // post a text data
//                if(actionChoice==1){
//                    postText();
//                }
//
//                // post a file
//                else{
//                    //postFile();
//                }
//
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String lenghtOfFile) {
//            // do stuff after posting data
//        }
//    }
//
//    // this will post our text data
//    private void postText(){
//        try{
//            // url where the data will be posted
//            String postReceiverUrl = Config.URL_VERIFY_OTP;
//            Log.v(TAG, "postURL: " + postReceiverUrl);
//
//            // HttpClient
//            HttpClient httpClient = new DefaultHttpClient();
//
//            // post header
//            HttpPost httpPost = new HttpPost(postReceiverUrl);
//
//            // add your data
//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//            nameValuePairs.add(new BasicNameValuePair("firstname", "Mike"));
//            nameValuePairs.add(new BasicNameValuePair("lastname", "Dalisay"));
//            nameValuePairs.add(new BasicNameValuePair("email", "mike@testmail.com"));
//
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//            // execute HTTP post request
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity resEntity = response.getEntity();
//
//            if (resEntity != null) {
//
//                String responseStr = EntityUtils.toString(resEntity).trim();
//                Log.v(TAG, "Response: " +  responseStr);
//
//                // you can add an if statement here and do other actions based on the response
//            }
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public class SendPostRequest extends AsyncTask<String, Void, String> {
//
//        protected void onPreExecute(){}
//
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("https://studytutorial.in/post.php"); // here is your URL path
//
//                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("name", "abc");
//                postDataParams.put("email", "abc@gmail.com");
//                Log.e("params",postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in=new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line="";
//
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                }
//                else {
//                    return new String("false : "+responseCode);
//                }
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getApplicationContext(), result,
//                    Toast.LENGTH_LONG).show();
//        }
//    }


//    private class votp extends AsyncTask<String,Void, String> {
//
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            String otp = strings[0] ;
//            //String url = Config.URL_VERIFY_OTP ;
//            try {
//
//                URL url = new URL(Config.URL_VERIFY_OTP);
//                JSONObject obj=new JSONObject();
//                obj.put("otp",otp);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//                //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                //connection.setRequestProperty("Accept", "*/*");
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(obj));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in=new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line="";
//
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                }
//                else {
//                    return new String("false : "+responseCode);
//                }
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.i("abcd",s);
//        }
//    }
//    public String getPostDataString(JSONObject params) throws Exception {
//
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//
//        Iterator<String> itr = params.keys();
//
//        while(itr.hasNext()){
//
//            String key= itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
//        return result.toString();
//    }

//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    public boolean checkPermission()
//    {
//        int currentAPIVersion = Build.VERSION.SDK_INT;
//        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
//        {
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getApplicationContext(), Manifest.permission.READ_SMS)) {
//                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getApplicationContext());
//                    alertBuilder.setCancelable(true);
//                    alertBuilder.setTitle("Permission necessary");
//                    alertBuilder.setMessage("Write calendar permission is necessary to write event!!!");
//                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions((Activity)getApplicationContext(), new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
//                        }
//                    });
//                    AlertDialog alert = alertBuilder.create();
//                    alert.show();
//                } else {
//                    ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
//                }
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return true;
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_WRITE_CALENDAR:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    writeCalendarEvent();
//                } else {
//                    //code for deny
//                }
//                break;
//        }
//    }
//
//






//        // view click listeners
//        btnEditMobile.setOnClickListener(this);
//        btnRequestSms.setOnClickListener(this);
//        btnVerifyOtp.setOnClickListener(this);
//
//        // hiding the edit mobile number
//        layoutEditMobile.setVisibility(View.GONE);

        //pref = new PrefManager(this);

        // Checking for user session
        // if user is already logged in, take him to main activity
//        if (pref.isLoggedIn()) {
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//
//            finish();
//        }

//        adapter = new ViewPagerAdapter();
//        viewPager.setAdapter(adapter);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


        /**
         * Checking if the device is waiting for sms
         * showing the user OTP screen
         */
//        if (pref.isWaitingForSms()) {
//            viewPager.setCurrentItem(1);
//            layoutEditMobile.setVisibility(View.VISIBLE);
//        }
    //}

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_request_sms:
//                validateForm();
//                break;
//
//            case R.id.btn_verify_otp:
//                verifyOtp();
//                break;
//
//            case R.id.btn_edit_mobile:
//                viewPager.setCurrentItem(0);
//                layoutEditMobile.setVisibility(View.GONE);
//                pref.setIsWaitingForSms(false);
//                break;
//        }
    }

    /**
     * Validating user details form
     */
    private void validateForm() {
//        String name = inputName.getText().toString().trim();
//        String email = inputEmail.getText().toString().trim();
//        String mobile = inputMobile.getText().toString().trim();
//
//        // validating empty name and email
//        if (name.length() == 0 || email.length() == 0) {
//            Toast.makeText(getApplicationContext(), "Please enter your details", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // validating mobile number
//        // it should be of 10 digits length
//        if (isValidPhoneNumber(mobile)) {
//
//            // request for sms
//            progressBar.setVisibility(View.VISIBLE);
//
//            // saving the mobile number in shared preferences
//            pref.setMobileNumber(mobile);
//
//            // requesting for sms
//            requestForSMS(name, email, mobile);
//
//        } else {
//            Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * Method initiates the SMS request on the server
     *
     * @param name   user name
     * @param email  user email address
     * @param mobile user valid mobile number
     */
    private void requestForSMS(final String name, final String email, final String mobile) {
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                Config.URL_REQUEST_SMS, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, response.toString());
//
//                try {
//                    JSONObject responseObj = new JSONObject(response);
//
//                    // Parsing json object response
//                    // response will be a json object
//                    boolean error = responseObj.getBoolean("error");
//                    String message = responseObj.getString("message");
//
//                    // checking for error, if not error SMS is initiated
//                    // device should receive it shortly
//                    if (!error) {
//                        // boolean flag saying device is waiting for sms
//                        pref.setIsWaitingForSms(true);
//
//                        // moving the screen to next pager item i.e otp screen
//                        viewPager.setCurrentItem(1);
//                        txtEditMobile.setText(pref.getMobileNumber());
//                        layoutEditMobile.setVisibility(View.VISIBLE);
//
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(getApplicationContext(),
//                                "Error: " + message,
//                                Toast.LENGTH_LONG).show();
//                    }
//
//                    // hiding the progress bar
//                    progressBar.setVisibility(View.GONE);
//
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
//
//                    progressBar.setVisibility(View.GONE);
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
//                progressBar.setVisibility(View.GONE);
//            }
//        }) {
//
//            /**
//             * Passing user parameters to our server
//             * @return
//             */
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", name);
//                params.put("email", email);
//                params.put("mobile", mobile);
//
//                Log.e(TAG, "Posting params: " + params.toString());
//
//                return params;
//            }
//
//        };
//
//        // Adding request to request queue
//        MyApplication.getInstance().addToRequestQueue(strReq);
    }

    /**
     * sending the OTP to server and activating the user
     */
    private void verifyOtp() {
//        String otp = inputOtp.getText().toString().trim();
//
//        if (!otp.isEmpty()) {
//            Intent grapprIntent = new Intent(getApplicationContext(), HttpService.class);
//            grapprIntent.putExtra("otp", otp);
//            startService(grapprIntent);
//        } else {
//            Toast.makeText(getApplicationContext(), "Please enter the OTP", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * Regex to validate the mobile number
     * mobile number should be of 10 digits length
     *
     * @param mobile
     * @return
     */
    private static boolean isValidPhoneNumber(String mobile) {
//        String regEx = "^[0-9]{10}$";
//        return mobile.matches(regEx);
        return true;
    }


//    class ViewPagerAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == ((View) object);
//        }
//
////        public Object instantiateItem(View collection, int position) {
////
////            int resId = 0;
////            switch (position) {
////                case 0:
////                    resId = R.id.layout_sms;
////                    break;
////                case 1:
////                    resId = R.id.layout_otp;
////                    break;
////            }
////            return findViewById(resId);
////        }
//    }

}
