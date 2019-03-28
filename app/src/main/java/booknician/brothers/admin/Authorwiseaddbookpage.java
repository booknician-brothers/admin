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

    DatabaseReference Myref = database.getReference();

    DatabaseReference Myrefbook = database.getReference();


    DatabaseReference Myref_auth_name = database.getReference();


    String genrebookdescription, genrebookname, genreauthorname, genrebookstock, genredailyprice, genrefixprice, genrebookprice, genrebookimage;

    public  int auth_count = 0, d = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_wise_add_book);

        genre_book_name_auth=findViewById(R.id.genre_book_name_auth);
        genre_author_name_auth=findViewById(R.id.genre_author_name_auth);
        genre_book_image_auth=findViewById(R.id.genre_book_image_auth);
        genre_book_stock_auth=findViewById(R.id.genre_book_stock_auth);
        genre_daily_price_auth=findViewById(R.id.genre_daily_price_auth);
        genre_fix_price_auth=findViewById(R.id.genre_fix_price_auth);
        genre_book_price_auth=findViewById(R.id.genre_book_price_auth);
        genre_book_description_auth=findViewById(R.id.genre_book_description_auth);

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

                genrebookdescription= genre_book_description_auth.getText().toString();
                genrebookname= genre_book_name_auth.getText().toString();
                genreauthorname= genre_author_name_auth.getText().toString();
                genrebookstock= genre_book_stock_auth.getText().toString();
                genredailyprice= genre_daily_price_auth.getText().toString();
                genrefixprice= genre_fix_price_auth.getText().toString();
                genrebookprice= genre_book_price_auth.getText().toString();
                genrebookimage= genre_book_image_auth.getText().toString();



                DatabaseReference fbDb = null;
                if (fbDb == null) {
                    fbDb = FirebaseDatabase.getInstance().getReference().child("Book author wise");
                }


                fbDb.child(genreauthorname)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                d= (int) dataSnapshot.getChildrenCount();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                d = d+1;

                auth_count = auth_count+1;

                Myref  = FirebaseDatabase.getInstance().getReference().child("Book author wise").child(genreauthorname).child(String.valueOf(d));

                Map saveData = new HashMap();
                {

                    saveData.put("name", genrebookname);
                    saveData.put("image", genrebookimage);

                }

                Myref.setValue(saveData);


                Myref_auth_name.child("Author name").child(String.valueOf(auth_count)).child("authorname").setValue(genreauthorname);


                Myrefbook= FirebaseDatabase.getInstance().getReference().child("Books").child(genrebookname);

                Map saveData_book = new HashMap();
                {

                    saveData_book.put("Author", genreauthorname);
                    saveData_book.put("Daily Price", genredailyprice);
                    saveData_book.put("Fix Price", genrefixprice);
                    saveData_book.put("Description", genrebookdescription);
                    saveData_book.put("Book In Stock", genrebookstock);


                }


                Myrefbook.setValue(saveData_book);




                Intent intent = new Intent(Authorwiseaddbookpage.this, Bookwise.class);
                startActivity(intent);






            }
        });


    }
}
