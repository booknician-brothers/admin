package booknician.brothers.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bookwise extends AppCompatActivity implements View.OnClickListener {

    private Button genre_typ_btn,bookbyauthor_typ_btn,bestseller_typ_btn;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_wise);


        genre_typ_btn=findViewById(R.id.genre_typ_btn);
        bookbyauthor_typ_btn=findViewById(R.id.bookbyauthor_typ_btn);
        bestseller_typ_btn=findViewById(R.id.bestseller_typ_btn);

        genre_typ_btn.setOnClickListener(this);
        bookbyauthor_typ_btn.setOnClickListener(this);
        bestseller_typ_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.genre_typ_btn:

                intent=new Intent(Bookwise.this,Genrelist.class);
                startActivity(intent);

                break;

            case R.id.bestseller_typ_btn:

                intent=new Intent(Bookwise.this,Bestsellerwiseaddbookpage.class);
                startActivity(intent);

                break;

            case R.id.bookbyauthor_typ_btn:

                intent=new Intent(Bookwise.this,Authorwiseaddbookpage.class);
                startActivity(intent);

                break;
        }

    }
}
