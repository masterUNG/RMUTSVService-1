package com.nuntakaset.siriluk.rmutsvservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nuntakaset.siriluk.rmutsvservice.R;
import com.nuntakaset.siriluk.rmutsvservice.utility.GetAllData;
import com.nuntakaset.siriluk.rmutsvservice.utility.MyAlert;
import com.nuntakaset.siriluk.rmutsvservice.utility.Mycomtent;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lenovo on 6/11/2560.
 */

public class MainFragment extends Fragment{

    private String userString, passwordString;
    private boolean userABoolean = true;   // true ==> User False

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();


    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                if (userString.equals("") || passwordString.equals("")) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill All Blank");
                } else {
                    chackUserAnPass();
                }

            }   // On Click
        });
    }

    private void chackUserAnPass() {
        try {

            Mycomtent mycomtent = new Mycomtent();
            String tag = "8novV1";
            GetAllData getAllData = new GetAllData(getContext());
            getAllData.execute(mycomtent.getUrlGetAllUser());
            String strJSON = getAllData.get();
            Log.d (tag, "JSON==>" + strJSON);
            ;String[] strings = new String[]{"Id", "Name", "Category", "User", "Password"};
            String[] userStrings1 = new String[strings.length];

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i = 0; i<jsonArray.length();i++) {


                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    userABoolean = false;

                    for (int i1=0; i1<strings.length; i1+=1) {
                        userStrings1[i1] = jsonObject.getString(strings[i1]);
                    }
                }
            } // for

            if (userABoolean) {
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("User Fales", "No this User in My Database");
            } else if (passwordString.equals(userStrings1[4])) {

                Toast.makeText(getActivity(), "Welcome" + userStrings1[1],
                        Toast.LENGTH_SHORT).show();
            } else {
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("Password False", "Please Try Again Password False");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain,new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }   // On click
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        return view;
    }
} // Main Class
