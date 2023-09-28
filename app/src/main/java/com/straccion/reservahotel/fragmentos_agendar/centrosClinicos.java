package com.straccion.reservahotel.fragmentos_agendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.straccion.reservahotel.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link centrosClinicos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class centrosClinicos extends Fragment {

    CircleImageView circleVolverEdit;
    View mview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public centrosClinicos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment centrosClinicos.
     */
    // TODO: Rename and change types and number of parameters
    public static centrosClinicos newInstance(String param1, String param2) {
        centrosClinicos fragment = new centrosClinicos();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_centros_clinicos, container, false);



        return mview;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CardView cardView1 = mview.findViewById(R.id.cardView1);
        final CardView cardView2 = mview.findViewById(R.id.cardView2);
        final CardView cardView3 = mview.findViewById(R.id.cardView3);
        final CardView cardView4 = mview.findViewById(R.id.cardView4);
        final LinearLayout extraInfoLayout1 = mview.findViewById(R.id.lnlInformacionAdd1);
        final LinearLayout extraInfoLayout2 = mview.findViewById(R.id.lnlInformacionAdd2);
        final LinearLayout extraInfoLayout3 = mview.findViewById(R.id.lnlInformacionAdd3);
        final LinearLayout extraInfoLayout4 = mview.findViewById(R.id.lnlInformacionAdd4);
        circleVolverEdit = mview.findViewById(R.id.circleVolverEdit);
        circleVolverEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cambiar la visibilidad de la información adicional
                if (extraInfoLayout1.getVisibility() == View.VISIBLE) {
                    extraInfoLayout1.setVisibility(View.GONE);
                } else {
                    extraInfoLayout1.setVisibility(View.VISIBLE);
                }
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extraInfoLayout2.getVisibility() == View.VISIBLE) {
                    extraInfoLayout2.setVisibility(View.GONE);
                } else {
                    extraInfoLayout2.setVisibility(View.VISIBLE);
                }
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extraInfoLayout3.getVisibility() == View.VISIBLE) {
                    extraInfoLayout3.setVisibility(View.GONE);
                } else {
                    extraInfoLayout3.setVisibility(View.VISIBLE);
                }
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extraInfoLayout4.getVisibility() == View.VISIBLE) {
                    extraInfoLayout4.setVisibility(View.GONE);
                } else {
                    extraInfoLayout4.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}