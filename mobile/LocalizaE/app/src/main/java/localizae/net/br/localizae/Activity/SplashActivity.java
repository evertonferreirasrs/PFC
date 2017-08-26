package localizae.net.br.localizae.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import localizae.net.br.localizae.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView iv_logo = (ImageView) findViewById(R.id.logo_id);
        final Animation an_logo = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2_logo = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv_logo.startAnimation(an_logo);
        an_logo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_logo.startAnimation(an2_logo);
                finish();
                Intent i = new Intent(getBaseContext(),MenuActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
