package com.example.fragmentexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fragmentexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements
        Fragment2.OnFragment2InteractionListener,
        Fragment3.OnFragmentInteractionListener {

    private ActivityMainBinding binding;
    private int currentFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Fragment1())
                    .commit();
        }

        binding.buttonContinue.setOnClickListener(v -> {
            if (currentFragment == 1) {
                navigateToFragment(new Fragment2());
                currentFragment = 2;
            } else if (currentFragment == 2) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (fragment instanceof Fragment2) {
                    ((Fragment2) fragment).continueToNextScreen();
                }
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragment2Continue(String name, String age, String email, String university, String gender) {
        Bundle bundle = new Bundle();
        bundle.putString("userName", name);
        bundle.putString("age", age);
        bundle.putString("email", email);
        bundle.putString("university", university);
        bundle.putString("gender", gender);

        Fragment3 fragment3 = new Fragment3();
        fragment3.setArguments(bundle);
        binding.buttonContinue.setVisibility(android.view.View.GONE);
        navigateToFragment(fragment3);
        currentFragment = 3;
    }

    @Override
    public void onCheckboxChanged(boolean isChecked) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof Fragment3) {
            ((Fragment3) fragment).updateContinueButton(isChecked);
        }
    }

    @Override
    public void onFinishClicked() {
        Toast.makeText(this, "Application Finished Successfully!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (currentFragment == 3) {
            currentFragment = 2;
            binding.buttonContinue.setVisibility(android.view.View.VISIBLE);
        } else if (currentFragment == 2) {
            currentFragment = 1;
        }
    }
}
