package com.example.task3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task3.R;
import com.example.task3.adapterBhaibandhu.ItemsItem;
import com.example.task3.adapterBhaibandhu.TypeItem;
import com.example.task3.fragments.TypesFragment;

import java.util.ArrayList;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> implements Filterable {

    private TypesFragment context;
    private List<TypeItem> tiList;
    private List<TypeItem> tiListFull;
    int count = 0;



    public TypeAdapter(TypesFragment context, List<TypeItem> tiList) {
        this.context = context;
        this.tiList = tiList;
//        tiListFull = new ArrayList<>(tiList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_types , parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeItem ti = tiList.get(position);
        holder.type.setText(ti.getType());
        holder.noDamageTo.setText(ti.getNoDamageTo());
        holder.halfDamageTo.setText(ti.getHalfDamageTo());
        holder.doubleDamageTo.setText(ti.getDoubleDamageTo());
        holder.noDamageFrom.setText(ti.getNoDamageFrom());
        holder.halfDamageFrom.setText(ti.getHalfDamageFrom());
        holder.doubleDamageFrom.setText(ti.getDoubleDamageFrom());
        holder.moveDamageClass.setText(ti.getMoveDamageClass());

        boolean isShown = ti.isExpanded();
        holder.show.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return tiList.size();
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            count++ ;
            if (count == 1) {
                tiListFull = new ArrayList<>(tiList);
            }

            List<TypeItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(tiList);
            }else{
                String filterPattern  = constraint.toString().toLowerCase().trim();
                for (TypeItem item : tiListFull){
                    if(item.getType().toLowerCase().contains(constraint)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tiList.clear();
            tiList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView noDamageTo;
        TextView halfDamageTo;
        TextView doubleDamageTo;
        TextView noDamageFrom;
        TextView halfDamageFrom;
        TextView doubleDamageFrom;
        TextView moveDamageClass;
        LinearLayout show ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.textView3);
            noDamageTo = (TextView) itemView.findViewById(R.id.textView11);
            halfDamageTo = (TextView) itemView.findViewById(R.id.textView13);
            doubleDamageTo = (TextView) itemView.findViewById(R.id.textView15);
            noDamageFrom = (TextView) itemView.findViewById(R.id.textView17);
            halfDamageFrom = (TextView) itemView.findViewById(R.id.textView19);
            doubleDamageFrom = (TextView) itemView.findViewById(R.id.textView21);
            moveDamageClass = (TextView) itemView.findViewById(R.id.textView23);
            show = (LinearLayout) itemView.findViewById(R.id.expandableLinearLayoutTypes);
            LinearLayout clickShow = (LinearLayout)itemView.findViewById(R.id.clickShowType);


            clickShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TypeItem listItem = tiList.get(getAdapterPosition());
                    listItem.setExpanded( !listItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });


        }
    }
}
