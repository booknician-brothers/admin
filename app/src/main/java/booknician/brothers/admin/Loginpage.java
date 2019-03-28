package booknician.brothers.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginpage extends AppCompatActivity {

    private EditText edtemail;
    private EditText edtpassword;
    private Button btnsignin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener AuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpass);
        btnsignin = findViewById(R.id.btnsgn);

        mAuth= FirebaseAuth.getInstance();

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail=edtemail.getText().toString();
                String userpassword=edtpassword.getText().toString();
                signinemail(useremail,userpassword);
            }
        });

        AuthListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(Loginpage.this, Homepage.class));
                }

            }
        };

    }


    @Override
    protected  void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(AuthListner);
    }


    public void signinemail(String useremail,String userpassword) {

        if(TextUtils.isEmpty(useremail)|| TextUtils.isEmpty(userpassword)){
            Toast.makeText(Loginpage.this, "Feild empty", Toast.LENGTH_LONG).show();

        }
        else {

            mAuth.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(Loginpage.this, "Sign in problem", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
}
