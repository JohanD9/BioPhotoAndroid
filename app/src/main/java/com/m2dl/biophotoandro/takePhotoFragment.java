package com.m2dl.biophotoandro;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link takePhotoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link takePhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class takePhotoFragment extends android.support.v4.app.Fragment {

    private View mRootView;
    private Button mbuttonSave;
    private Uri imageUri;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final String COORD_X = "coordX";
    private static final String COORD_Y = "coordY";
    private static final String INFOS = "infos";

    float coordX;
    float coordY;

    private Infos pictureInfos;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment takePhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static takePhotoFragment newInstance() {
        takePhotoFragment fragment = new takePhotoFragment();
        return fragment;
    }

    public takePhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pictureInfos = new Infos();
        takePhoto();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_take_photo, container, false);

        mbuttonSave = (Button) mRootView.findViewById(R.id.buttonSavePoi);
        mbuttonSave.setClickable(false);

        mRootView.findViewById(R.id.buttonDeletePoi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView poi = (ImageView) mRootView.findViewById(R.id.imageViewPoi);
                poi.setImageResource(android.R.color.transparent);
                mbuttonSave.setClickable(false);
            }
        });


        mbuttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mRootView.getContext(), "TODO", Toast.LENGTH_LONG).show();
                infoPhotoFragment fragInfo = new infoPhotoFragment();

                Bundle args = new Bundle();
                args.putString(COORD_X, String.valueOf(coordX));
                args.putParcelable(INFOS, pictureInfos);
                args.putString(COORD_Y, String.valueOf(coordY));
                fragInfo.setArguments(args);

                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.container, fragInfo);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

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

    public void takePhoto() {
        //Création d'un intent
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        //Création du fichier image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "BioPhotoAndro_" + timeStamp;
        File photo = new File(Environment.getExternalStorageDirectory(),  imageFileName);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        pictureInfos.setPictureUri(imageUri);

        //On lance l'intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage = imageUri;
        mRootView.getContext().getContentResolver().notifyChange(selectedImage, null);
        ImageView imageView = (ImageView) mRootView.findViewById(R.id.imageViewPhoto);
        switch (requestCode) {
            //Si l'activité était une prise de photo
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {

                    ContentResolver cr = mRootView.getContext().getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, selectedImage);
                        imageView.setImageBitmap(bitmap);
                        //Affichage de l'infobulle
                        Toast.makeText(mRootView.getContext(), "Touchez l'image pour ajouter un point d'intérêt", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(mRootView.getContext(), "Impossible de charger l'image", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(getTag(), "touche");
                coordX = event.getX();
                coordY = event.getY();

                pictureInfos.setCoordX(coordX);
                pictureInfos.setCoordY(coordY);

                ImageView poi = (ImageView) mRootView.findViewById(R.id.imageViewPoi);
                poi.setImageResource(R.drawable.poi);
                poi.setX(coordX - 20);
                poi.setY(coordY - 47);
                mbuttonSave.setClickable(true);

                return true;
            }
        });
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
