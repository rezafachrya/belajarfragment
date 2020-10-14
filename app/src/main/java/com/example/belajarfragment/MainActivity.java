package com.example.belajarfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.belajarfragment.helper.DialogHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements SignupFragment.OnFragmentInteractionListener {

    private boolean isBack = false;
    private int logOutCounter = 0;
    Context mContext;
    private LinearLayout mBottomSheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomSheet = findViewById(R.id.bottom_sheet);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void addFragmentOnTop(Fragment fragment) {
        isBack = true;
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_up, R.anim.stay, R.anim.stay, R.anim.slide_down)
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    //pada saat session telah berakhir baru digunakan
    public void popFragmentOnTop() {
        getSupportFragmentManager().popBackStack();
    }

    public void popAllFragment() {
        isBack = false;
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }


    //dapat digunakan sebagai pengecek apakah intro aplikasi telah selesai dibuka
    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1);
        return currentFragment;
    }

    public void buttonNext(View view) {
        if (!isBack) {
            addFragmentOnTop(SignupFragment.newInstance("DASHBOARD ACTIVITY", "SIGNUP"));
        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getFragments().size() > 0) {

            popAllFragment();
        } else if (!isBack) {
//            back();
//            logout();
            DialogHelper.showConfirmgWithCallback(MainActivity.this, "Keluar Aplikasi", "Anda yakin akan keluar dari aplikasi?", "Keluar", "Batal", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.addCategory(Intent.CATEGORY_HOME);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
                    System.exit(0);
                }
            }).show();
        }
    }


    //Menampilkan BOTTOM Sheet dialog
    public void buttonSheet(View view) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, mBottomSheet);
        bottomSheetView.findViewById(R.id.buttonClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

//    public void logout() {
//        backToLogin();
//    }

//    private void backToLogin() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }
//    private void back() {
//        if (isBack) {
//            super.onBackPressed();
//            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
//                isBack = false;
////                profile2();
//            }
//            logOutCounter = 0;
//        } else {
//            logOutCounter++;
//            if (logOutCounter == 1) {
//                Toast.makeText(mContext, "Tekan Back Sekali lagi untuk keluar dari aplikasi", Toast.LENGTH_LONG).show();
//                System.exit(0);
//            }
//            if (logOutCounter == 2) {
//                System.exit(0);
//            }
//        }
//    }
}