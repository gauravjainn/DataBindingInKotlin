package com.example.gaurav.kotlin_navigationdrawer.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gaurav.kotlin_navigationdrawer.BR;
import com.example.gaurav.kotlin_navigationdrawer.Interface.CustomClickListener;
import com.example.gaurav.kotlin_navigationdrawer.R;
import com.example.gaurav.kotlin_navigationdrawer.databinding.HomeListItemBinding;
import com.example.gaurav.kotlin_navigationdrawer.model.Contact;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> /*implements CustomClickListener */{

    private List<Contact> contactList;
    private Context mContext;

    public HomeAdapter(List<Contact> list, FragmentActivity activity) {
        this.contactList = list;
        this.mContext= activity;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.home_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact dataModel = contactList.get(position);
        holder.bind(dataModel);
     //   holder.homeItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public HomeListItemBinding homeItemBinding;

        public ViewHolder(HomeListItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.homeItemBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            homeItemBinding.setVariable(BR.model, obj);
            homeItemBinding.executePendingBindings();
        }
    }

    public void cardClicked(Contact contact) {
        Toast.makeText(mContext, "You clicked " +  contact.getName(),
                Toast.LENGTH_LONG).show();
    }

}