package com.example.max.navdrawer.Fragments;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.max.navdrawer.Answers.AnswerParams;
import com.example.max.navdrawer.Answers.NoTransferAnswer;
import com.example.max.navdrawer.Answers.NoTransferAnswerArgs;
import com.example.max.navdrawer.ExpListAdapterOneTrFragment;
import com.example.max.navdrawer.ListAdapterNoTrFragment;
import com.example.max.navdrawer.NetworkInfo;
import com.example.max.navdrawer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoTransferAnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoTransferAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoTransferAnswerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;
    private ArrayList<NoTransferAnswerArgs> results;

    public NoTransferAnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NoTransferAnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoTransferAnswerFragment newInstance(ArrayList<NoTransferAnswerArgs> param1) {
        NoTransferAnswerFragment fragment = new NoTransferAnswerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("noTransferResults", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            results = getArguments().getParcelableArrayList("noTransferResults");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_no_transfer_answer, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listViewNoTrAnswer);
        TextView tvNull = (TextView) rootView.findViewById((R.id.textViewNull));

        if(results.size() < 1) {
            listView.setVisibility(View.INVISIBLE);
            tvNull.setVisibility(View.VISIBLE);
        }
        else if(results.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            tvNull.setVisibility(View.INVISIBLE);

            ListAdapterNoTrFragment adapter = new ListAdapterNoTrFragment(this.getContext(), results);
            listView.setAdapter(adapter);
        }

//            for (final NoTransferAnswerArgs temp : results) {
//                try {
//                    TableRow row = new TableRow(this.getContext());
//                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
//                            TableLayout.LayoutParams.WRAP_CONTENT);
//                    row.setLayoutParams(params);
//
//                    TableRow.LayoutParams param1 = new TableRow.LayoutParams();
//                    param1.setMargins(10, 0, 0, 0);
//                    TableRow.LayoutParams param2 = new TableRow.LayoutParams();
//                    param2.setMargins(5, 0, 0, 0);
////                TableLayout.LayoutParams layoutRow = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
////                        TableLayout.LayoutParams.MATCH_PARENT);
////
////                row.setLayoutParams(layoutRow);
////
////                TableRow.LayoutParams layoutHistory = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
////                        TableRow.LayoutParams.MATCH_PARENT);
////                clock.setLayoutParams(layoutHistory);
////                clock.setText(text);
////
////
////                clock.setGravity(Gravity.CENTER);
////                tbRow.setGravity(Gravity.CENTER);
////                tbRow.addView(clock);
////                table.addView(tbRow);
//
//
//                    int height = 220;
//
//                    TextView tv = new TextView(this.getContext());
//                    tv.setTextSize(30);
//                    tv.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tv.setTypeface(Typeface.SERIF);
//                    tv.setText("" + temp.getBus());
//                    tv.setLayoutParams(param1);
//                    row.setGravity(Gravity.CENTER | Gravity.TOP);
//
//                    TextView tv1 = new TextView(this.getContext());
//                    tv1.setTextSize(16);
//                    tv1.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tv1.setTypeface(Typeface.SERIF);
//                    tv1.setText("-");
//                    tv1.setLayoutParams(param2);
//
//                    TextView tv2 = new TextView(this.getContext());
//                    tv2.setTextSize(16);
//                    tv2.setGravity(Gravity.TOP);
//                    tv2.setTypeface(Typeface.SERIF);
//                    if(temp.getDirection().equals("Unknown") || temp.getDirection().equals("") || temp.getDirection() == null) {
//                        String line = "От о.\"" + temp.getStartHalt() + "\" до о.\""
//                                + temp.getDestHalt() + "\"";
//                        tv2.setText(line);
//                    }
//                    else {
//                        String line = "От о.\"" + temp.getStartHalt() + "\" (" + temp.getDirection() + ") до о.\""
//                                + temp.getDestHalt() + "\"";
//                        tv2.setText(line);
//                    }
//                    tv2.setLayoutParams(param2);
//
//                    if(tv2.getText().length() < 80) {
//                        height = 180;
//                    }
//                    if(tv2.getText().length() < 70) {
//                        height = 160;
//                    }
//                    if(tv2.getText().length() < 60) {
//                        height = 120;
//                    }
//                    if(tv2.getText().length() < 40) {
//                        height = 100;
//                    }
//
//                    tv.setMinHeight(height);
//                    tv1.setMinHeight(height);
//                    tv2.setMinHeight(height);
//
//                    row.addView(tv);
//                    row.addView(tv1);
//                    row.addView(tv2);
//
//                    table.addView(row);
//                } catch (NullPointerException ex) {
//                    Log.i("NULL_POINTER_EXCEPTION", "table_row");
//                }
//            }
//        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
