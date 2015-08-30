package space.theninjaguys.www.lifa.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import space.theninjaguys.www.lifa.Helper.AppController;
import space.theninjaguys.www.lifa.Helper.Keys;
import space.theninjaguys.www.lifa.Helper.UserSession;
import space.theninjaguys.www.lifa.Helper.Utils;
import space.theninjaguys.www.lifa.R;


public class LoginActivity extends ActionBarActivity {

    UserSession mSession;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSession = new UserSession(getApplicationContext());

        if (mSession.isUserLoggedIn()) {

            intentToDashBoard();
        } else {

            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

            final EditText etContactNumber = (EditText) findViewById(R.id.loginNumber);
            final EditText etPassword = (EditText) findViewById(R.id.loginPassword);
            Button btLogin = (Button) findViewById(R.id.loginButton);
            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((etContactNumber.getText().toString().trim().length() > 0) && etPassword.getText().toString().trim().length() > 0) {

                        if (Utils.isNetworkAvailable(getApplicationContext())) {

                            mProgressBar.setVisibility(View.VISIBLE);
                            runLogin(Integer.valueOf(etContactNumber.getText().toString().trim()), etPassword.getText().toString().trim());
                        } else {

                            showToastMessage("No Internet available");

                        }

                    } else {

                        showToastMessage("Login Error. Please check all data is entered");

                    }

                }
            });

            TextView tvGoToSignUp = (TextView) findViewById(R.id.textViewSignUp);
            tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(mIntent);

                }
            });

        }

    }

    private void showToastMessage(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private void runLogin(int contactNumber, String password) {

        try {

            JSONObject requestObjectContent = new JSONObject();
            requestObjectContent.put(Keys.USER_CONTACT, contactNumber);
            requestObjectContent.put(Keys.USER_PASSWORD,password);

            JSONObject requestObject = new JSONObject();
            requestObject.put(Keys.USER_OBJECT_KEY,requestObject);

            JsonObjectRequest orderRequest = new JsonObjectRequest(Request.Method.POST,
                    Keys.URL_LOGIN, requestObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject json) {

                    mProgressBar.setVisibility(View.GONE);
                    //try {

                        Log.i("response",json.toString());

                    //} catch (JSONException e) {

                      //  e.printStackTrace();
                    //}

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
        } catch (JSONException e) {

            e.printStackTrace();
        }


    }

    private void intentToDashBoard() {
        Intent mIntent = new Intent(LoginActivity.this, Dashboard.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(mIntent);
        finish();
    }

   /* @Override
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
    }*/
}
