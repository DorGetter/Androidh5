package com.example.androidseries;


import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {


    Button btn;
    ImageView iv;
    BitmapDrawable drawable;
    Bitmap bitmap;
    String imageString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.submit);

        iv = (ImageView) findViewById(R.id.image_view);


        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        final Python py = Python.getInstance();


        btn.setOnClickListener((v) -> {

            drawable = (BitmapDrawable) iv.getDrawable();
            bitmap = drawable.getBitmap();
            imageString = getStringImage(bitmap);

//            PyObject pyo = py.getModule("find_lanes");
//            PyObject obj = pyo.callAttr("main", imageString);
//
//            String str = obj.toString();
//            byte data[] = android.util.Base64.decode(str, Base64.DEFAULT);
//            Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
//
//            iv.setImageBitmap(bmp);


            PyObject pyo = py.getModule("testModel");
            PyObject obj = pyo.callAttr("main", imageString);
            float steering_angle = obj.toFloat();
            System.out.println("steering:");
            System.out.println(steering_angle);

        });
    }



    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }





    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor assetFileDescriptor = this.getAssets().openFd("model.tflite");
        FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = fileInputStream.getChannel();
        long startoffset = assetFileDescriptor.getStartOffset();
        long length = assetFileDescriptor.getLength();

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startoffset, length);
    }



}