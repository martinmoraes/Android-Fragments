package br.com.appviral.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CONTEXTO_EM_STRING = "contextoEmSTRING";
    private static final String SEQUENCAL = "sequencial";

    private String mContextoEmString;
    private int mSequencial;

    private int mSequencialLocal = 0;

    private OnFragmentInteractionListener mListener = null;




    public BlankFragment() {
        // Required empty public constructor
        Log.d("MEUAPP","BlankFragment: " + mostra());
    }


    public static BlankFragment newInstance(String contextoEmString, int sequencial ) {
        String log = String.format("No newInstance: Sequencial: %s - Contexto: %s", sequencial, contextoEmString);
        Log.d("MEUAPP",log);
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
        if (getArguments() != null) {
            mContextoEmString = getArguments().getString(CONTEXTO_EM_STRING);
            mSequencial = getArguments().getInt(SEQUENCAL);
            Log.d("MEUAPP","onCreate: " + mostra());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        Log.d("MEUAPP","onButtonPressed: " + mostra());
        if (mListener != null) {
            mListener.onFragmentInteraction(str + getTag());
        } else {
            Log.d("MEUAPP", "mListener não definido.");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("MEUAPP","onAttach: " + mostra() + " Contexto recebido: "+context.toString());
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d("MEUAPP","onDetach: " + mostra());
        super.onDetach();
        mListener = null;
    }



    private String mostra(){
        String contexto = null;
        if(getActivity() != null){
            contexto = getActivity().toString();
        }

        return String.format("\t" +
                "\n- Sequencial Local: %s " +
                "\n- Sequencial Geral: %s " +
                "\n- Tag: %s " +
                "\n- Contexto: %s", mSequencialLocal++, mSequencial, getTag(),contexto);
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String msg);
    }
}
