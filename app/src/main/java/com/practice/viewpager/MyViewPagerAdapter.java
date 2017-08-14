package com.practice.viewpager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by usera on 2017/8/14.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public static final int FRAG_TYPE_LIKE = 0;
    public static final int FRAG_TYPE_COMMENT = 1;
    public static final int[] FRAG_TYPE_ARRAY = {FRAG_TYPE_LIKE, FRAG_TYPE_COMMENT};
    Map<Integer, Fragment> fragmentMap = new HashMap<>();
    Map<Integer, TabItemViewHolder> tabItemViewHolderMap = new HashMap<>();
    Map<Integer, String> fragmentTitleMap = new HashMap<>();

    public MyViewPagerAdapter(Context cxt, FragmentManager fm) {
        super(fm);
        this.context = cxt;
        fragmentMap.put(FRAG_TYPE_LIKE, BlankFragment.newInstance());
        fragmentMap.put(FRAG_TYPE_COMMENT, BlankFragment2.newInstance());
        fragmentTitleMap.put(FRAG_TYPE_COMMENT, "评论");
        fragmentTitleMap.put(FRAG_TYPE_LIKE, "赞");

        for (int fragType : FRAG_TYPE_ARRAY) {
            TabItemViewHolder tabItemViewHolder = new TabItemViewHolder(LayoutInflater.from(context).inflate(R.layout.tab_item, null));
            tabItemViewHolder.setTitle(fragmentTitleMap.get(fragType));
            tabItemViewHolderMap.put(fragType, tabItemViewHolder);
        }
    }

    public void populateTabLayout(TabLayout tabLayout) {
        if (tabLayout == null || tabLayout.getTabCount() == 0) {
            return;
        }
        for (int i = 0; i < FRAG_TYPE_ARRAY.length; ++i) {
            tabLayout.getTabAt(i).setCustomView(tabItemViewHolderMap.get(FRAG_TYPE_ARRAY[i]).tabItemView);
        }
    }

    public TabItemViewHolder getTabItemViewHolder(int fragType) {
        return tabItemViewHolderMap.get(fragType);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMap.get(FRAG_TYPE_ARRAY[position]);
    }

    @Override
    public int getCount() {
        return FRAG_TYPE_ARRAY.length;
    }


    public static class TabItemViewHolder {
        public static int MAX_UNREAD_MSG_NUM = 99;
        public View tabItemView;
        @BindView(R.id.titleTv)
        TextView titleTv;
        @BindView(R.id.unReadMsgNumTv)
        TextView unReadMsgNumTv;

        public TabItemViewHolder(View tabItemView) {
            this.tabItemView = tabItemView;
            ButterKnife.bind(this, tabItemView);
        }

        public void setTitle(String title) {
            titleTv.setText(title);
        }

        public void hideUnReadMsgNum() {
            unReadMsgNumTv.setVisibility(View.GONE);
        }

        public void setUnReadMsgNum(int num) {
            unReadMsgNumTv.setText(num > MAX_UNREAD_MSG_NUM ? String.valueOf(MAX_UNREAD_MSG_NUM) : String.valueOf(num));
            unReadMsgNumTv.setVisibility(num > 0 ? View.VISIBLE : View.GONE);
        }
    }

}
