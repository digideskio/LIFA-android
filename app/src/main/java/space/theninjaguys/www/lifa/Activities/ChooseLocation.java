package space.theninjaguys.www.lifa.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import space.theninjaguys.www.lifa.R;


public class ChooseLocation extends AppCompatActivity {

    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyAox_m8Ow4nTjZZXp4PLheteH6eKO7JEZ0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        AutoCompleteTextView atvChooseLocation = (AutoCompleteTextView) findViewById(R.id.chooseLocation);
        atvChooseLocation.setAdapter(new ChooseLocationAutoCompleteAdapter(this,R.layout.location_list_item));
        atvChooseLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

            }
        });


    }

    public static ArrayList autoComplete(String input) {

        ArrayList resultList = null;
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        try {

            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            //sb.append("&components=country:gr");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();

            InputStreamReader isr = new InputStreamReader(conn.getInputStream());

            int read;
            char[] buff = new char[1024];
            while ((read = isr.read(buff)) != -1) {

                jsonResults.append(buff, 0, read);

            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }
        return resultList;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_location, menu);
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

    class ChooseLocationAutoCompleteAdapter extends ArrayAdapter implements Filterable {
        private ArrayList resultList;

        public ChooseLocationAutoCompleteAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int position) {
            return (String) resultList.get(position);
        }

        @Override
        public Filter getFilter() {
            Filter mFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults mFilterResults = new FilterResults();
                    if(constraint != null) {

                        resultList = autoComplete(constraint.toString());

                        mFilterResults.values = resultList;
                        mFilterResults.count = resultList.size();
                    }
                    return mFilterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    if(results != null && results.count > 0) {

                        notifyDataSetChanged();

                    } else {

                        notifyDataSetInvalidated();
                    }
                }
            };

            return mFilter;
        }
    }
}
