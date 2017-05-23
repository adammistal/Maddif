package adammistal.maddif.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import adammistal.maddif.R;
import adammistal.maddif.presentation.home.Home;

/**
 * Created by Adam on 22.05.2017.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root,Home.create())
                    .commit();
        }
    }
}
