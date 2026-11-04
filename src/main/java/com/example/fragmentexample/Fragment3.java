package com.example.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentexample.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    public interface OnFragmentInteractionListener {
        void onCheckboxChanged(boolean isChecked);
        void onFinishClicked();
    }

    private Fragment3Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            String userName = args.getString("userName", "User");
            binding.tvWelcomeName.setText("Welcome, " + userName + "!");
            binding.tvUserDetails.setText(
                    "Age: " + args.getString("age", "") + "\n" +
                    "Email: " + args.getString("email", "") + "\n" +
                    "University: " + args.getString("university", "") + "\n" +
                    "Gender: " + args.getString("gender", ""));
        }

        binding.buttonFinish.setEnabled(false);
        binding.buttonFinish.setText("Continue");
        binding.checkboxAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (getActivity() instanceof OnFragmentInteractionListener) {
                ((OnFragmentInteractionListener) getActivity()).onCheckboxChanged(isChecked);
            }
        });
        binding.buttonFinish.setOnClickListener(v -> {
            if (getActivity() instanceof OnFragmentInteractionListener) {
                ((OnFragmentInteractionListener) getActivity()).onFinishClicked();
            }
        });
    }

    public void updateContinueButton(boolean isChecked) {
        if (binding != null) {
            binding.buttonFinish.setEnabled(isChecked);
            binding.buttonFinish.setText(isChecked ? "Finish" : "Continue");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
