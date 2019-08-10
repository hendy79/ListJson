package com.hendyapp.listjson;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {
    private String lat,lng,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("User Detail");
        Intent getData = getIntent();

        TextView tv;
        tv = (TextView) findViewById(R.id.did);
        tv.setText(getData.getStringExtra("id"));
        tv = (TextView) findViewById(R.id.dname);
        this.name=getData.getStringExtra("name");
        tv.setText(this.name);
        tv = (TextView) findViewById(R.id.duname);
        tv.setText(getData.getStringExtra("username"));
        tv = (TextView) findViewById(R.id.demail);
        tv.setText(getData.getStringExtra("email"));
        tv = (TextView) findViewById(R.id.dphone);
        tv.setText(getData.getStringExtra("phone"));
        tv = (TextView) findViewById(R.id.dsite);
        tv.setText(getData.getStringExtra("website"));
        tv = (TextView) findViewById(R.id.dstreet);
        tv.setText(getData.getStringExtra("street"));
        tv = (TextView) findViewById(R.id.dsuite);
        tv.setText(getData.getStringExtra("suite"));
        tv = (TextView) findViewById(R.id.dcity);
        tv.setText(getData.getStringExtra("city"));
        tv = (TextView) findViewById(R.id.dzipcode);
        tv.setText(getData.getStringExtra("zipcode"));
        tv = (TextView) findViewById(R.id.dlat);
        this.lat=getData.getStringExtra("lat");
        tv.setText(this.lat);
        tv = (TextView) findViewById(R.id.dlng);
        this.lng=getData.getStringExtra("lng");
        tv.setText(this.lng);
        tv = (TextView) findViewById(R.id.dcname);
        tv.setText(getData.getStringExtra("cname"));
        tv = (TextView) findViewById(R.id.dccp);
        tv.setText(getData.getStringExtra("ccp"));
        tv = (TextView) findViewById(R.id.dcbs);
        tv.setText(getData.getStringExtra("cbs"));
    }
    public void bt1(View view){
        Intent intent = new Intent(Detail.this,MapPage.class);
        intent.putExtra("latitude",this.lat);
        intent.putExtra("longitude",this.lng);
        intent.putExtra("nameofuser",this.name);
        startActivity(intent);
    }

}
