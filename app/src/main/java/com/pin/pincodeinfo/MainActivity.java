package com.pin.pincodeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText pin;
    private TextView areaDetails;
    Button getDetails;
    String pinCode;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pin=findViewById(R.id.editpin);
        areaDetails=findViewById(R.id.textViewDetails);
        getDetails=findViewById(R.id.button);

        mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCode=pin.getText().toString().trim();

                if(TextUtils.isEmpty(pinCode)){
                    Toast.makeText(MainActivity.this,"Enter Valid Pin Code",Toast.LENGTH_SHORT).show();
                }
                else{
                    GetData(pinCode);
                }
            }
        });




    }

    private void GetData(String pincode) {

        mRequestQueue.getCache().clear();

        String url= "http://www.postalpincode.in/api/pincode/" + pincode;

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray array = response.getJSONArray("PostOffice");
                    if(response.getString("Status").equals("Error")){
                        areaDetails.setText("Invalid PinCode");
                    }
                    else{
                        JSONObject object= array.getJSONObject(0);

                        String district = object.getString("District");
                        String state = object.getString("State");
                        String country = object.getString("Country");
                        String name = object.getString("Name");
                        String taluka = object.getString("Taluk");

                        areaDetails.setText("Details of pin code is : \n" + "District is : " + district + "\n" + "State : "
                                + state + "\n" + "Country : " + country + "\n" +"Name : " + name + "\n" + "Taluka : " + taluka);
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                    areaDetails.setText("Invalid PinCode");

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                areaDetails.setText("Invalid PinCode");
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(jsonObjectRequest);


    }
}