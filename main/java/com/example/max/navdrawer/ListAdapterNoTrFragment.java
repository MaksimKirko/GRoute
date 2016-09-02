package com.example.max.navdrawer;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.max.navdrawer.Answers.NoTransferAnswerArgs;
import com.example.max.navdrawer.Answers.OneTransferAnswerArgs;

import java.util.ArrayList;

/**
 * Created by MadMax on 22.05.2016.
 */
public class ListAdapterNoTrFragment implements ListAdapter {
    private ArrayList<NoTransferAnswerArgs> mItems;
    private Context mContext;

    public ListAdapterNoTrFragment(Context context, ArrayList<NoTransferAnswerArgs> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public NoTransferAnswerArgs getItem(int itemPosition) {
        return mItems.get(itemPosition);
    }

    @Override
    public long getItemId(int itemPosition) {
        return itemPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public View getView(int itemPosition, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        NoTransferAnswerArgs temp = getItem(itemPosition);

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText("" + temp.getBus());
        TextView textGroup2 = (TextView) convertView.findViewById(R.id.textGroup2);
        String s = "От о.\"" + temp.getStartHalt() + "\" (" + temp.getDirection() + ") до о.\""
                                + temp.getDestHalt() + "\"";
        if(temp.getDirection().equals("Unknown") || temp.getDirection().equals("") || temp.getDirection() == null) {
            s = "От о.\"" + temp.getStartHalt() + "\" до о.\"" + temp.getDestHalt() + "\"";
        }
        textGroup2.setText(s);

        int height = s.length() * 1000 / textGroup2.getMaxWidth() - 50;
        if(height < 70) {
            height = 70;
        }
        textGroup.setHeight(height);
        //Toast.makeText(mContext,"" + s.length() + " " + textGroup2.getMaxWidth(),5000).show();

        return convertView;

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
