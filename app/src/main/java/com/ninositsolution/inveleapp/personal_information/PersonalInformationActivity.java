package com.ninositsolution.inveleapp.personal_information;
import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityPersonalInformationBinding;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class PersonalInformationActivity extends AppCompatActivity {
    private static final String TAG = "PersonalActivity";

    ActivityPersonalInformationBinding binding;
    PersonalInformationVM personalInformationVMGlobal;
    private static final String IMAGE_DIRECTORY = "/invele";
    private int GALLERY = 1, CAMERA = 2;
    public String gender;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_information);
        personalInformationVMGlobal = ViewModelProviders.of(this).get(PersonalInformationVM.class);
        binding.setPersonalInfo(personalInformationVMGlobal);
        binding.setLifecycleOwner(this);
        context = PersonalInformationActivity.this;
        binding.dateEdittext.setInputType(InputType.TYPE_NULL);
        Session.setImagePath("",this);
        requestMultiplePermissions();


        personalInformationVMGlobal.getPersonalInfoApi(Session.getUserId(context));
        personalInformationVMGlobal.getPersonalInfoDetailsMutableLiveData().observe(PersonalInformationActivity.this, new Observer<PersonalInformationVM>() {
            @Override
            public void onChanged(@Nullable PersonalInformationVM personalInformationVM)
            {
                if (!personalInformationVM.status.get().isEmpty())
                {
                    if (personalInformationVM.status.get().equalsIgnoreCase("success"))
                    {

                        binding.setPersonalInfo(new PersonalInformationVM(personalInformationVM.user.get()));
//                       personalInformationVMGlobal.firstName.set(personalInformationVM.user.get().first_name);
//                       personalInformationVMGlobal.lastName.set(personalInformationVM.user.get().last_name);
//                       personalInformationVMGlobal.mobileNumber.set(personalInformationVM.user.get().mobile);
//                       personalInformationVMGlobal.emailAddress.set(personalInformationVM.user.get().email);
//                       personalInformationVMGlobal.dateOfBirth.set(personalInformationVM.user.get().dob);

//                       binding.genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                            @Override
//                            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                switch (checkedId)
//                                {
//                                    case R.id.personGenderMale:
//                                        gender = "Male";
//                                        break;
//
//                                    case R.id.personGenderFemale:
//                                        gender = "Female";
//                                        break;
//
//
//                                }
//                            }
//                        });


                    }
                }
            }
        });



        binding.setIpersonalInfo(new IPersonalInformation() {
            @Override
            public void onUpdateProfileClicked() {

                int status = personalInformationVMGlobal.personalValidation();
                if (status == Constants.NAME_EMPTY)
                {
                    binding.personfirstName.setError("Required");
                    binding.personfirstName.requestFocus();
                }

                if (status == Constants.EMAIL_EMPTY)
                {
                    binding.personEmail.setError("Required");
                    binding.personEmail.requestFocus();
                }
                if (status == Constants.MOBILE_NO_EMPTY)
                {
                    binding.personMobileNumber.setError("Required");
                    binding.personMobileNumber.requestFocus();
                }
                if (status == Constants.DATE_OF_BIRTH_EMPTY)
                {
                    binding.dateEdittext.setError("Required");
                    binding.dateEdittext.requestFocus();
                }

                else if (status == Constants.SUCCESS)

                {
                    showProgressBar();
                    personalInformationVMGlobal.profileUpdateApi(Integer.parseInt(Session.getUserId(PersonalInformationActivity.this)),Session.getImagePath(PersonalInformationActivity.this),gender);
                    personalInformationVMGlobal.getPersonalInformationMutableLiveData().observe(PersonalInformationActivity.this, new Observer<PersonalInformationVM>() {
                        @Override
                        public void onChanged(@Nullable PersonalInformationVM personalInformationVM) {
                            if (!personalInformationVM.status.get().isEmpty()) {
                                if (personalInformationVM.status.get().equalsIgnoreCase("success")) {
                                    hideProgressBar();
                                    Toast.makeText(PersonalInformationActivity.this, "" + personalInformationVM.msg.get(), Toast.LENGTH_LONG).show();
                                    personalInformationVM.status.get();
                                    finish();
                                    Log.d(TAG, "status: ->" + personalInformationVM.status.get());
                                    personalInformationVM.status.set("");

                                } else {
                                    if (personalInformationVM.status.get().equalsIgnoreCase("error")) {
                                        hideProgressBar();
                                        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_LONG).show();
                                        Log.d(TAG, "status: ->" + personalInformationVM.status.get());
                                        personalInformationVM.status.set("");

                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }

            @Override
            public void omImageViewClicked() {

                showPictureDialog();


            }

            @Override
            public void onDateEditTextClicked() {

                showDatePickerDialog();

            }
        });


    }

    private void showPictureDialog()
    {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();

    }

    private void takePhotoFromCamera()
    {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    private void choosePhotoFromGallery()
    {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(PersonalInformationActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    binding.profileImage.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(PersonalInformationActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            binding.profileImage.setImageBitmap(thumbnail);
            saveImage(thumbnail);
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "Path is:--->" + f.getAbsolutePath());
            Session.setImagePath(f.getAbsolutePath(), PersonalInformationActivity.this);

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void  requestMultiplePermissions()
    {

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 555);
        }
    }

    public void showDatePickerDialog() {

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

        newFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                binding.dateEdittext.setText(datePicker.getDayOfMonth()+"-"+(datePicker.getMonth() + 1) +"-" +datePicker.getYear());

            }
        });

    }

    private void showProgressBar()
    {
        if (binding.updateInfoProgress.getVisibility() == View.GONE)
            binding.updateInfoProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.updateInfoProgress.getVisibility() == View.VISIBLE)
            binding.updateInfoProgress.setVisibility(View.GONE);
    }
}



