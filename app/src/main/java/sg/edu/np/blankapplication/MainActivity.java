package sg.edu.np.blankapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Parse intent
        Intent in = getIntent();
        Integer index = in.getIntExtra("index",0);
        User user1 = User.userList.get(index);
        //get  widgets
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        Button followButton = (Button) findViewById(R.id.followButton);
        //set text
        nameTextView.setText(user1.name);
        descriptionTextView.setText(user1.description);
        if(user1.followed){
            followButton.setText("Unfollow");
        }else{
            followButton.setText("Follow");
        }


        //String prevText = (String) display.getText();
        //String rng = String.valueOf(in.getIntExtra("randomNumber",0));
        //display.setText(prevText+" "+rng);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user1.followed){
                    user1.followed=false;
                    followButton.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                }else{
                    user1.followed=true;
                    followButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}