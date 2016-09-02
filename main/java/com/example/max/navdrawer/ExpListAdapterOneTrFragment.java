package com.example.max.navdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max.navdrawer.Answers.NoTransferAnswerArgs;
import com.example.max.navdrawer.Answers.OneTransferAnswerArgs;

import java.util.ArrayList;

/**
 * Created by MadMax on 22.05.2016.
 */
public class ExpListAdapterOneTrFragment extends BaseExpandableListAdapter {

    private ArrayList<OneTransferAnswerArgs> mGroups;
    private Context mContext;

    public ExpListAdapterOneTrFragment(Context context, ArrayList<OneTransferAnswerArgs> groups){
        mContext = context;
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).getGaps().size();//mGroups.get(groupPosition).size();
    }

    @Override
    public OneTransferAnswerArgs getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public NoTransferAnswerArgs getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).getGaps().get(childPosition); //.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        OneTransferAnswerArgs temp = getGroup(groupPosition);

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText("" + temp.getBus1());
        TextView textGroup2 = (TextView) convertView.findViewById(R.id.textGroup2);
        String s = "От о.\"" + temp.getStartHalt() + "\"(" + temp.getDirection1() + "); пересадка:";
        if(temp.getDirection1().equals("Unknown") || temp.getDirection1().equals("") || temp.getDirection1() == null) {
            s = "От о.\"" + temp.getStartHalt() + "\"" + "; пересадка:";
        }
        textGroup2.setText(s);

        int height = s.length() * 1000 / textGroup2.getMaxWidth() - 35;
        if(height < 70) {
            height = 70;
        }
        textGroup.setHeight(height);
        //Toast.makeText(mContext,"" + s.length() + " " + textGroup2.getMaxWidth(),5000).show();

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        NoTransferAnswerArgs temp = getChild(groupPosition, childPosition);

        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText("" + temp.getBus());
        TextView textChild2 = (TextView) convertView.findViewById(R.id.textChild2);
        String s = "От о.\"" + temp.getStartHalt() + "\"(" + temp.getDirection() + ") до о.\"" + temp.getDestHalt() + "\"";
        if(temp.getDirection().equals("Unknown") || temp.getDirection().equals("") || temp.getDirection() == null) {
            s = "От о.\"" + temp.getStartHalt() + "\"" + " до о.\"" + temp.getDestHalt() + "\"";
        }
        textChild2.setText(s);

        int height = s.length() * 1000 / textChild2.getMaxWidth() - 35;
        if(height < 70) {
            height = 70;
        }
        textChild.setHeight(height);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
