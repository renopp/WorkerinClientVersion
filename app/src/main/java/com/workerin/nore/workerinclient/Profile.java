package com.workerin.nore.workerinclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Nore on 12/6/16.
 */

public class Profile extends Fragment  {

    private static final int GALLERY_INTENT = 2;
    //Buttons
    private Button buttonChoose, logOut;
    //ImageView
    private ImageView imageView;
    //a Uri object to store file path
    private Uri filePath;
    private  StorageReference storageRef;
    private ProgressDialog progres;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.profile, container, false);
        logOut = (Button) rootView.findViewById(R.id.btnLogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent (Profile.this.getActivity(),Login.class);
                startActivity(intent);
            }
        });

        storageRef = FirebaseStorage.getInstance().getReference();
        //getting views from layout
        buttonChoose = (Button) rootView.findViewById(R.id.btnChoose);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        progres = new ProgressDialog(Profile.this.getActivity());

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            progres.setMessage("Uploading....");
            progres.show();
            Uri uri = data.getData();
            StorageReference filepath = storageRef.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(Profile.this.getActivity()).load(downloadUri).fit().centerCrop().into(imageView);
                    Toast.makeText(Profile.this.getActivity(), "upload done", Toast.LENGTH_SHORT).show();
                    progres.dismiss();

                }
            });


        }
    }
}

