package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText messageEdit;
    private static final String LOG_TAB = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.Activity.extra.MESSAGE";

    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAB, "-------");
        Log.d(LOG_TAB, "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageEdit = findViewById(R.id.editText);

        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState
                        .getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAB, "onStart");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAB, "onPause");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAB, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAB, "onRestart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAB, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAB, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",
                    mReplyTextView.getText().toString());
        }
    }

    public void nextPage(View view) {
        Log.d(LOG_TAB, "Button clicked");
        Intent intent = new Intent(this, SecondPage.class);
        String message = messageEdit.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondPage.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
