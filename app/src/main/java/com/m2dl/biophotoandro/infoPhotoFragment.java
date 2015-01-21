package com.m2dl.biophotoandro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private static final String INFOS = "infos";
    private static final int SEND_EMAIL_ACTIVITY_REQUEST_CODE = 200;

    // TODO: Rename and change types of parameters
    private Infos pictureInfos;

    private OnFragmentInteractionListener mListener;
    private View mRootView;
    private TextView mtextViewX;
    private TextView mtextViewY;
    private Button mSendButton;
    private EditText mDestinataire;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment infoPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static infoPhotoFragment newInstance(Parcelable infos) {
        infoPhotoFragment fragment = new infoPhotoFragment();
        Bundle args = new Bundle();
        args.putParcelable(INFOS, infos);
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
            pictureInfos = getArguments().getParcelable(INFOS);
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
        mDestinataire = (EditText) mRootView.findViewById(R.id.editTextMail);


        mtextViewX.setText(String.valueOf(pictureInfos.getCoordX()));
        mtextViewY.setText(String.valueOf(pictureInfos.getCoordY()));


        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dest = mDestinataire.getText().toString();
                EditText com = (EditText) mRootView.findViewById(R.id.editTextComment);
                pictureInfos.setCommentaire(com.getText().toString());
                if (dest != "") {
                    String subject = "Picture BioPic";
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, dest);
                    email.setType("application/image");
                    email.putExtra(Intent.EXTRA_STREAM, pictureInfos.getPictureUri());
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, getContentMail(pictureInfos));

                    startActivityForResult(Intent.createChooser(email, "Choisissez un client de messagerie:"), SEND_EMAIL_ACTIVITY_REQUEST_CODE);
                }
            }
        });

        return mRootView;
    }

    private String getContentMail(Infos picture){
        StringBuilder sb = new StringBuilder();

        sb.append("Date : ");
        sb.append(picture.getDate());

        sb.append("\n");
        sb.append("\n");

        sb.append("Point d'intérêt : \n");

        sb.append("x : ");
        sb.append(picture.getCoordX());
        sb.append("\n");

        sb.append("y : ");
        sb.append(picture.getCoordY());
        sb.append("\n");

        sb.append("\n");
        sb.append("\n");

        sb.append("Clé de caractérisation : " + "TODO" +"\n");

        sb.append("Commentaire :\n");
        sb.append(picture.getCommentaire());

        return sb.toString();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SEND_EMAIL_ACTIVITY_REQUEST_CODE) {

        }
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
