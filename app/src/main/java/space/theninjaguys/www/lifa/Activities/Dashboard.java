package space.theninjaguys.www.lifa.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import space.theninjaguys.www.lifa.Fragments.BarberList;
import space.theninjaguys.www.lifa.Fragments.BookedHistory;
import space.theninjaguys.www.lifa.Fragments.LogOut;
import space.theninjaguys.www.lifa.Helper.FragmentNavDrawer;
import space.theninjaguys.www.lifa.R;


public class Dashboard extends ActionBarActivity {

    private FragmentNavDrawer mDrawer;
    private String[] navMenuTitles;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(this.getClass().getSimpleName());
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //toolbar.setLogo(R.drawable.ic_launcher);

        mDrawer = (FragmentNavDrawer) findViewById(R.id.drawer_layout);
        mDrawer.setupDrawerConfiguration(
                (ListView) findViewById(R.id.lvDrawer), toolbar,
                R.layout.drawer_list_item, R.id.flContent);

        mDrawer.addNavItem(navMenuTitles[0], navMenuTitles[0],
                BarberList.class);
        mDrawer.addNavItem(navMenuTitles[1], navMenuTitles[1],
                BookedHistory.class);
        //TODO Add about us page
        mDrawer.addNavItem(navMenuTitles[3], navMenuTitles[3],
                LogOut.class);

        // Select default
        if (savedInstanceState == null) {
            mDrawer.selectDrawerItem(0);
            //selectItem(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawer.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {

        if (mDrawer.isDrawerOpen()) {

            //menu.findItem(R.id.action_settings).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        mDrawer.getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        mDrawer.getDrawerToggle().onConfigurationChanged(newConfig);
    }
}
