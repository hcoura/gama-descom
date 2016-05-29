package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.Module;

import java.util.List;

/**
 * Created by Henrique Coura on 25/05/2016.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context mContext;
    private List<Module> mModules;

    public ModuleAdapter(List<Module> modules, Context context) {
        mContext = context;
        mModules = modules;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.module_list_item_text_view);
            image = (ImageView) v.findViewById(R.id.module_list_item_image_view);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_module, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ViewHolder vh = holder;
        holder.title.setText(mModules.get(position).getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ModuleActivity.class);
                i.putExtra(ModuleActivity.MODULE_ID_EXTRA, vh.getAdapterPosition());
                mContext.startActivity(i);
            }
        });

        holder.image.setImageResource(mModules.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }
}
