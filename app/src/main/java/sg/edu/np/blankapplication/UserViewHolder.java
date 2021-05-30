package sg.edu.np.blankapplication;

import android.view.View;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView desc;
    public View view;
    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textView);
        desc = itemView.findViewById(R.id.textView2);
        view = itemView;
    }
}
