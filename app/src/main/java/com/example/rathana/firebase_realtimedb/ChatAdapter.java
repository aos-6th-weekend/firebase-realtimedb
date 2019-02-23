package com.example.rathana.firebase_realtimedb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rathana.firebase_realtimedb.model.Chat;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> chats;

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        if(Chat.OWNER==i)
            view=inflater.inflate(R.layout.owner_chat_item_layout,viewGroup,false);
        else if(Chat.USER==i){
            view =inflater.inflate(R.layout.user_chat_item_layout,viewGroup,false);
        }

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return chats.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Chat chat=chats.get(i);
        if(chat.getType()==Chat.USER)
            viewHolder.profile.setImageResource(R.drawable.user_profile);
        else if(chat.getType()==Chat.OWNER)
            viewHolder.profile.setImageResource(R.drawable.profile);

        viewHolder.msg.setText(chat.getText());
        viewHolder.date.setText(chat.getDate());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public void addChat(Chat chat) {
        this.chats.add(chat);
        notifyItemInserted(chats.size()-1);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView profile;
        private TextView msg,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile=itemView.findViewById(R.id.circleImageView);
            msg=itemView.findViewById(R.id.text);
            date=itemView.findViewById(R.id.date);
        }
    }

}
