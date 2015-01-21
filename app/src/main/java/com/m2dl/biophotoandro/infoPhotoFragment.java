package com.m2dl.biophotoandro;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link infoPhotoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link infoPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoPhotoFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String COORD_X = "coordX";
    private static final String COORD_Y = "coordY";

    // TODO: Rename and change types of parameters
    private String mCoordX;
    private String mCoordY;

    private OnFragmentInteractionListener mListener;
    private View mRootView;
    private TextView mtextViewX;
    private TextView mtextViewY;
    private Button mSendButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param x Parameter 1.
     * @param y Parameter 2.
     * @return A new instance of fragment infoPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static infoPhotoFragment newInstance(String x, String y) {
        infoPhotoFragment fragment = new infoPhotoFragment();
        Bundle args = new Bundle();
        args.putString(COORD_X, x);
        args.putString(COORD_Y, y);
        fragment.setArguments(args);
        return fragment;
    }

    public infoPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCoordX = getArguments().getString(COORD_X);
            mCoordY = getArguments().getString(COORD_Y);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_info_photo, container, false);
        mtextViewX = (TextView) mRootView.findViewById(R.id.textViewX);
        mtextViewY = (TextView) mRootView.findViewById(R.id.textViewY);
        mSendButton = (Button) mRootView.findViewById(R.id.buttonMail);


        mtextViewX.setText(mCoordX);
        mtextViewY.setText(mCoordY);


        return mRootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        public void onFragmentInteraction(Uri uri);
    }

}
