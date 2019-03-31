package booknician.brothers.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Authorwiseaddbookpage extends AppCompatActivity {


    EditText genre_book_description_auth, genre_book_name_auth, genre_author_name_auth, genre_book_stock_auth, genre_daily_price_auth, genre_fix_price_auth, genre_book_price_auth, genre_book_image_auth;

    Button genre_add_btn_auth;

    final FirebaseDatabase database =  FirebaseDatabase.getInstance();

    DatabaseReference Myref_auth_name = database.getReference();


    String genrebookdescription, genrebookname, genreauthorname, genrebookstock, genredailyprice, genrefixprice, genrebookprice, genrebookimage;

    public  int auth_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_wise_add_book);

        genre_author_name_auth=findViewById(R.id.genre_author_name_auth);

        genre_add_btn_auth=findViewById(R.id.genre_add_btn_auth);




        DatabaseReference fbDb_auth = null;
        if (fbDb_auth == null) {
            fbDb_auth = FirebaseDatabase.getInstance().getReference();
        }


        fbDb_auth.child("Author name")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        auth_count = (int) dataSnapshot.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





        genre_add_btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genreauthorname= genre_author_name_auth.getText().toString();

                auth_count = auth_count+1;


                Myref_auth_name.child("Author name").child(String.valueOf(auth_count)).child("authorname").setValue(genreauthorname);

                Intent intent = new Intent(Authorwiseaddbookpage.this, Bookwise.class);
                startActivity(intent);






            }
        });


    }
}
