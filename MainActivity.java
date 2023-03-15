package com.example.lab4;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button click;
    TextView textView;
    ProgressBar loading;
    private Integer pgrsStatus=0;
    private Integer MaxValue=100;
    private Handler handler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click= findViewById(R.id.click);
        textView=findViewById(R.id.textView);
        loading=findViewById(R.id.progressBar);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are You Sure ");
                builder.setTitle("Alert !!!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressLoad();


                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //AlertDialog alert=builder.create();
                        Toast.makeText(MainActivity.this, "You've Clicked No", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }

            public void progressLoad() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(pgrsStatus<100)
                        {
                            pgrsStatus +=1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                  loading.setProgress(pgrsStatus);
                                  textView.setText(pgrsStatus+"/"+MaxValue);
                                }

                            });

                            try {
                                Thread.sleep(200);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();


            }

        });


    }
}