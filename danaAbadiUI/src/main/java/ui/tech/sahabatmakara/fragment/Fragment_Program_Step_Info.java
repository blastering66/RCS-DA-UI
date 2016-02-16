package ui.tech.sahabatmakara.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import ui.tech.sahabatmakara.activity.R;
import ui.tech.sahabatmakara.dialog.Dialog_Progress;
import ui.tech.sahabatmakara.entities.RowData_Program;
import ui.tech.sahabatmakara.helper.NavigationPaymentCallback;
import ui.tech.sahabatmakara.util.Font;
import ui.tech.sahabatmakara.util.ParameterCollections;
import ui.tech.sahabatmakara.util.ServiceHandlerJSON;

/**
 * Created by RebelCreative-A1 on 20/10/2015.
 */
public class Fragment_Program_Step_Info extends Fragment {
    EditText ed_NamaDonatur, ed_Email, ed_NamaProgram, ed_JmhDonasi;
    Spinner spinner_Fakultas, spinner_Payment, spinner_Donasi;
    View wrapper_jmh_donasi;
    SharedPreferences sp;
    private String cNamaLengkap, cEmail, cNamaProgram, cNamaFakultas, cJumlahDonasi;
    private int cViaPayment;
    private String cProgramId;
    private ArrayList<RowData_Program> objects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences(ParameterCollections.SH_NAME, Context.MODE_PRIVATE);
        cProgramId = sp.getString(ParameterCollections.SH_PROGRAM_ID, "1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.fragment_program_step_info, null);

        TextView label_nama_lengkap = (TextView) v.findViewById(R.id.label_nama_lengkap);
        TextView label_email = (TextView) v.findViewById(R.id.label_email);
        TextView label_nama_program = (TextView) v.findViewById(R.id.label_nama_program);
        TextView label_informasi = (TextView) v.findViewById(R.id.label_informasi);
        TextView label_nama_fakultas = (TextView) v.findViewById(R.id.label_nama_fakultas);
        TextView label_metode = (TextView) v.findViewById(R.id.label_metode);

        TextView label_jmh_donasi = (TextView) v.findViewById(R.id.label_jmh_donasi);
        TextView label_tlp = (TextView) v.findViewById(R.id.label_tlp);

        ed_NamaDonatur = (EditText) v.findViewById(R.id.ed_donatur_nama);
        ed_Email = (EditText) v.findViewById(R.id.ed_donatur_email);

        ed_NamaProgram = (EditText) v.findViewById(R.id.ed_donatur_nama_program);

        spinner_Fakultas = (Spinner) v.findViewById(R.id.spinner_fakultas);
        spinner_Payment = (Spinner) v.findViewById(R.id.spinner_payment);
        spinner_Donasi = (Spinner) v.findViewById(R.id.spinner_donasi);

        ed_JmhDonasi = (EditText) v.findViewById(R.id.ed_jmhdonasi_other);
        ed_JmhDonasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nominal = s.toString();
                int int_nominal = Integer.valueOf(nominal);

                if (int_nominal > 25000000) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.payment_via_all));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Payment.setAdapter(adapter);

                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.payment_via));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_Payment.setAdapter(adapter);

                }

            }
        });

        wrapper_jmh_donasi = (View) v.findViewById(R.id.wrapper_jmhdonasi_other);

        ed_NamaProgram.setText(sp.getString(ParameterCollections.SH_NAMA_PROGRAM, ""));

        Button btn_Next = (Button) v.findViewById(R.id.btn_next);

        label_nama_lengkap.setTypeface(Font.setLato(getActivity()));
        label_email.setTypeface(Font.setLato(getActivity()));
        label_nama_program.setTypeface(Font.setLato(getActivity()));
        label_informasi.setTypeface(Font.setLato(getActivity()));
        label_nama_fakultas.setTypeface(Font.setLato(getActivity()));
        label_metode.setTypeface(Font.setLato(getActivity()));
        label_jmh_donasi.setTypeface(Font.setLato(getActivity()));
        label_tlp.setTypeface(Font.setLato(getActivity()));

        ed_Email.setTypeface(Font.setLato(getActivity()));
        ed_JmhDonasi.setTypeface(Font.setLato(getActivity()));
        ed_NamaDonatur.setTypeface(Font.setLato(getActivity()));
        ed_NamaProgram.setTypeface(Font.setLato(getActivity()));


        btn_Next.setTypeface(Font.setLato(getActivity()));

        //set text bila sudah Login
        if(sp.getBoolean(ParameterCollections.SH_LOGGED, false)){
            ed_NamaDonatur.setText(sp.getString(ParameterCollections.SH_NAMA_DONATUR,""));
            ed_Email.setText(sp.getString(ParameterCollections.SH_EMAIL_DONATUR,""));


        }

        spinner_Fakultas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cNamaFakultas = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cNamaFakultas = "";
            }
        });

        spinner_Payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cViaPayment = i;
