package com.example.alejandrosalguero.projectoaprende;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button Entrar,crear;
    private CheckBox alumno,profe;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ImageView foto_gallery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("https://projectoaprende.firebaseio.com/Usuario/");

        View v= inflater.inflate(R.layout.fragment_registro, container, false);
        mEmailField = v.findViewById(R.id.Correo);
       mPasswordField = v.findViewById(R.id.password);
        foto_gallery = (ImageView) v.findViewById(R.id.imagen);
        // Buttons

        Entrar = v.findViewById(R.id.password);
        crear = v.findViewById(R.id.password);
        alumno = v.findViewById(R.id.checkBox2);
        profe = v.findViewById(R.id.checkBox3);
        DatabaseReference usersRef = ref.child("users");

        Map<String, Usuario> users = new HashMap<>();
        users.put("alanisawesome", new Usuario(mEmailField.toString(), mPasswordField.toString(),));
        users.put("gracehop", new Usuario("December 9, 1906", "Grace Hopper"));

        usersRef.setValueAsync(users);
        Entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity CrearCuenta = (MainActivity)getActivity();

                CrearCuenta.createAccount( mEmailField.toString(),mPasswordField.toString());
                if(alumno.isChecked()){
                    Intent intent = new Intent(getContext(), Alumno.class);

                    startActivity(intent);
                }
                if(profe.isChecked()){
                    Intent intent = new Intent(getContext(), Profesor.class);

                    startActivity(intent);
                }
            }
        });
        crear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity Registro = (MainActivity)getActivity();


                Registro.Registro();



            }
        });
        foto_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

return v;
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            foto_gallery.setImageURI(imageUri);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
