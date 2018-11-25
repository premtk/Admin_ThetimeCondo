package com.se.weapp.admin_thetimecondo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private static class ViewHolder {
        TextView title;
        TextView detail;
    }
    private ViewHolder mViewHolder; /// view holder global var ///
    private LayoutInflater mInflater;
    List<ItemAnnoun> mPosts;

    public CustomAdapter(Activity activity, List<ItemAnnoun> posts) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPosts = posts;
    }
    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_announ, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.title = (TextView) convertView.findViewById(R.id.title_item_announ);
            mViewHolder.detail = (TextView) convertView.findViewById(R.id.detail_item_announ);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        ItemAnnoun post = mPosts.get(position);
        mViewHolder.detail.setText(post.detail);
        mViewHolder.title.setText(post.title);
        return convertView;
    }
}
