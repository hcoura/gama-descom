package com.apps.coura.decomplicaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.coura.decomplicaapp.model.Module;
import com.apps.coura.decomplicaapp.model.User;

import java.util.List;

import info.hoang8f.widget.FButton;

/**
 * Created by Henrique Coura on 27/05/2016.
 */
public class LoginActivity extends AppCompatActivity {

    public FButton btnFacebook;
    private AlertDialog mLoginDialog;
    private String mNickname;
    private int mSelectedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnFacebook = (FButton) findViewById(R.id.login_button);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog();
            }
        });


    }

    private void loginDialog() {
        View v = getLayoutInflater().inflate(R.layout.dialog_view_login,
                (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content), false);

        RecyclerView avatarRecyclerView = (RecyclerView) v.findViewById(R.id.login_gridView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        avatarRecyclerView.setHasFixedSize(true);
        avatarRecyclerView.setLayoutManager(layoutManager);

        AvatarAdapter avatarAdapter = new AvatarAdapter(User.mAvatarIds);
        avatarRecyclerView.setAdapter(avatarAdapter);

        final FButton dialogLoginButton = (FButton) v.findViewById(R.id.login_dialog_button);

        dialogLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoginDialog != null) mLoginDialog.dismiss();
                User.setUserName(LoginActivity.this, mNickname);
                User.setAvatarId(LoginActivity.this, mSelectedPos);
                User.setIsLogged(LoginActivity.this, true);
                Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(i);
                finish();
            }
        });

        EditText nicknameEditText = (EditText)v.findViewById(R.id.nickname_editText);
        nicknameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    dialogLoginButton.setEnabled(false);
                } else {
                    dialogLoginButton.setEnabled(true);
                    mNickname = s.toString();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        mLoginDialog = builder.create();
        mLoginDialog.setCancelable(true);
        mLoginDialog.show();
    }

    private class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.ViewHolder> {

        private int[] mAvatarIds;

        public AvatarAdapter(int[] avatarIds) {
            mAvatarIds = avatarIds;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            public FrameLayout layout;

            public ViewHolder(View v) {
                super(v);
                image = (ImageView) v.findViewById(R.id.login_avatar_imageView);
                layout = (FrameLayout) v.findViewById(R.id.login_avatar_layout);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.grid_item_avatar, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your data set at this position
            // - replace the contents of the view with that element
            final ViewHolder vh = holder;

            holder.image.setImageResource(mAvatarIds[position]);

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(mSelectedPos);
                    mSelectedPos = vh.getLayoutPosition();
                    notifyItemChanged(mSelectedPos);
                }
            });

            holder.layout.setSelected(position == mSelectedPos);
        }

        @Override
        public int getItemCount() {
            return mAvatarIds.length;
        }
    }

}
