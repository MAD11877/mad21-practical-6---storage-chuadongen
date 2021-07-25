package sg.edu.np.blankapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ArrayList<User> userList = User.userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        for(int i=1;i<21;i++){
            int randomNo = (int) (Math.random()*100);
            String randomNumber = String.valueOf(randomNo);
            boolean fol;
            if(i%2==0){
                fol=false;
            }else{
                fol=true;
            }
            userList.add(new User("Name "+randomNumber,"Description "+randomNumber,randomNo,fol));
        }
        DBHandler db = new DBHandler(this,null,null,1);
        if(db.GetUsers().size()==0){
            for(User u:userList){
                db.AddUser(u);
            }
        }else{
            userList.clear();
            userList = db.GetUsers();
        }

        RecyclerView rv = findViewById(R.id.recyclerView1);
        UsersAdapter adapter = new UsersAdapter(this,userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        ImageView middleImageView = (ImageView) findViewById(R.id.middleImageView);
//        middleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                builder.setTitle("Profile");
//                builder.setMessage("MADitate");
//                builder.setCancelable(false);
//                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        int randomNumber = (int) (Math.random()*100);
//                        Intent in = new Intent(ListActivity.this,MainActivity.class);
//                        in.putExtra("randomNumber",randomNumber);
//                        startActivity(in);
//                    }
//                });
//                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
    }
}