//                if(cViaPayment == 0){
//                    wrapper_no_tlp.setVisibility(View.VISIBLE);
//                }else{
//                    wrapper_no_tlp.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cViaPayment = 999;
            }
        });

        spinner_Donasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        cJumlahDonasi = "500000";
                        break;
                    case 1:
                        cJumlahDonasi = "1000000";
                        break;
                    case 2:
                        cJumlahDonasi = "2000000";
                        break;
                    case 3:
                        cJumlahDonasi = "5000000";
                        break;
                    case 4:
                        cJumlahDonasi = "10000000";
                        break;
                    case 5:
                        wrapper_jmh_donasi.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cJumlahDonasi = "";
            }
        });


        btn_Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!ed_NamaDonatur.getText().toString().isEmpty()) {
                    new AsyncTask_Checkout().execute();
                } else {
                    Toast.makeText(getActivity(), "Harap isi Nama Donatur dan Nominal Donasi Anda", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        return v;
    }

    private class AsyncTask_Checkout extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            cek_information();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(wrapper_jmh_donasi.getVisibility() == View.VISIBLE){
                cJumlahDonasi = ed_JmhDonasi.getText().toString();
            }
            switch (cViaPayment) {
                case 0:
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,
                            new Fragment_Program_Step_Bayar_Transfer().create(cNamaLengkap, cJumlahDonasi,
                                    cNamaProgram, cNamaFakultas, cEmail)).addToBackStack("").commit();

                    break;
                case 1:
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,
                            new Fragment_Program_Step_Bayar_KartuKredit().create(cNamaLengkap, cJumlahDonasi))
                            .addToBackStack("").commit();
                    break;

                case 2:
                    getFragmentManager().beginTransaction().replace(R.id.frame_container,
                            new Fragment_JemputDana_Step2().create(cNamaLengkap, cEmail,
                                    cNamaProgram, cNamaFakultas, cJumlahDonasi, cProgramId))
                            .addToBackStack("").commit();
                    break;

            }


        }

        private void cek_information() {
            cNamaLengkap = ed_NamaDonatur.getText().toString();
            cEmail = ed_Email.getText().toString();
            cNamaProgram = ed_NamaProgram.getText().toString();
            sp.edit().putString(ParameterCollections.SH_NAMA_DONATUR, cNamaLengkap).commit();
            sp.edit().putString(ParameterCollections.SH_NAMA_PROGRAM, cNamaProgram).commit();
            sp.edit().putString(ParameterCollections.SH_JUMLAH_DONASI, cJumlahDonasi).commit();
            sp.edit().putString(ParameterCollections.SH_NAMA_FAKULTAS, cNamaFakultas).commit();

            //value published untuk dtampilkan sbg donatur atau tidak
            sp.edit().putString(ParameterCollections.SH_PUBLISHED, "Yes").commit();

            sp.edit().putString(ParameterCollections.SH_EMAIL_DONATUR, cEmail).commit();


        }
    }



}