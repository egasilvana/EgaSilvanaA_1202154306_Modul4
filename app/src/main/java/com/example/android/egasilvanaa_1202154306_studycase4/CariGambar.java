package com.example.android.egasilvanaa_1202154306_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class CariGambar extends AppCompatActivity {

    private EditText mInputLink;
    private Button mButtonCari;
    private ImageView mTampilGambar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_cari_gambar);

        mInputLink = findViewById(R.id.inputurl);
        mButtonCari = findViewById(R.id.btnCari);
        mTampilGambar = findViewById(R.id.tampilGambar);
    }

    public void SearchImage(View view) {
        loadImageInit();
    }

    private void loadImageInit(){
        String ImgUrl = mInputLink.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImgUrl);
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Membuat Progress Dialog
            mProgressDialog = new ProgressDialog(CariGambar.this);

            // Seting message Progress Dialog
            mProgressDialog.setMessage("Loading...");

            // menampilkan Progress Dialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmap
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // menampung gambar ke imageView
            mTampilGambar.setImageBitmap(bitmap);

            // menghilangkan Progress Dialog
            mProgressDialog.hide();
        }
    }
}
