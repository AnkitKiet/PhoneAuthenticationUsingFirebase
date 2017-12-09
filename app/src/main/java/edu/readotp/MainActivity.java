package edu.readotp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Getting Firebase Instance
        FirebaseAuth auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_start);
        // Custom Activity for phone Authentication
        findViewById(R.id.btnCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
                finish();
            }
        });
        // Start Ui Activity or We can instantiate Our own PhoneAuth Activity, but for that we have to use Firebase Callbacks.. To understand that code is in the PhoneAuth.java Activity
        findViewById(R.id.btnFirebaseUI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                        Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build())).build(), 1000);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()) {
                    startActivity(new Intent(MainActivity.this, SigninActivity.class).putExtra("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
                    finish();
                    return;

                } else {
                    if (response == null) {
                        Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                        Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        Toast.makeText(this, "unkown Error", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }

            }

        }
    }
}
