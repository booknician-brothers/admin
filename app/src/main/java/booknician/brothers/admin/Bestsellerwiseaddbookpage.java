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

public class Bestsellerwiseaddbookpage extends AppCompatActivity {

    EditText genre_book_description_bs, genre_book_name_bs, genre_author_name_bs, genre_book_stock_bs, genre_daily_price_bs, genre_fix_price_bs, genre_book_price_bs, genre_book_image_bs;

    Button genre_add_btn_bs;

    final FirebaseDatabase database =  FirebaseDatabase.getInstance();

    DatabaseReference Myref = database.getReference();

    DatabaseReference Myrefbook = database.getReference();

    String genrebookdescription, genrebookname, genreauthorname, genrebookstock, genredailyprice, genrefixprice, genrebookprice, genrebookimage;

    public int d=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestseller_wise_add_bookpage);


        genre_book_name_bs=findViewById(R.id.genre_book_name_bs);
        genre_author_name_bs=findViewById(R.id.genre_author_name_bs);
        genre_book_image_bs=findViewById(R.id.genre_book_image_bs);
        genre_book_stock_bs=findViewById(R.id.genre_book_stock_bs);
        genre_daily_price_bs=findViewById(R.id.genre_daily_price_bs);
        genre_fix_price_bs=findViewById(R.id.genre_fix_price_bs);
        genre_book_price_bs=findViewById(R.id.genre_book_price_bs);
        genre_book_description_bs=findViewById(R.id.genre_book_description_bs);

        genre_add_btn_bs=findViewById(R.id.genre_add_btn_bs);

        DatabaseReference fbDb = null;
        if (fbDb == null) {
            fbDb = FirebaseDatabase.getInstance().getReference().child("Best Seller");
        }


        fbDb.child("Best Seller")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        d= (int) dataSnapshot.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        genre_add_btn_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genrebookdescription= genre_book_description_bs.getText().toString();
                genrebookname= genre_book_name_bs.getText().toString();
                genreauthorname= genre_author_name_bs.getText().toString();
                genrebookstock= genre_book_stock_bs.getText().toString();
                genredailyprice= genre_daily_price_bs.getText().toString();
                genrefixprice= genre_fix_price_bs.getText().toString();
                genrebookprice= genre_book_price_bs.getText().toString();
                genrebookimage= genre_book_image_bs.getText().toString();

                d=d+1;

                Myref  = FirebaseDatabase.getInstance().getReference().child("Best Seller").child(String.valueOf(d));

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


                }


                Myrefbook.setValue(saveData_book);
                Intent intent = new Intent(Bestsellerwiseaddbookpage.this, Bookwise.class);
                startActivity(intent);



            }
        });

    }
}
