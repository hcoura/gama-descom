package com.apps.coura.decomplicaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.coura.decomplicaapp.model.User;

import info.hoang8f.widget.FButton;

public class StoreActivity extends AppCompatActivity {

    private int mAvailableCoins;
    FButton changeAvatarButton;
    TextView coinsTextView;
    private AlertDialog mLoginDialog;
    private int mSelectedPos;
    private String mNickname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Loja");
        }


        changeAvatarButton = (FButton)findViewById(R.id.avatar_change_button);
        coinsTextView = (TextView)findViewById(R.id.store_available_coins_textView);

        handleCoins();

        changeAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAvatarDialog();
            }
        });

    }

    private void handleCoins() {
        mAvailableCoins = User.getGoldCoins(this) - User.getSpentCoins(this);
        coinsTextView.setText(String.valueOf(mAvailableCoins));

        if (mAvailableCoins > 25 ) {
            changeAvatarButton.setEnabled(true);
            changeAvatarButton.setButtonColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            changeAvatarButton.setEnabled(false);
            changeAvatarButton.setButtonColor(ContextCompat.getColor(this, R.color.disabled_grey));
        }
    }

    private void changeAvatarDialog() {
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
                User.setUserName(StoreActivity.this, mNickname);
                User.setAvatarId(StoreActivity.this, mSelectedPos);
                User.setIsLogged(StoreActivity.this, true);
                User.setSpentCoins(StoreActivity.this, 25);
                handleCoins();
                Toast.makeText(StoreActivity.this, "Apelido e Avatar alterados com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        dialogLoginButton.setText("Salvar!");

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

