package com.mindorks.example.android_dagger2_example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.example.android_dagger2_example.R;
import com.mindorks.example.android_dagger2_example.application.MyApplication;
import com.mindorks.example.android_dagger2_example.data.DataManager;
import com.mindorks.example.android_dagger2_example.data.model.User;
import com.mindorks.example.android_dagger2_example.di.component.DaggerMainActivityComponent;
import com.mindorks.example.android_dagger2_example.di.component.MainActivityComponent;
import com.mindorks.example.android_dagger2_example.di.module.MainActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mNameView;
    private EditText mPasswordView;
    private Button mSignInButton;
    private Button mLogoutButton;
    private TextView mTvUser;
    private TextView mTvUserExists;
    private Button mDeleteButton;
    @Inject
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .dataManagerComponent(MyApplication.get(this).getDataManagerApplicationComponent())
                .build();
        mainActivityComponent.inject(this);
        mNameView = (AutoCompleteTextView) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        mTvUser = (TextView) findViewById(R.id.tv_user);
        mTvUserExists = (TextView) findViewById(R.id.tv_is_user_exists);

        mSignInButton = (Button) findViewById(R.id.insert_user);
        mLogoutButton = (Button) findViewById(R.id.get_user);
        mDeleteButton = (Button) findViewById(R.id.delete_user);

        mSignInButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);

//        mDataManager = mDataManagerComponent.getDataManager();
        refreshData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert_user: {
                String name = mNameView.getText().toString();
                String password = mPasswordView.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "Name or Pwd cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User();
                user.setUsername(name);
                user.setPassword(password);
                mDataManager.createUser(user);
                mDataManager.setLoginFlag(true);

                refreshData();
                break;
            }

            case R.id.get_user: {
                User user = mDataManager.getUser(1L);
                mTvUser.setText(user.toString());
                break;
            }

            case R.id.delete_user: {
                mDataManager.deleteAll(1L);
                refreshData();
                break;
            }
            default:
                break;
        }
    }

    private void refreshData() {
        if (mDataManager.getLoginFlag()) {
            mNameView.setEnabled(false);
            mPasswordView.setEnabled(false);
            mSignInButton.setEnabled(false);
            mTvUserExists.setText("User has exists in db");
        } else {
            mNameView.setEnabled(true);
            mPasswordView.setEnabled(true);
            mSignInButton.setEnabled(true);
            mNameView.setText("");
            mPasswordView.setText("");
            mTvUserExists.setText("");
            mNameView.requestFocus();
            mTvUser.setText("");
        }
    }
}
