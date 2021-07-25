package sg.edu.np.blankapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    ArrayList<User> data;

    public UsersAdapter(Context c, ArrayList<User> d){
        context = c;
        data=d;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user, parent, false);;
        if (viewType == 1) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user, parent, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user_7, parent, false);
        }

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.name);
        holder.desc.setText(u.description);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Profile");
                builder.setMessage(u.name);
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent i = new Intent(context,MainActivity.class);
                        i.putExtra("index",data.indexOf(u));
                        context.startActivity(i);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        String name = data.get(position).name;
        if(name.charAt(name.length()-1) == '7')
        {
            return 0;
        }
        return 1;
    }
}
