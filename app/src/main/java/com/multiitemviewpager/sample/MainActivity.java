package com.multiitemviewpager.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiItemViewPager viewPager = (MultiItemViewPager) findViewById(R.id.viewPager);
        viewPager.setPageMargin(5);
        viewPager.setPageOffset(0.2f);
        viewPager.setVisiblePageCount(5);
        viewPager.setBorderColor(Color.YELLOW);
        viewPager.setBorderVisible(true);
        viewPager.setAdapter(new MyAdapter());

        ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new MyAdapter2(viewPager2));

    }

    public class MyAdapter extends PagerAdapter {

        List<String> data;

        public MyAdapter() {
            data = getImageData();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);

            Glide.with(container.getContext()).load(data.get(position))
                    .placeholder(new ColorDrawable(Color.BLUE))
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public class MyAdapter2 extends PagerAdapter {

        private float mPageWidth;
        List<String> data;

        public MyAdapter2(ViewPager viewPager) {
            data = getImageData();
            setUpViewPager(viewPager,3,0.3f);
        }

        public void setUpViewPager(ViewPager viewPager,int pageSize,float offset){
            viewPager.setClipToPadding(false);
            //viewPager.setClipChildren(false);
            viewPager.setPageMargin(15);
            viewPager.setOffscreenPageLimit(pageSize);
            int widthPixels = viewPager.getResources().getDisplayMetrics().widthPixels;
            float pageWidth = widthPixels/pageSize;
            pageWidth = pageWidth + pageWidth*offset;
            int padding = (int) ((widthPixels - pageWidth) / 2);
            mPageWidth = pageWidth/(pageWidth+padding);
            viewPager.setPadding(padding, 0, 0, 0);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);

            Glide.with(container.getContext()).load(data.get(position))
                    .placeholder(new ColorDrawable(Color.BLUE))
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public float getPageWidth(int position) {
            return mPageWidth;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private List<String> getImageData() {
        List<String> data = new ArrayList<>();
        data.add("http://p1.meituan.net/movie/d178615b3904cc900d2bc57349ad1c9f81016.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/7a1ab391b0d4261f38947f902c921bc4909744.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/4918e718c9621592722ede0b50e1d701662342.jpg@160w_220h_1e_1c");
        data.add("http://p1.meituan.net/movie/4babdacb18347ebf750e59a9de39ca8a389662.png@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/6ef7e98e0db17cb014fba5f5818cbbee293702.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/545c214c28a9a3c545cb4d9bd6d1f4dd310303.jpg@160w_220h_1e_1c");
        data.add("http://p1.meituan.net/movie/a5b276d9cdccc4de44f9d3ca29bef9f0170257.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/c0d2daa78b579d32ff4320b92105ae48261895.jpg@160w_220h_1e_1c");
        data.add("http://p1.meituan.net/movie/13d4883803f25244d266c7cd3ac2f15d327618.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/4918e718c9621592722ede0b50e1d701662342.jpg@160w_220h_1e_1c");
        data.add("http://p1.meituan.net/movie/4babdacb18347ebf750e59a9de39ca8a389662.png@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/6ef7e98e0db17cb014fba5f5818cbbee293702.jpg@160w_220h_1e_1c");
        data.add("http://p0.meituan.net/movie/545c214c28a9a3c545cb4d9bd6d1f4dd310303.jpg@160w_220h_1e_1c");
        data.add("http://p1.meituan.net/movie/a5b276d9cdccc4de44f9d3ca29bef9f0170257.jpg@160w_220h_1e_1c");
        return data;
    }
}
