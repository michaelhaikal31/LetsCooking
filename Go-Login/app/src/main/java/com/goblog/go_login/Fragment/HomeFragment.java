package com.goblog.go_login.Fragment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goblog.go_login.R;
import com.goblog.go_login.activity.DetailActivity;
import com.goblog.go_login.adapter.AdapterAndroidVersion;
import com.goblog.go_login.model.AndroidVersion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haikal on 4/3/2016.
 */
public class HomeFragment extends Fragment{
    public HomeFragment(){  }
    private  RecyclerView recyclerView;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private AdapterAndroidVersion adapter;
    private AndroidVersion androidVersion;
    /*private static ArrayList<AndroidVersion> androidVersions_list;
    private static List<AndroidVersion> data;*/
    private Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private JSONArray jArray;
    private JSONObject json_data;
    private static List<AndroidVersion> data;
    private SearchView searchView = null;
    /*private static final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private static final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };
    private static  final  String url_youtube_android[]={
            "o7VVHhK9zf0",
            "t2YyNGE3Frc",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0",
            "o7VVHhK9zf0"
    };
    public static ArrayList<AndroidVersion> placeList(){
        androidVersions_list = new ArrayList<>();
        for(int i =0; i < android_version_names.length; i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            androidVersion.setUrl_youtube(url_youtube_android[i]);
            androidVersions_list.add(androidVersion);
        }
        return (androidVersions_list);
    }*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        new AsyncLogin().execute();
        toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.Swipelayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncLogin().execute();
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        return rootView;
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://beta.json-generator.com/api/json/get/VkgbkQ8T-");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    json_data = jArray.getJSONObject(i);
                    androidVersion = new AndroidVersion();
                    androidVersion.setAndroid_version_name(json_data.getString("name_cooking"));
                    androidVersion.setAndroid_image_url( json_data.getString("image_cooking"));
                    androidVersion.setUrl_youtube(json_data.getString("image_cooking"));
                    androidVersion.setDescripsi(json_data.getString("descripsi_cooking"));
                    androidVersion.setBahan(json_data.getString("bahan_cooking"));
                    androidVersion.setMasak(json_data.getString("masak_cooking"));
                    data.add(androidVersion);
                }

                // Setup and Handover data to recyclerview
                adapter = new AdapterAndroidVersion(getContext(), data);
                recyclerView = (RecyclerView)getView().findViewById(R.id.home_recyclerView);
                recyclerView.setHasFixedSize(true);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addOnItemTouchListener(new AdapterAndroidVersion.RecyclerTouchListener(getContext(), recyclerView, new AdapterAndroidVersion.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        AndroidVersion AV_data= new AndroidVersion();
                        Intent intent = new Intent(getContext(),DetailActivity.class);
                        try {
                            intent.putExtra(DetailActivity.ID_Image, jArray.optJSONObject(position).getString("image_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            intent.putExtra(DetailActivity.ID_Text, jArray.optJSONObject(position).getString("name_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            intent.putExtra(DetailActivity.ID_Video, jArray.optJSONObject(position).getString("video_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            intent.putExtra(DetailActivity.ID_Descripsi, jArray.optJSONObject(position).getString("descripsi_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            intent.putExtra(DetailActivity.ID_Bahan, jArray.optJSONObject(position).getString("bahan_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            intent.putExtra(DetailActivity.ID_Masak, jArray.optJSONObject(position).getString("masak_cooking"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                new Pair<View, String>(view.findViewById(R.id.tv_android),
                                        getString(R.string.transition_name)),
                                new Pair<View, String>(view.findViewById(R.id.img_android),
                                        getString(R.string.transition_image))
                        );
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Get Search item from action bar and Get Search service
        MenuItem searchItem = menu.findItem(R.id.action_home);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setIconified(false);
        }

        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_home :

                break;
        }*/

        return super.onOptionsItemSelected(item);
    }


}
