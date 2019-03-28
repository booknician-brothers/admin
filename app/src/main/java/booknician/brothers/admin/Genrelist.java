package booknician.brothers.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Genrelist extends AppCompatActivity implements View.OnClickListener {

    TextView romantic_btn, women_btn, suspense_btn, fantasy_btn, biography_btn, horror_btn, science_btn;

    public static String genre_type_name;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_list);

        romantic_btn=findViewById(R.id.romantic_btn);
        women_btn=findViewById(R.id.women_btn);
        suspense_btn=findViewById(R.id.suspense_btn);
        fantasy_btn=findViewById(R.id.fantasy_btn);
        biography_btn=findViewById(R.id.biography_btn);
        horror_btn=findViewById(R.id.horror_btn);
        science_btn=findViewById(R.id.science_btn);


        romantic_btn.setOnClickListener(this);
        women_btn.setOnClickListener(this);
        suspense_btn.setOnClickListener(this);
        fantasy_btn.setOnClickListener(this);
        biography_btn.setOnClickListener(this);
        horror_btn.setOnClickListener(this);
        science_btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.romantic_btn:

                genre_type_name= "Romantic";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.women_btn:

                genre_type_name= "Women";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.fantasy_btn:

                genre_type_name= "Fantasy";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.suspense_btn:

                genre_type_name= "Suspense";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.biography_btn:

                genre_type_name= "Biography";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.horror_btn:

                genre_type_name= "Horror";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

            case R.id.science_btn:

                genre_type_name= "Science";

                intent= new Intent(Genrelist.this, Addbookpage.class);
                startActivity(intent);

                break;

        }


    }
}
