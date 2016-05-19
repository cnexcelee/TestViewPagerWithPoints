package com.example.lijia.testapplication;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    CoordinatorLayout coordinatorLayout;
    // viewpager
    ViewPager mViewPager;
    // points layout
    ViewGroup pointsLayout;
    //pagerview lists
    List<View> mViews = new ArrayList<View>();

    ImageView[] pointsImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化view
     */
    public void initView() {

        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.container);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pointsLayout = (ViewGroup) findViewById(R.id.viewpager_points);

        for (int i = 0; i < 4; i++) {
            View view = mLayoutInflater.inflate(R.layout.viewpager_item, null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.viewpager_imv);
            mImageView.setBackgroundResource(R.drawable.wuzang);
            mViews.add(view);
        }

        pointsImageViews = new ImageView[mViews.size()];
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置每个小圆点距离左边的间距
        margin.setMargins(10, 0, 0, 0);

        for (int i = 0; i < 4; i++) {

            ImageView imageview = new ImageView(this);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            pointsImageViews[i] = imageview;
            //设置 viewpager小点 显示的图片
            if (i == 0) // 选中状态
                pointsImageViews[i].setBackgroundResource(R.drawable.viewpager_point_forced);
            else
                pointsImageViews[i].setBackgroundResource(R.drawable.viewpager_point_unforced);

            pointsImageViews[i].setVisibility(View.VISIBLE);
            pointsLayout.addView(imageview, margin);

        }
        Log.i("TAG","size : " + pointsLayout.getChildCount());
        mViewPager.setAdapter(new MyPagerAdapter());

        mViewPager.addOnPageChangeListener(new MyPagerChangeListener());

    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void testSnackbar(View view) {
        Snackbar.make(coordinatorLayout, "Test", Snackbar.LENGTH_LONG).setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "teSt");
            }
        }).show();
    }

    /**
     * adapter
     */
    class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }
    }

    /**
     * points listener
     */
    class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < pointsImageViews.length; i++) {
                pointsImageViews[i].setBackgroundResource(R.drawable.viewpager_point_unforced);
                if (i == position)
                    pointsImageViews[i].setBackgroundResource(R.drawable.viewpager_point_forced);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
