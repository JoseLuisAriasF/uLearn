package com.example.ulearn.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ulearn.MainActivity;
import com.example.ulearn.R;
import com.example.ulearn.activity_login;
import com.example.ulearn.activity_splash;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView miid;
    private FirebaseAuth mAuth;
    //DESLOGUEARSE
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);




        final TextView miid = root.findViewById(R.id.tbid);
        final TextView minombre = root.findViewById(R.id.tbnombre);
        final TextView micorreo = root.findViewById(R.id.tbcorreo);
        final ImageView mifoto = root.findViewById(R.id.ivuser);
        final Button btnLogout = root.findViewById(R.id.cerrarsesion);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentuser=mAuth.getCurrentUser();
        miid.setText(currentuser.getUid());
        minombre.setText(currentuser.getDisplayName());
        micorreo.setText(currentuser.getEmail());
        Glide.with(this).load(currentuser.getPhotoUrl()).into(mifoto);





       // miid.setText("hola");
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    public void logout(View view){

            mAuth.signOut();

        Intent mainActivity = new Intent(getActivity(),
                activity_login.class);
        startActivity(mainActivity);

        this.getActivity().finish(); //en tu fragment deseado.


    }




}