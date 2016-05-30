package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.Module;
import com.apps.coura.decomplicaapp.model.User;

import java.util.List;

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
        public LinearLayout layout;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.module_list_item_text_view);
            image = (ImageView) v.findViewById(R.id.module_list_item_image_view);
            layout = (LinearLayout) v.findViewById(R.id.module_list_item_layout);

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
        // - get element from your data set at this position
        // - replace the contents of the view with that element
        final ViewHolder vh = holder;
        holder.title.setText(mModules.get(position).getTitle());

        if (User.getUnlockedModule(mContext) >= position && position < 2) {
            holder.image.setImageResource(mModules.get(position).getIcon());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ModuleActivity.class);
                    i.putExtra(ModuleActivity.MODULE_ID_EXTRA, vh.getAdapterPosition());
                    mContext.startActivity(i);
                }
            });
        } else {
            holder.image.setImageResource(mModules.get(position).getLockedIcon());
        }


    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }
}
