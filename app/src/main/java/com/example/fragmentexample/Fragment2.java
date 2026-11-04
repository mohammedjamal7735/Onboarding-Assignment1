package com.example.fragmentexample;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentexample.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    public interface OnFragment2InteractionListener {
        void onFragment2Continue(String name, String age, String email, String university, String gender);
    }

    private Fragment2Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initial user details supplied for this assignment.
        binding.editTextName.setText("mohammed jamal abu zaid");
        binding.editTextAge.setText("23");
        binding.editTextEmail.setText("mhmd7735.as.az@gmail.com");
        binding.editTextUniversity.setText("alaqsa university");
        binding.rbMale.setChecked(true);
    }

    public void continueToNextScreen() {
        if (binding == null) return;

        String name = binding.editTextName.getText().toString().trim();
        String age = binding.editTextAge.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String university = binding.editTextUniversity.getText().toString().trim();
        String gender = binding.radioGroupGender.getCheckedRadioButtonId() == binding.rbMale.getId()
                ? "Male"
                : binding.radioGroupGender.getCheckedRadioButtonId() == binding.rbFemale.getId()
                    ? "Female" : "";

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(university) || TextUtils.isEmpty(gender)) {
            Toast.makeText(requireContext(), "Please complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (getActivity() instanceof OnFragment2InteractionListener) {
            ((OnFragment2InteractionListener) getActivity())
                    .onFragment2Continue(name, age, email, university, gender);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
