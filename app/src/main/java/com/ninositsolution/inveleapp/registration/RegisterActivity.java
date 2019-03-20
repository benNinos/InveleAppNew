package com.ninositsolution.inveleapp.registration;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.ninositsolution.inveleapp.R;
import com.ninositsolution.inveleapp.databinding.ActivityRegisterBinding;
import com.ninositsolution.inveleapp.home.HomeActivity;
import com.ninositsolution.inveleapp.login.LoginActivity;
import com.ninositsolution.inveleapp.utils.Constants;
import com.ninositsolution.inveleapp.utils.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    RegisterVM registerVMGlobal;
    private static final String TAG = "RegisterActivity";

    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth mAuth;

    Context context;

    private static final int RC_SIGN_IN = 234;
    private FirebaseAuth auth;

    CallbackManager callbackManager;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        context = RegisterActivity.this;

        initGoogle();

        initFacebook();


        registerVMGlobal = ViewModelProviders.of(this).get(RegisterVM.class);


        binding.setRegister(registerVMGlobal);

        binding.setLifecycleOwner(this);


        binding.setIRegister(new IRegister() {
            @Override
            public void onEmailClicked() {
                swapRegistrationVisibility();
            }

            @Override
            public void onMobileClicked() {
                swapRegistrationVisibility();
            }

            @Override
            public void onUserClicked() {
                startActivity(new Intent(context, LoginActivity.class));
                finish();
            }

            @Override
            public void onEmailContinueClicked() {
                Session.setIsEmailRegistered(true, context);

                int status = registerVMGlobal.emailValidation();

                if (status == Constants.EMAIL_EMPTY)
                {
                    binding.emailEdit.setError("required");
                    binding.emailEdit.requestFocus();

                } else if (status == Constants.NO_EMAIL_PATTERN)
                {
                    binding.emailEdit.setError("not a valid Email address");
                    binding.emailEdit.requestFocus();

                } else if (status == Constants.EMAIL_NAME_EMPTY)
                {
                    binding.emailNameEdit.setError("required");
                    binding.emailNameEdit.requestFocus();

                } else if (status == Constants.PASSWORD_EMPTY)
                {
                    binding.passwordRegister.setError("required");
                    binding.passwordRegister.requestFocus();

                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();

                    registerVMGlobal.registerViaEmail(Session.getDevice_id(context));

                   registerVMGlobal.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                       @Override
                       public void onChanged(@Nullable RegisterVM registerVM) {

                           hideProgressBar();

                           if (!registerVM.status.get().isEmpty())
                           {

                               if (registerVM.status.get().equalsIgnoreCase("success"))
                               {
                                   Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                   Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                                   getOtpLayout();
                                   registerVMGlobal.enter_otp.set(String.valueOf(registerVM.otp.get()));

                               } else {
                                   Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();

                                   if (registerVM.msg.get().equalsIgnoreCase("Email Already Registered"))

                                   {
                                       showAlert();
                                   }
                               }
                           } else
                           {
                               Toast.makeText(RegisterActivity.this, "Api Error", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

                } else
                {

                    Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onUser2Clicked() {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onMobileContinueClicked() {

                Session.setIsEmailRegistered(false, RegisterActivity.this);

                int status = registerVMGlobal.mobileValidation();

                if (status == Constants.MOBILE_NO_EMPTY)
                {
                    binding.mobileNoEdit.setError("required");
                    binding.mobileNoEdit.requestFocus();
                } else if (status == Constants.MOBILE_NAME_EMPTY)
                {
                    binding.mobileNameEdit.setError("required");
                    binding.mobileNameEdit.requestFocus();
                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    registerVMGlobal.registerViaMobile(Session.getDevice_id(RegisterActivity.this));

                    registerVMGlobal.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                        @Override
                        public void onChanged(@Nullable RegisterVM registerVM) {

                            if (!registerVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (registerVM.status.get().equalsIgnoreCase("success"))
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                                    Log.i(TAG, "user_id : "+registerVM.user.get().id);
                                    getOtpLayout();
                                    registerVMGlobal.enter_otp.set(String.valueOf(registerVM.otp.get()));

                                } else
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }

                //getOtpLayout();
            }

            @Override
            public void onResendClicked() {

                if (Session.getIsEmailRegistered(RegisterActivity.this))
                {
                        showProgressBar();

                        registerVMGlobal.registerViaEmail(Session.getDevice_id(RegisterActivity.this));

                        registerVMGlobal.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                            @Override
                            public void onChanged(@Nullable RegisterVM registerVM) {

                                hideProgressBar();

                                if (!registerVM.status.get().isEmpty())
                                {

                                    if (registerVM.status.get().equalsIgnoreCase("success"))
                                    {
                                        Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                        Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                                        getOtpLayout();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    }
                                } else
                                {
                                    Toast.makeText(RegisterActivity.this, "Api Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                } else
                {
                    showProgressBar();
                    registerVMGlobal.registerViaMobile(Session.getDevice_id(RegisterActivity.this));

                    registerVMGlobal.getRegisterVMMutableLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                        @Override
                        public void onChanged(@Nullable RegisterVM registerVM) {

                            if (!registerVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (registerVM.status.get().equalsIgnoreCase("success"))
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                                    Log.i(TAG, "user_id : "+registerVM.user.get().id);
                                    getOtpLayout();
                                    registerVMGlobal.enter_otp.set(String.valueOf(registerVM.otp.get()));

                                } else
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                    binding.resend.setEnabled(false);
                    countDownTimer.start();
                    binding.resend.setTextColor(getResources().getColor(R.color.star_grey));
            }

            @Override
            public void onOtpContinueClicked() {
                //getBackRegistrationVisibility();

                int status = registerVMGlobal.otpValidations();

                if (status == Constants.OTP_EMPTY)
                {
                    binding.otp.setError("required");

                } else if (status == Constants.INVALID_OTP_LENGTH)
                {
                    binding.otp.setError("Please Enter 6 digit otp");
                } else if (status == Constants.SUCCESS)
                {
                    showProgressBar();
                    registerVMGlobal.otpVerifyApi(Session.getUserId(RegisterActivity.this));

                    registerVMGlobal.getOtpVerifyLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                        @Override
                        public void onChanged(@Nullable RegisterVM registerVM) {

                            if (!registerVM.status.get().isEmpty())
                            {
                                hideProgressBar();

                                if (registerVM.status.get().equalsIgnoreCase("success"))
                                {
                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    // next process
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                   // getBackRegistrationVisibility();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    registerVM.status.set("");

                                } else {

                                    Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                    // next process
                                    registerVM.status.set("");

                                }
                            }
                        }
                    });



                } else
                {
                    Toast.makeText(RegisterActivity.this, "Invalidation Errors", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFacebookClicked() {

                showProgressBar();

                binding.loginButton.performClick();

                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        handlefacebooktoken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        hideProgressBar();
                        Toast.makeText(RegisterActivity.this, "cancelled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        hideProgressBar();
                        Toast.makeText(RegisterActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onGoogleClicked() {

                showProgressBar();

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();

                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
    }

    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

        builder.setTitle(R.string.already_registered)
                .setMessage(R.string.proceed_with_login)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void handlefacebooktoken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            updateUI(firebaseUser);
                        }
                        else
                        {
                            hideProgressBar();
                            Toast.makeText(RegisterActivity.this, "Could not register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser) {

        if (firebaseUser.getDisplayName() != null)
        {
            Log.i(TAG, "fb_name : "+firebaseUser.getDisplayName());
            Session.setUsername(firebaseUser.getDisplayName(), RegisterActivity.this);
        }

        if (firebaseUser.getPhoneNumber() != null)
        {
            Log.i(TAG, "fb_phone : "+firebaseUser.getPhoneNumber());
            Session.setUserPhone(firebaseUser.getPhoneNumber(), RegisterActivity.this);
        }

        if (firebaseUser.getEmail() != null)
        {
            Log.i(TAG, "fb_email : "+firebaseUser.getEmail());
            Session.setUserEmail(firebaseUser.getEmail(), RegisterActivity.this);
        }

        if (firebaseUser.getUid() != null)
        {
            Log.i(TAG, "fb_uid : "+firebaseUser.getUid());
            Session.setUserUid(firebaseUser.getUid(), RegisterActivity.this);
        }

        if (firebaseUser.getPhotoUrl() != null)
        {
            Log.i(TAG, "fb_photo : "+firebaseUser.getPhotoUrl());
            Session.setUserPhoto(firebaseUser.getPhotoUrl().toString(), RegisterActivity.this);
        }

        registerVMGlobal.fbLoginApi(
                Session.getUserName(RegisterActivity.this),
                Session.getUserPhone(RegisterActivity.this),
                Session.getUserEmail(RegisterActivity.this),
                Session.getUserUid(RegisterActivity.this),
                Session.getDevice_id(RegisterActivity.this)
        );

        registerVMGlobal.getFbLoginLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
            @Override
            public void onChanged(@Nullable RegisterVM registerVM) {

                hideProgressBar();

                if (!registerVM.status.get().isEmpty())
                {
                    if (registerVM.status.get().equalsIgnoreCase("success"))
                    {
                        Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                        Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                        registerVM.status.set("");
                        startActivity(new Intent(context, HomeActivity.class));
                    } else
                    {
                        Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                    }
                }

                else
                {
                    Toast.makeText(RegisterActivity.this, "Api Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initFacebook() {

        auth = FirebaseAuth.getInstance();

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setReadPermissions(Arrays.asList("email"));

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.ninositsolution.inveleapp", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest digest = MessageDigest.getInstance("SHA");
                digest.update(signature.toByteArray());
                String key = Base64.encodeToString(digest.digest(), Base64.DEFAULT);
                Log.i("Keyhash",key);
                Toast.makeText(this, ""+key, Toast.LENGTH_SHORT).show();
                //email.setText(key);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                hideProgressBar();
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else
        {
            hideProgressBar();
            //Toast.makeText(this, "User permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();

                            Log.i(TAG, "user_name : "+user.getDisplayName());
                            Log.i(TAG, "user_email : "+user.getEmail());
                            Log.i(TAG, "user_phone : "+user.getPhoneNumber());
                            Log.i(TAG, "user_uid : "+user.getUid());
                            Log.i(TAG, "user_photo : "+user.getPhotoUrl());

                            if (user.getDisplayName() != null)
                            {
                                Session.setUsername(user.getDisplayName(), RegisterActivity.this);
                            }

                            if (user.getEmail() != null)
                            {
                                Session.setUserEmail(user.getEmail(), RegisterActivity.this);
                            }

                            if (user.getPhoneNumber() != null)
                            {
                                Session.setUserPhone(user.getPhoneNumber(), RegisterActivity.this);
                            }

                            if (user.getUid() != null)
                            {
                                Session.setUserUid(user.getUid(), RegisterActivity.this);
                            }

                            if (user.getPhotoUrl() != null)
                            {
                                Session.setUserPhoto(user.getPhotoUrl().toString(), RegisterActivity.this);
                            }

                            registerVMGlobal.googleLoginApi(
                                    Session.getUserName(RegisterActivity.this),
                                    Session.getUserPhone(RegisterActivity.this),
                                    Session.getUserEmail(RegisterActivity.this),
                                    Session.getUserUid(RegisterActivity.this),
                                    Session.getDevice_id(RegisterActivity.this)
                            );

                            registerVMGlobal.getGoogleLoginLiveData().observe(RegisterActivity.this, new Observer<RegisterVM>() {
                                @Override
                                public void onChanged(@Nullable RegisterVM registerVM) {

                                    hideProgressBar();

                                    if (!registerVM.status.get().isEmpty())
                                    {
                                        if (registerVM.status.get().equalsIgnoreCase("success"))
                                        {

                                            Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                            registerVM.status.set("");
                                            Session.setUserId(String.valueOf(registerVM.user.get().id), RegisterActivity.this);
                                            startActivity(new Intent(context, HomeActivity.class));
                                        } else
                                        {
                                            Toast.makeText(RegisterActivity.this, ""+registerVM.msg.get(), Toast.LENGTH_SHORT).show();
                                            registerVM.status.set("");
                                        }
                                    } else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Api Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {

                            hideProgressBar();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initGoogle() {

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private void getBackRegistrationVisibility() {

        if (binding.otpLayout.getVisibility() == View.VISIBLE)
        {
            binding.otpLayout.setVisibility(View.GONE);
            binding.registerLayout.setVisibility(View.VISIBLE);


            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.cardRegister.getLayoutParams();
            layoutParams.setMargins(36, 250, 36, 0);
            binding.cardRegister.requestLayout();
        }
    }

    private void getOtpLayout() {

        countDownTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    binding.timer.setText(millisUntilFinished/1000 + " sec");
            }

            @Override
            public void onFinish() {

                binding.resend.setEnabled(true);
                binding.resend.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        }.start();

        if (binding.registerLayout.getVisibility() == View.VISIBLE)
        {
            binding.registerLayout.setVisibility(View.GONE);
            binding.otpLayout.setVisibility(View.VISIBLE);

            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.cardRegister.getLayoutParams();
            layoutParams.setMargins(36, 475, 36, 0);
            binding.cardRegister.requestLayout();
        }

    }

    private void swapRegistrationVisibility() {

        if (binding.emailRegister.getVisibility() == View.VISIBLE)
        {
            binding.emailHead.setTextColor(getResources().getColor(R.color.text_color));
            binding.mobileHead.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.emailRegister.setVisibility(View.GONE);
            binding.viewEmail.setVisibility(View.INVISIBLE);
            binding.mobileRegister.setVisibility(View.VISIBLE);
            binding.viewMobile.setVisibility(View.VISIBLE);
        } else
        {
            binding.mobileHead.setTextColor(getResources().getColor(R.color.text_color));
            binding.emailHead.setTextColor(getResources().getColor(R.color.colorPrimary));
            binding.mobileRegister.setVisibility(View.GONE);
            binding.viewMobile.setVisibility(View.INVISIBLE);
            binding.emailRegister.setVisibility(View.VISIBLE);
            binding.viewEmail.setVisibility(View.VISIBLE);
        }
    }

    private void showProgressBar()
    {
        if (binding.registerProgress.getVisibility() == View.GONE)
            binding.registerProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if (binding.registerProgress.getVisibility() == View.VISIBLE)
            binding.registerProgress.setVisibility(View.GONE);
    }

   /*
    @Override
    public void updateTimer(String time) {
        binding.timer.setText(time);

        if (time.equalsIgnoreCase("try again"))
            binding.resend.setEnabled(true);
    }

    }*/
}
