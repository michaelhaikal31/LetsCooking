package com.goblog.go_login.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.goblog.go_login.R;
import com.goblog.go_login.model.AndroidVersion;

import java.util.Collections;
import java.util.List;

/**
 * Created by haikal on 5/8/2016.
 */
public class AdapterAndroidVersion extends RecyclerView.Adapter<AdapterAndroidVersion.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<AndroidVersion> data= Collections.emptyList();
    AndroidVersion current;
    int currentPos=0;
    public AdapterAndroidVersion(Context context,List<AndroidVersion> data) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public AdapterAndroidVersion.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
       /* final AndroidVersion item = HomeFragment.placeList().get(i);*/
        AndroidVersion item = data.get(i);
        viewHolder.tv_android.setText(item.getAndroid_version_name());
        Glide.with(context)
                .load(item.getAndroid_image_url())
                .centerCrop()
                .into(viewHolder.img_android);
        /*Picasso.with(context)
                .load(android_versions.get(i).getAndroid_image_url())
                .resize(120, 60)
                .into(viewHolder.img_android);*/
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        RelativeLayout frame;
        public ViewHolder(View view) {
            super(view);
            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
            frame = (RelativeLayout)view.findViewById(R.id.placeNameHolder);

        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private AdapterAndroidVersion.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final AdapterAndroidVersion.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}