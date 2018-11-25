package com.se.weapp.admin_thetimecondo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_Announ extends RecyclerView.Adapter<Adapter_Announ.UserViewHolder>{
    private List<ItemAnnoun> list;

    public Adapter_Announ(List<ItemAnnoun> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_announ,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int position) {
           ItemAnnoun item = list.get(position);

           userViewHolder.textTitle.setText(item.getTitle());    //item.title เอามาจากหน้า ItemAnnoun
           userViewHolder.textDetail.setText(item.getDetail());
   }

    @Override
    public int getItemCount() {

            return list.size();

    }

    public void notifyDataSetChanged(int index) {
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textDetail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.title_item_announ);
            textDetail = (TextView) itemView.findViewById(R.id.detail_item_announ);
        }
    }
}
