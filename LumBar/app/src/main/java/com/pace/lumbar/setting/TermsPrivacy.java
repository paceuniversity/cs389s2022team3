package com.pace.lumbar.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.pace.lumbar.R;

public class TermsPrivacy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_privacy);

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("LumBar_PrivacyPolicy.pdf").load();
    }
}