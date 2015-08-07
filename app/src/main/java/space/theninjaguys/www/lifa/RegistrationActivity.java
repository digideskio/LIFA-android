package space.theninjaguys.www.lifa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import space.theninjaguys.www.lifa.Helper.Keys;
import space.theninjaguys.www.lifa.Helper.UserSession;


public class RegistrationActivity extends ActionBarActivity {

    UserSession mSession;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        final EditText etName = (EditText) findViewById(R.id.name);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etNumber = (EditText) findViewById(R.id.contactNumber);
        Button btRegister = (Button) findViewById(R.id.createAccountButton);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etName.getText().toString().trim().length() > 0) && (etPassword.getText().toString().trim().length() > 0)
                        && (etEmail.getText().toString().trim().length() > 0) && (etNumber.getText().toString().trim().length() > 0)) {

                    if(isNetworkAvailable()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        runLogin(etName.getText().toString().trim(),etEmail.getText().toString().trim(),
                                etPassword.getText().toString().trim(),Integer.valueOf(etNumber.getText().toString().trim()));

                    } else {

                        showToastMessage("No Internet available");

                    }

                } else {

                    showToastMessage("Please check if all data is entered");

                }


            }
        });

    }

    private void showToastMessage(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void runLogin(String name,String email, String password,int contactNumber) {

        JSONObject registrationData = new JSONObject();

        //TODO Create reg data for above json based on format.

        JsonObjectRequest orderRequest = new JsonObjectRequest(Request.Method.GET,
                Keys.URL_REGISTRATION, registrationData, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject json) {

                mProgressBar.setVisibility(View.GONE);
                try {

                    //TODO Handle response and if success save data to user session
                    //TODO And Take run intentToDashboard
                    //TODO If failure show error message


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("VolleyDebug",
                        "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("VolleyDebug", "Error: " + error.getMessage());

                if (error instanceof NetworkError) {

                    Toast.makeText(getApplicationContext(),
                            "NetworkError", Toast.LENGTH_SHORT).show();

                } else if (error instanceof ServerError) {

                    Toast.makeText(getApplicationContext(),
                            "ServerError", Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {

                    Toast.makeText(getApplicationContext(),
                            "AuthFailureError", Toast.LENGTH_SHORT)
                            .show();

                } else if (error instanceof ParseError) {

                    Toast.makeText(getApplicationContext(),
                            "ParseError", Toast.LENGTH_SHORT).show();

                } else if (error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(),
                            "NoConnectionError", Toast.LENGTH_SHORT)
                            .show();

                } else if (error instanceof TimeoutError) {

                    Toast.makeText(getApplicationContext(),
                            "TimeOutError", Toast.LENGTH_SHORT).show();
                }

            }
        });

        orderRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(orderRequest);
        Log.i("VolleyDebug", "Volley Object added to request");
    }

    private void intentToDashBoard() {
        Intent mIntent = new Intent(RegistrationActivity.this, Dashboard.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(mIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
