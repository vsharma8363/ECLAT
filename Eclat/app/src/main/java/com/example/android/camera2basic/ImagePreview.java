package com.example.android.camera2basic;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.camera2basic.CameraActivity;

import java.io.File;import java.lang.Integer;import java.lang.Override;import java.lang.String;

import fbla.hhs.eclat.R;

/**
 * Created by Helen on 1/1/2016.
 */
public class ImagePreview extends Fragment implements View.OnClickListener{

    private String imgSource;
    private Fragment fragment;
    private Integer cameraType;

    public void setImage(File f, Fragment frag, Integer i){
        imgSource=f.toString();
        fragment=frag;
        cameraType=i;
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView myImage=(ImageView)findViewById(R.id.articleImg);
        int id=getResources().getIdentifier(imgSource,"drawable","com.example.android.camera2basic");
       myImage.setImageResource(id);
        (R.layout.image_preview);
    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_preview, null);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        view.findViewById(R.id.close).setOnClickListener(this);
        view.findViewById(R.id.upload).setOnClickListener(this);
        ImageView myImage=(ImageView)view.findViewById(R.id.articleImg);
       // ImageView jpgView = (ImageView)view.findViewById(R.id.jpgview);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        //String myJpgPath = "/sdcard/DSC_3509.JPG"; //UPDATE WITH YOUR OWN JPG FILE

        //jpgName.setText(myJpgPath);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        //myImage.setImageBitmap(bm);
        Bitmap bm = BitmapFactory.decodeFile(imgSource);
       // myImage.setImageBitmap(bm);
        //myImage.setRotation(90);
        //myImage.setScaleType(ImageView.ScaleType.FIT_XY);


        Matrix matrix = new Matrix();

        if(cameraType==1) {
            matrix.preScale(-1, 1);
            matrix.postRotate(90);
        }else{
            matrix.postRotate(90);
        }

        float scale=(float)(width)/(float)(bm.getWidth());

        matrix.postScale(scale,scale);
        /*int x=dm.widthPixels;
        while(x<=0){
            x=dm.widthPixels;
        }
        */

        //Bitmap myImg = getBitmapFromDrawable(imgview.getDrawable());

        Bitmap rotated =  Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),matrix,true);

        myImage.setImageBitmap(rotated);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close: {
                switchFrag();
                break;
            }
            case R.id.upload:{
                uploadPicture();
                break;
            }
            /*case R.id.info: {
                Activity activity = getActivity();
                if (null != activity) {
                    new AlertDialog.Builder(activity)
                            .setMessage(R.string.intro_message)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
                break;
            }*/
        }
    }

    private void uploadPicture(){
        /**
         * HEY VIKRAM
         * Put the code here :)
         */
        File file=new File(imgSource);//the picture
    }

    private void switchFrag(){
        /*FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        getActivity().startActivity(intent);
        File file = new File(imgSource);
        boolean deleted = file.delete();
        onStop();
    }
}
