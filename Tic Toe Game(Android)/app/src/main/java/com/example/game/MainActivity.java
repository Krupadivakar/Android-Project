package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0-yellow 1-red
    int activeplayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};//2 means unplayed that is nothing in that slot.
    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        //System.out.println(counter.getTag().toString());

        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedcounter] == 2) {
            gameState[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow_coin);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_coin);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            for (int[] Winning : winningposition) {
                if (gameState[Winning[0]] == gameState[Winning[1]] &&
                        gameState[Winning[1]] == gameState[Winning[2]] && gameState[Winning[0]] != 2) {
                    String winner = "Red";
                    if (gameState[Winning[0]] == 0) {
                        winner = "yellow";
                    }

                    //Some one has win the game...........
                    TextView winnerMessage = (TextView) findViewById(R.id.winnermessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public void playagain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        for (int j = 0; j < gameState.length; j++) {
            gameState[j] = 2;
        }
        msg();
    }
//    public void msg(){
//        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
//        for(int i=0;i<gridLayout.getChildCount();i++)//getChildCount gives how many view are present inside the grid layout at present 9
//        {
//            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);//set to an empty image
//        }
//    }
        public void msg(){
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)//getChildCount gives how many view are present inside the grid layout at present 9
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);//set to an empty image
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}