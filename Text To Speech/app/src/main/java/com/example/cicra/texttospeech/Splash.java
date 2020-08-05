package com.example.cicra.texttospeech;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * Created by Sarasi Sumiyuru on 3/15/2020.
 */

    public class Splash extends AwesomeSplash {
        @Override
        public void initSplash(ConfigSplash configSplash) {

            //Customize Circular Reveal
            configSplash.setBackgroundColor(R.color.colorPrimaryDark); //any color you want form colors.xml
            configSplash.setAnimCircularRevealDuration(2000); //int ms
            configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
            configSplash.setRevealFlagY(Flags.REVEAL_LEFT);


            //Customize Logo
            configSplash.setLogoSplash(R.drawable.mic); //or any other drawable
            configSplash.setAnimLogoSplashDuration(2000); //int ms
            //Customize Title
            configSplash.setTitleSplash("Text To Speech");
            // configSplash.setTitleTextColor(R.color.Wheat);
            configSplash.setTitleTextSize(20f); //float value
            configSplash.setAnimTitleDuration(3000);
            configSplash.setAnimTitleTechnique(Techniques.FlipInX);

        }

        @Override
        public void animationsFinished() {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }


