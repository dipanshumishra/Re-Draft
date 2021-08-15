package com.example.re_draft_d;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.re_draft_d.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

//    NavigationView nav;
//    ActionBarDrawerToggle toggle;
//    DrawerLayout drawerLayout;

    int IMAGE_REQ_CODE = 11;
    int CAMERA_REQ_CODE =14;
    int RESULT_CODE = 200;
    //creating variable binding
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflating binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //google add initialization
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
          binding.adView.loadAd(adRequest);
        ////////////////////////////////////////////

         //Setting buttton start
        binding.settingmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.re_draft_d.Setting.class);
                startActivity(intent);
            }
        });
        //Setting buttton end


//       //////////navigation start
//        nav=(NavigationView)findViewById(R.id.navView);
//        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
//
//        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
//
//                switch(item.getItemId())
//                {
//                    case R.id.home_menubtn:
//                        Toast.makeText(MainActivity.this, "This is Home", Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.setting_menubtn:
//                        Toast.makeText(MainActivity.this, "This is Setting", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.call_menubtn:
//                        Toast.makeText(MainActivity.this, "This is Calling", Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }
//                return true;
//            }
//        });
//
//       //////////navigation end

        //hidding the Action bar
        try {
            getSupportActionBar().hide();
        }
        catch (Exception e){}

        //to select a photo we will use this code
        binding.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,IMAGE_REQ_CODE);
            }
        });

        // for camera permission
        binding.camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                 ActivityCompat.requestPermissions(MainActivity.this,
                         new String[]{Manifest.permission.CAMERA},32);
                }
                else {
                    Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(CameraIntent , CAMERA_REQ_CODE);

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQ_CODE)
        {
            if(data.getData()!=null)
            {
                Uri img_url  = data.getData();
                Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(img_url);

                //output image folder code
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Re-draft");

                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
            }
        }
        if(requestCode==RESULT_CODE)
        {
            Intent intent = new Intent(MainActivity.this, com.example.re_draft_d.ResultActivity.class);
            intent.setData(data.getData());
            startActivity(intent);
        }

       if(requestCode==CAMERA_REQ_CODE){
           Bitmap photo = (Bitmap)data.getExtras().get("data");
           Uri uri = getImageUri(photo);

           Uri img_url  = data.getData();
           Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
           dsPhotoEditorIntent.setData(uri);

           //output image folder code
           dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Re-draft");

           int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
           dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
           startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
       }
    }

  public Uri getImageUri(Bitmap bitmap){
      ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG,100,arrayOutputStream);
      String path = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Title",null);
      return Uri.parse(path);
    }

    //onback pressed close the activity start
    int counter=0;
    @Override
    public void onBackPressed() {
        counter++;
        if(counter==1)
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_LONG).show();
        if(counter==2)
        {
            finishAffinity();  //app close kar dega ye
            super.onBackPressed();
        }
    }
    //onback pressed close the activity end

}