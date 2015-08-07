package space.theninjaguys.www.lifa.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import space.theninjaguys.www.lifa.Adapters.BarberListAdapter;
import space.theninjaguys.www.lifa.Helper.Keys;
import space.theninjaguys.www.lifa.Helper.UserSession;
import space.theninjaguys.www.lifa.Models.BarberListItem;
import space.theninjaguys.www.lifa.R;

public class BarberList extends Fragment {

    ListView barberListView;
    BarberListAdapter mAdapter;
    ProgressBar mProgressBar;
    UserSession session;
    ArrayList<BarberListItem> list;
    Button mPromoteButton; //mSellButton
    TextView emptyListView;

    String email_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        session = new UserSession(getActivity());
        HashMap<String, String> user = session.getUserDetails();

        //TODO Get whatever data required for the GET Reqest
        email_id = user.get(Keys.USER_NAME);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_barber_list, container, false);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        emptyListView = (TextView) rootView.findViewById(R.id.empty);

        barberListView = (ListView) rootView
                .findViewById(R.id.listview);
        barberListView.setEmptyView(rootView.findViewById(R.id.empty));
        emptyListView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        if (isNetworkAvailable()) {

            loadBarberList();

        } else {

            showToastMessage("No internet available");
        }

        return rootView;
    }

    private void loadBarberList() {




    }

    private void showToastMessage(String msg) {

        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);


    }
}
