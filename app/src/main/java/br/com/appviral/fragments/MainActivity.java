package br.com.appviral.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements BlankFragment.OnFragmentInteractionListener, View.OnClickListener {

    private Button mB1, mB2, mB3, mB4;
    private int sequencial = 0;
    private BlankFragment mBlankFragment = null;
    private String mTagFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mB1 = (Button) findViewById(R.id.b1);
        mB2 = (Button) findViewById(R.id.b2);
        mB3 = (Button) findViewById(R.id.b3);
        mB4 = (Button) findViewById(R.id.b4);

        mB1.setOnClickListener(this);
        mB2.setOnClickListener(this);
        mB3.setOnClickListener(this);
        mB4.setOnClickListener(this);

        modoAparelho();

        if (savedInstanceState != null) {
            mTagFragment = savedInstanceState.getString("TAG");

            if (mTagFragment == null)
                abreFragments("B1");
            else
                abreFragments(mTagFragment);
        } else{
            abreFragments("B1");
        }
    }

    private void abreFragments(String tag) {
        // FRAGMENT
        Log.d("MEUAPP", "Está em abreFragments(): " + tag);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (mBlankFragment != null)
            ft.hide(mBlankFragment);
        BlankFragment frag = (BlankFragment) fragmentManager.findFragmentByTag(tag);
        if (frag == null) {
            frag = frag.newInstance(this.toString(), sequencial++);
//            ft.replace(R.id.rl_fragment_container, frag, tag);
            ft.add(R.id.rl_fragment_container, frag, tag);
        } else {
            ft.show(frag);
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(tag);
        ft.commit();
        mTagFragment = tag;
        mBlankFragment = frag;
    }


    @Override
    public void onFragmentInteraction(String msg) {
        Log.d("MEUAPP", "Fragment clicado foi: " + msg);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.b1:
                abreFragments("B1");
                break;
            case R.id.b2:
                abreFragments("B2");
                break;
            case R.id.b3:
                abreFragments("B3");
                break;
            case R.id.b4:
                abreFragments("B4");
                break;
        }
    }

    public void modoAparelho() {

        int orientacao = getResources().getConfiguration().orientation;

        switch (orientacao) {
            case Configuration.ORIENTATION_LANDSCAPE:
                Log.d("MEUAPP", "ORIENTATION_LANDSCAPE");
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Log.d("MEUAPP", "ORIENTATION_PORTRAIT");
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                Log.d("MEUAPP", "ORIENTATION_UNDEFINED");
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MEUAPP", "Passou pelo onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TAG", mTagFragment);
        Log.d("MEUAPP", "Passou pelo onSaveInstanceState(): " + mTagFragment);
    }

}
