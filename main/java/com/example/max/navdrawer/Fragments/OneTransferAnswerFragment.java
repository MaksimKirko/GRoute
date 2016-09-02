package com.example.max.navdrawer.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.max.navdrawer.Answers.OneTransferAnswerArgs;
import com.example.max.navdrawer.ExpListAdapterOneTrFragment;
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
public class OneTransferAnswerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;
    private ArrayList<OneTransferAnswerArgs> results;

    public OneTransferAnswerFragment() {
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
    public static OneTransferAnswerFragment newInstance(ArrayList<OneTransferAnswerArgs> param1) {
        OneTransferAnswerFragment fragment = new OneTransferAnswerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("oneTransferResults", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            results = getArguments().getParcelableArrayList("oneTransferResults");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one_transfer_answer, container, false);
        ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        TextView tvNull = (TextView) rootView.findViewById((R.id.textViewNull));
        if(results.size() < 1) {
            expListView.setVisibility(View.INVISIBLE);
            tvNull.setVisibility(View.VISIBLE);
        }
        else if(results.size() > 0){
            expListView.setVisibility(View.VISIBLE);
            tvNull.setVisibility(View.INVISIBLE);

            ExpListAdapterOneTrFragment adapter = new ExpListAdapterOneTrFragment(this.getContext(), results);
            expListView.setAdapter(adapter);

//            for (OneTransferAnswerArgs temp : results) {
//                try {
//                    TableRow row = new TableRow(this.getContext());
//                    TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
//                            TableLayout.LayoutParams.WRAP_CONTENT);
//                    row.setLayoutParams(params);
//
//                    int height = 220;
//
//                    TableRow.LayoutParams param1 = new TableRow.LayoutParams();
//                    param1.setMargins(10, 0, 0, 0);
//                    TableRow.LayoutParams param2 = new TableRow.LayoutParams();
//                    param2.setMargins(5, 0, 0, 0);
//
//                    TextView tv = new TextView(this.getContext());
//                    tv.setTextSize(30);
//                    tv.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tv.setTypeface(Typeface.SERIF);
//                    tv.setText("" + temp.getBus1());
//                    tv.setLayoutParams(param1);
//
//
//                    TextView tv1 = new TextView(this.getContext());
//                    tv1.setTextSize(16);
//
//                    tv1.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tv1.setTypeface(Typeface.SERIF);
//                    tv1.setText("-");
//                    tv1.setLayoutParams(param2);
//
//
//                    TextView tv2 = new TextView(this.getContext());
//                    tv2.setTextSize(16);
//                    tv2.setGravity(Gravity.TOP);
//                    tv2.setTypeface(Typeface.SERIF);
//                    if(temp.getDirection1().equals("Unknown") || temp.getDirection1().equals("") || temp.getDirection1() == null) {
//                        String line = "От о.\"" + temp.getStartHalt() + "\" до о.\""
//                                + temp.getGapHalt() + "\"; пересадка:";
//                        tv2.setText(line );
//                    }
//                    else {
//                        String line = "От о.\"" + temp.getStartHalt() + "\" (" + temp.getDirection1() + ") до о.\""
//                                + temp.getGapHalt() + "\"; пересадка:";
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
//                    table.addView(row);
//
//                    TableRow row1 = new TableRow(this.getContext());
//                    row1.setLayoutParams(params);
//
//
//                    TextView tp = new TextView(this.getContext());
//                    tp.setTextSize(30);
//                    tp.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tp.setTypeface(Typeface.SERIF);
//                    tp.setText("" + temp.getBus2());
//                    tp.setLayoutParams(param1);
//
//                    TextView tp1 = new TextView(this.getContext());
//                    tp1.setTextSize(16);
//                    tp1.setGravity(Gravity.CENTER | Gravity.TOP);
//                    tp1.setTypeface(Typeface.SERIF);
//                    tp1.setText("-");
//                    tp1.setLayoutParams(param2);
//
//
//                    TextView tp2 = new TextView(this.getContext());
//                    tp2.setTextSize(16);
//                    tp2.setGravity(Gravity.TOP);
//                    tp2.setTypeface(Typeface.SERIF);
//                    if(temp.getDirection2().equals("Unknown") || temp.getDirection2().equals("") || temp.getDirection2() == null) {
//                        String line = "От о.\"" + temp.getGapHalt() + "\" до о.\""
//                                + temp.getDestHalt() + "\"";
//                        tp2.setText(line);
//                    }
//                    else {
//                        String line = "От о.\"" + temp.getGapHalt() + "\" (" + temp.getDirection2() + ") до о.\""
//                                + temp.getDestHalt() + "\"";
//                        tp2.setText(line);
//                    }
//                    tp2.setLayoutParams(param2);
//
//                    if(tp2.getText().length() < 80) {
//                        height = 180;
//                    }
//                    if(tp2.getText().length() < 70) {
//                        height = 160;
//                    }
//                    if(tp2.getText().length() < 60) {
//                        height = 120;
//                    }
//                    if(tp2.getText().length() < 40) {
//                        height = 100;
//                    }
//
//                    tp.setMinHeight(height);
//                    tp1.setMinHeight(height);
//                    tp2.setMinHeight(height);
//
//                    row1.addView(tp);
//                    row1.addView(tp1);
//                    row1.addView(tp2);
//
//                    table.addView(row1);
//                } catch (NullPointerException ex) {
//                    Log.i("NULL_POINTER_EXCEPTION", "table_row");
//                }
//            }
        }

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
