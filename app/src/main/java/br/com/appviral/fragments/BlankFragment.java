package br.com.appviral.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BlankFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CONTEXTO_EM_STRING = "contextoEmSTRING";
    private static final String SEQUENCAL = "sequencial";

    private String mContextoEmString;
    private int mSequencialGeral = -1;

    private int mSequencialLocal = 0;


    public BlankFragment() {
        // Required empty public constructor
        Log.d("MEUAPP","BlankFragment: " + mostra());
    }


    public static BlankFragment newInstance(String contextoEmString, int sequencial ) {
        String log = String.format("No newInstance: Sequencial: %s - Contexto: %s", sequencial, contextoEmString);
        Log.w("MEUAPP",log);
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(CONTEXTO_EM_STRING, contextoEmString);
        args.putInt(SEQUENCAL, sequencial);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO E este cara?
        // setRetainInstance(true);
        if (getArguments() != null) {
            mContextoEmString = getArguments().getString(CONTEXTO_EM_STRING);
            mSequencialGeral = getArguments().getInt(SEQUENCAL);
            Log.d("MEUAPP","onCreate: " + mostra());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MEUAPP","onCreateView: " + mostra());
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed("Botão precionado no fragments: ");
            }
        });

        TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(" Tag:" + getTag() + " Contexto em String:" + mContextoEmString);
        return view;
    }


    public void onButtonPressed(String str) {
        Context context = getActivity();
        Log.d("MEUAPP","onButtonPressed: " + mostra());
        if (context instanceof OnFragmentInteractionListener) {
            OnFragmentInteractionListener mListener = (OnFragmentInteractionListener) context;
            mListener.onFragmentInteraction(str + getTag());
        } else {
            throw new RuntimeException(context.toString()
                    + " A Activity necessita implementar OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MEUAPP","onDestroy: " + mostra());
    }

    private String mostra(){
        String contexto = null;
        if(getActivity() != null){
            contexto = getActivity().toString();
        }

        return String.format("\t" +
                "\n- Sequencial Geral: %s " +
                "\n- Sequencial Local: %s " +
                "\n- Tag: %s " +
                "\n- Contexto: %s", mSequencialGeral, mSequencialLocal++, getTag(),contexto);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String msg);
    }
}
