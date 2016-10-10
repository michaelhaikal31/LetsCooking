package com.goblog.go_login.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goblog.go_login.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DetailActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {
    public final static String ID = "ID_JSON";
    public final static String ID_Image ="Json_image";
    public final static String ID_Text ="Json_text";
    public final static String ID_Video ="Json_video";
    public final static String ID_Descripsi="Json_descripsi";
    public final static String ID_Bahan ="Json_bahan";
    public final static String ID_Masak ="Json_masak";
    private TextView text;
    private ImageView image;
    public static final String API_KEY = "AIzaSyChmD7FIW3FGEoJWoGtS4J7IrFsMolSZR0";
    public static String VIDEO_ID = null;
    private TextView getIDyoutube;
    private TextView Descripsi;
    private TextView Bahan;
    private TextView Masak;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();

        text = (TextView)findViewById(R.id.text_detail);
        image = (ImageView)findViewById(R.id.image_detail);
        getIDyoutube = (TextView)findViewById(R.id.idyoutube);
        Descripsi = (TextView)findViewById(R.id.descripsi_detail);
        Bahan = (TextView)findViewById(R.id.bahan_detail);
        Masak = (TextView)findViewById(R.id.masak_detail);


        Glide.with(getApplicationContext())
                .load(intent.getStringExtra(DetailActivity.ID_Image))
                .centerCrop()
                .into(image);
        text.setText(intent.getStringExtra(DetailActivity.ID_Text));
        getIDyoutube.setText(intent.getStringExtra(DetailActivity.ID_Video));
        Descripsi.setText(intent.getStringExtra(DetailActivity.ID_Descripsi));
        Bahan.setText(intent.getStringExtra(DetailActivity.ID_Bahan));
        Masak.setText(intent.getStringExtra(DetailActivity.ID_Masak));
        VIDEO_ID = (String) getIDyoutube.getText();
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubeplayerview);
        youTubePlayerView.initialize(API_KEY, this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupWindowAnimations();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupWindowAnimations() {
        /*Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);*/

        Slide slide = new Slide();
        slide.setDuration(350);
        getWindow().setReturnTransition(slide);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getApplicationContext(),
                "onInitializationFailure()",
                Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bookmark) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
