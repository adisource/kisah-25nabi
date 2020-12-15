package id.suryaproject.a25nabi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import id.suryaproject.a25nabi.R;
import id.suryaproject.a25nabi.model.ModelMain;

public  class AdapterMain extends RecyclerView.Adapter<AdapterMain.MyViewHolder> {
    Context mContext;
    ArrayList<ModelMain> modelMains;
    private  OnselectedItem onselectedItem;


    public  interface OnselectedItem{
        void itemSelected(ModelMain modelMain);
    }


    public AdapterMain(Context mContext, ArrayList<ModelMain> modelMains,OnselectedItem onselectedItem) {
        this.mContext = mContext;
        this.modelMains = modelMains;
        this.onselectedItem = onselectedItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nama_nabi.setText(modelMains.get(position).getName());
        holder.name_arab.setText(modelMains.get(position).getName_arab());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelMain main = modelMains.get(position);
                onselectedItem.itemSelected(main);
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelMains.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama_nabi,name_arab;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_nabi = itemView.findViewById(R.id.nama_nabi);
            name_arab =itemView.findViewById(R.id.nama_arab);


        }
    }
}
