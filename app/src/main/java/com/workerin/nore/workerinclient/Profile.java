package com.workerin.nore.workerinclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Nore on 12/6/16.
 */

public class Profile extends Fragment {

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button logOut;

    public Profile() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getActivity().setContentView(R.layout.profile);

        logOut = (Button) getActivity().findViewById(R.id.btnLogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent (Profile.this.getActivity(),Login.class);
                startActivity(intent);
            }
        });*/
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
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
        return rootView;
    }
}
