package com.fahedhermoza.developer.owaspapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SplashScreenActivityFragment extends Fragment {
    private View viewRoot;
    private ImageView imageViewLogo;
    public SplashScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        imageViewLogo = (ImageView)viewRoot.findViewById(R.id.imageViewLogo);
        animateWidgets();
        frozenActivity frozenActivity = new frozenActivity();
        frozenActivity.execute();
        return viewRoot;
    }

    private void animateWidgets() {
        Animation bounceAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        imageViewLogo.startAnimation(bounceAnimation);
    }


    public void navigationToLogin() {
        Intent intent = new Intent(viewRoot.getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public class frozenActivity extends AsyncTask<String, Integer, Integer> {

        public frozenActivity() {
            super();
        }

        @Override
        protected Integer doInBackground(String... params) {
            try{
                Thread.sleep(1500);
            }catch (Exception e){
                Log.e("Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            navigationToLogin();
        }
    }
}
