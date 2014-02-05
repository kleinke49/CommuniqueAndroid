package com.funkypantssoftware.communique.ui;

import static com.funkypantssoftware.communique.core.Constants.Extra.USER;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.funkypantssoftware.communique.R;
import com.funkypantssoftware.communique.core.User;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

public class UserActivity extends BootstrapActivity {

    @InjectView(R.id.iv_avatar) protected ImageView avatar;
    @InjectView(R.id.tv_name) protected TextView name;

    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_view);

        if(getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().getSerializable(USER);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this).load(user.getAvatarUrl()).placeholder(R.drawable.gravatar_icon).into(avatar);

        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));

    }


}
