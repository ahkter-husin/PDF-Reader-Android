package com.rishabhanand.pdf_reader_android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ViewPDFFiles extends AppCompatActivity {

    Uri uri;
    PDFView pdfView;
    int position = -1,REQUEST_CODE=42;
    String type=null,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdffiles);

        pdfView=(PDFView)findViewById(R.id.pdfView);
        type= (String) getIntent().getStringExtra("type");

        if(type.equals("local")) {
            position=getIntent().getIntExtra("position",-1);
        } else {
            url= (String) getIntent().getStringExtra("url");
        }

        displayPDF();
    }


    private void displayPDF() {

        if(type.equals("local")) {
            pdfView.fromFile(MainActivity.pdfarray.get(position))
                    .enableSwipe(true)
                    .enableAnnotationRendering(true)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        }
        else if (type.equals("url")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }



    }

}
