package booknician.brothers.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Addbookpage extends AppCompatActivity {

    EditText genre_book_description, genre_book_name, genre_author_name, genre_book_stock, genre_daily_price, genre_fix_price, genre_book_price, genre_book_image;

    Button  genre_add_btn;

    CheckBox checkBox;

    final FirebaseDatabase database =  FirebaseDatabase.getInstance();

    DatabaseReference Myref = database.getReference();

    DatabaseReference Myrefbook = database.getReference();

    DatabaseReference Myrefbook_authwise = database.getReference();

    DatabaseReference Myrefbestseller = database.getReference();


    String genrebookdescription, genrebookname, genreauthorname, genrebookstock, genredailyprice, genrefixprice, genrebookprice, genrebookimage;

    public int d, auth_count,bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_page);

        genre_book_name=findViewById(R.id.genre_book_name);
        genre_author_name=findViewById(R.id.genre_author_name);
        genre_book_image=findViewById(R.id.genre_book_image);
        genre_book_stock=findViewById(R.id.genre_book_stock);
        genre_daily_price=findViewById(R.id.genre_daily_price);
        genre_fix_price=findViewById(R.id.genre_fix_price);
        genre_book_price=findViewById(R.id.genre_book_price);
        genre_book_description=findViewById(R.id.genre_book_description);

        checkBox=findViewById(R.id.checkbox);

        genre_add_btn=findViewById(R.id.genre_add_btn);




        DatabaseReference fbDb = null;
        if (fbDb == null) {
            fbDb = FirebaseDatabase.getInstance().getReference().child("Genre");
        }


        fbDb.child(Genrelist.genre_type_name)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        d= (int) dataSnapshot.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





        genre_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genrebookdescription= genre_book_description.getText().toString();
                genrebookname= genre_book_name.getText().toString();
                genreauthorname= genre_author_name.getText().toString();
                genrebookstock= genre_book_stock.getText().toString();
                genredailyprice= genre_daily_price.getText().toString();
                genrefixprice= genre_fix_price.getText().toString();
                genrebookprice= genre_book_price.getText().toString();
                genrebookimage= genre_book_image.getText().toString();

                if(checkBox.isChecked())
                {
                    DatabaseReference fbDb_bs = null;
                    if (fbDb_bs == null) {
                        fbDb_bs = FirebaseDatabase.getInstance().getReference();
                    }


                    fbDb_bs.child("Best Seller")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    bs= (int) dataSnapshot.getChildrenCount();

                                    bs = bs+1;

                                    Myrefbestseller  = FirebaseDatabase.getInstance().getReference().child("Best Seller").child(String.valueOf(bs));

                                    Map saveData_bestseller = new HashMap();
                                    {

                                        saveData_bestseller.put("name", genrebookname);
                                        saveData_bestseller.put("image", genrebookimage);


                                    }

                                    Myrefbestseller.setValue(saveData_bestseller);
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                }



                DatabaseReference fbDb_authbook = null;
                if (fbDb_authbook == null) {
                    fbDb_authbook = FirebaseDatabase.getInstance().getReference().child("Book author wise");
                }


                fbDb_authbook.child(genreauthorname)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                auth_count = (int) dataSnapshot.getChildrenCount();

                                auth_count++;

                                Myrefbook_authwise= FirebaseDatabase.getInstance().getReference().child("Book author wise").child(genreauthorname).child(String.valueOf(auth_count));

                                Map saveData_book_auth = new HashMap();
                                {

                                    saveData_book_auth.put("name", genrebookname);
                                    saveData_book_auth.put("image", genrebookimage);


                                }


                                Myrefbook_authwise.setValue(saveData_book_auth);
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                d=d+1;

                Myref  = FirebaseDatabase.getInstance().getReference().child("Genre").child(Genrelist.genre_type_name).child(String.valueOf(d));

                Map saveData = new HashMap();
                {

                    saveData.put("name", genrebookname);
                    saveData.put("image", genrebookimage);


                }

                Myref.setValue(saveData);



                Myrefbook= FirebaseDatabase.getInstance().getReference().child("Books").child(genrebookname);

                Map saveData_book = new HashMap();
                {

                    saveData_book.put("Author", genreauthorname);
                    saveData_book.put("Daily Price", genredailyprice);
                    saveData_book.put("Fix Price", genrefixprice);
                    saveData_book.put("Description", genrebookdescription);
                    saveData_book.put("Book In Stock", genrebookstock);
                    saveData_book.put("name", genrebookname);
                    saveData_book.put("image", genrebookimage);


                }


                Myrefbook.setValue(saveData_book);


                Intent intent = new Intent(Addbookpage.this, Genrelist.class);
                startActivity(intent);



            }
        });
    }
}
