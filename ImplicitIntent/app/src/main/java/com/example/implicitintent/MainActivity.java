package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText m_website_edit_text;
    private EditText m_share_text_edit_text;
    private EditText m_location_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_website_edit_text = findViewById(R.id.website_edittext);
        m_location_edit_text = findViewById(R.id.location_edittext);
        m_share_text_edit_text= findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {

        String url = m_website_edit_text.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if(intent.resolveActivity(getPackageManager()) != null)  // intent le eşleşip yüklü uygulamaları bulacak
        {
            startActivity(intent);
        }

        else{
            Log.d("ImplicitIntents", "Can't handle this!");
        }

    }

    public void openLocation(View view) {

        String loc = m_location_edit_text.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

        else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }

    }

    public void shareText(View view) {
        String txt = m_share_text_edit_text.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)  //  başlatan activity
                .setType(mimeType)  // mime type to be shared
                .setChooserTitle("Share this text with: ")  // başlık
                .setText(txt)
                .startChooser();
    }
}
