package com.example.radiobutton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageSwitcher;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class MainActivity extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    MediaPlayer mediaPlayer;
    Thread time;
Button plays;
     RadioGroup radioGroup;
     RadioButton radioButton;
    Button btnDisplay;
    Button button;
    TextView textView;
    int imageIds[] = {R.drawable.a, R.drawable.b};
    int count = imageIds.length;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ak);
        mediaPlayer.start();
        time = new Thread() {
            public void run() {
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                finally {
//                    Intent intent=new Intent(MainActivity.this);
//                    startActivity(intent);
//                }
            }
        };
        time.start();
    }


    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        textView=findViewById(R.id.textView);
        imageSwitcher=findViewById(R.id.switcher);
        button=findViewById(R.id.button3);
        plays=findViewById(R.id.play_stop);




        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();
                textView.setText(String.valueOf(radioButton.getText()));


                imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView() {
                        //create a new image view and set its properties
                        ImageView imageView = new ImageView(getApplicationContext());
                        //set scale type image view to fill parent
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        //set the height and width of imageview to fill parent
                        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        return imageView;
                    }
                });
               // Animation in= AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left);
               // Animation out= AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right);
                Animation in= AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left);
                Animation out= AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right);

                //set the animation type of ImageSwitcher
                imageSwitcher.setInAnimation(in);
                imageSwitcher.setOutAnimation(out);


                // imageviver

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex++;
                        if (currentIndex == count)
                            currentIndex = 0;
                        //if(mediaPlayer.isPlaying())
//                     //   {
//                            mediaPlayer.pause();
//                        }else
//                        {
//                            mediaPlayer.start();
//                        }
                        imageSwitcher.setImageResource(imageIds[currentIndex]);

                    }
                });

                plays.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {

                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.start();
                        }
                    }
                });




            }

        });

    }

}



