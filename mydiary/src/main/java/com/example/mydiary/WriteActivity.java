package com.example.mydiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnSave, btnImage, btnPhoto;
    EditText editTitle, editContent;
    DiaryDAO diaryDAO;
    DiaryVO diaryVO;
    String id = "";
    ImageView imageDiary;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    static final int REQUEST_PHOTO_SELECTION = 3;
    String currentPhotoPath;
    Uri photoURI, selectedImage;
    File photoFile;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

//        currentPhotoPath= diaryVO.getImg();
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        imageDiary = findViewById(R.id.imageDiary);
        btnPhoto = findViewById(R.id.btnPhoto);


        dbHelper = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            id = intent.getExtras().getString("id");
            String title = intent.getExtras().getString("title");
            String content = intent.getExtras().getString("content");
            editTitle.setText(title);
            editContent.setText(content);
            if(intent.getExtras().getString("img") != null) {
                String img = intent.getExtras().getString("img");
                Uri photoURI = Uri.parse(img);
                imageDiary.setImageURI(photoURI);
            }
        }
        //갤러리 버튼
        btnPhoto.setOnClickListener(v -> {
            getPhoto();
        });

        //카메라버튼
        btnImage.setOnClickListener(v -> {
            dispatchTakePictureIntent();
        });


        //등록 & 수정 버튼
        btnSave.setOnClickListener(v -> {
            if (id.equals("")) {
                System.out.println("id" + id);
                diaryDAO = new DiaryDAO();
                diaryVO = new DiaryVO();
                diaryVO.setTitle(editTitle.getText().toString());
                diaryVO.setContent(editContent.getText().toString());
                if(selectedImage != null){
                    diaryVO.setImg(selectedImage.toString());
                }else {
                    diaryVO.setImg(currentPhotoPath);
                }
                diaryDAO.insert(dbHelper, diaryVO);

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(this,"등록완료",Toast.LENGTH_SHORT).show();
            } else {
                diaryDAO = new DiaryDAO();
                diaryVO = new DiaryVO();
                diaryVO.setId(id);
                diaryVO.setTitle(editTitle.getText().toString());
                diaryVO.setContent(editContent.getText().toString());
                if(selectedImage != null){
                    diaryVO.setImg(selectedImage.toString());
                }else {
                    diaryVO.setImg(currentPhotoPath);
                }
                diaryDAO.update(dbHelper, diaryVO);
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent3);
                finish();
                Toast.makeText(this,"수정완료",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO_SELECTION);
    }
    //카메라로 찍으면 파일명정해주고 사진파일의경로 전역변수에 저장
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();  // 파일의 풀경로명
        Toast.makeText(this,"사진저장완료",Toast.LENGTH_SHORT).show();
        return image;
    }
    //카메라버튼 누르면 실행되는 메소드
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//        }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //카메라찍어서가지고오는법
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
        } else if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            //갤러리에서 찾아오는
            imageDiary.setImageURI(photoURI);
        } else if(requestCode == REQUEST_PHOTO_SELECTION && resultCode == RESULT_OK){
            selectedImage = data.getData();
            imageDiary.setImageURI(selectedImage);
        }
    }
}

