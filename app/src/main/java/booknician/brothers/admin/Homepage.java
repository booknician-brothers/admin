package booknician.brothers.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    private TextView btn_add_book,btn_order,btn_logout;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        btn_add_book=findViewById(R.id.btn_add_book);
        btn_order=findViewById(R.id.btn_order);
        btn_logout=findViewById(R.id.btn_logout);

        btn_add_book.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.btn_add_book:

                intent=new Intent(Homepage.this,Bookwise.class);
                startActivity(intent);

                break;

            case R.id.btn_order:

                //intent=new Intent(Homepage.this,orderactivity.class);
                //startActivity(intent);

                break;

            case R.id.btn_logout:

                //intent=new Intent(Homepage.this,NewActivity.class);
                //startActivity(intent);

                break;
        }

    }
}
