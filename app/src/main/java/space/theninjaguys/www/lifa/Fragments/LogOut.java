package space.theninjaguys.www.lifa.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import space.theninjaguys.www.lifa.Helper.UserSession;

public class LogOut extends Fragment {

    UserSession mSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSession = new UserSession(getActivity());
        mSession.logoutUser();
        if (getActivity() != null) {

            getActivity().finish();
        }

    }

}
