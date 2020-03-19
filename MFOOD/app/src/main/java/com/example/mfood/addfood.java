package com.example.mfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class addfood extends AppCompatActivity {
    private static final int REQUESTS_CODE = 1;
    EditText edtName, edtDes, edtPrice;
    Button btSubmitAddFood;
    ImageView imgFood;
    public static Bitmap bitmap=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);
        setID();
        imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUESTS_CODE);
            }
        });
        btSubmitAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information_Food information_food;
                String nameFood, priceFood, DesFood;
                nameFood = edtName.getText().toString().trim();
                priceFood = edtPrice.getText().toString().trim();
                DesFood = edtDes.getText().toString().trim();
                String BITMAP = BitMapToString(bitmap);
                Toast.makeText(addfood.this, BITMAP, Toast.LENGTH_SHORT).show();
                information_food = new Information_Food(nameFood, DesFood, Double.parseDouble(priceFood),BITMAP);
                Intent intent = new Intent();
                Bundle bundle= new Bundle();
                bundle.putSerializable("food", information_food);
                intent.putExtra("data", bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void setID() {
        edtName = findViewById(R.id.edtFoodNameAddFood);
        edtDes = findViewById(R.id.edtDescriptionAddFood);
        edtPrice = findViewById(R.id.edtFoodPriceAddFood);
        btSubmitAddFood = findViewById(R.id.btSubmitAddFood);
        imgFood = findViewById(R.id.imgAddFood);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTS_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                try {
                    bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(), data.getData()));
                    imgFood.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imgFood.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else
            Toast.makeText(getApplicationContext(), "Fail!", Toast.LENGTH_LONG).show();
    }
    public String BitMapToString(Bitmap bitmap){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String result = Base64.encodeToString(bytes,Base64.DEFAULT);
            return result;
    }
}
