package localizae.net.br.controller.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import java.util.Calendar;
import java.util.List;

import localizae.net.br.controller.R;
import localizae.net.br.helper.RetrofitInicializador;
import localizae.net.br.model.Estande;
import localizae.net.br.model.File;
import localizae.net.br.model.Promocao;
import localizae.net.br.utils.ControladorDadosUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormPromocoesFragment extends Fragment {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 250;
    private Button botao_voltar;
    private Button botao_cadastrar;
    private EditText data_editText;
    private EditText hora_editText;
    private EditText nome_editText;
    private EditText descricao_editText;
    private Estande estande;
    private RetrofitInicializador retrofit;
    private ProgressDialog dialog;
    private Button alterarButton;
    private String caminhoFoto;
    private Promocao promocao;
    private String imageBase64;
    private File fileToUpload;

    public FormPromocoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_form_promocoes, container, false);
        getActivity().setTitle(" Promoção");

        dialog = new ProgressDialog(getContext());

        retrofit = new RetrofitInicializador();
        Call<List<Estande>> readEstandeCall = retrofit.getEstandeService().getByUser(ControladorDadosUsuario.lerDados(getContext()).getId());
        dialog.setMessage("Carregando...");
        dialog.show();

        readEstandeCall.enqueue(new Callback<List<Estande>>() {
            @Override
            public void onResponse(Call<List<Estande>> call, Response<List<Estande>> response) {
                dialog.cancel();
                dialog.dismiss();

                List<Estande> estandeList = response.body();
                if(estandeList == null || estandeList.size() == 0){
                    Toast.makeText(getContext(), R.string.error_read_estande, Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }

                Log.i("IP", call.request().url().toString());

                estande = estandeList.get(0);
            }

            @Override
            public void onFailure(Call<List<Estande>> call, Throwable t) {
                dialog.cancel();
                dialog.dismiss();

                Toast.makeText(getContext(), R.string.error_read_estande, Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        botao_voltar = (Button) view.findViewById(R.id.botao_voltar_id);
        botao_cadastrar = (Button) view.findViewById(R.id.botao_cadastar_id);
        data_editText = (EditText) view.findViewById(R.id.data_id);
        hora_editText = (EditText) view.findViewById(R.id.hora_id);
        nome_editText = (EditText) view.findViewById(R.id.nome_promocao_id);
        descricao_editText = (EditText) view.findViewById(R.id.descricao_id);
        alterarButton = (Button) view.findViewById(R.id.alterar_id);

        data_editText.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    data_editText.setText(current);
                    data_editText.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        alterarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        botao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Promocao promocao = new Promocao();

            promocao.setNome(nome_editText.getText().toString());
            promocao.setDescricao(descricao_editText.getText().toString());
            promocao.setEstande(estande);

            String dataDigitada = data_editText.getText().toString();
            String dataSplit[] = dataDigitada.split("/");

            String horaDigitada = hora_editText.getText().toString();
            String horaSplit[] = horaDigitada.split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataSplit[0]));
            calendar.set(Calendar.MONTH, (Integer.parseInt(dataSplit[1])-1));
            calendar.set(Calendar.YEAR, Integer.parseInt(dataSplit[2]));
            calendar.set(Calendar.HOUR, Integer.parseInt(horaSplit[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(horaSplit[1]));

            promocao.setDataHora(calendar.getTimeInMillis());

                if(imageBase64 != null && !imageBase64.isEmpty()){

                    try {
                        localizae.net.br.model.File file = new localizae.net.br.model.File();
                        file.setUri(String.valueOf(System.currentTimeMillis())+".jpg");
                        file.setBase64(imageBase64);
                        promocao.setImagem(file);
                        file.setFile(fileToUpload.getFile());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("ERRO UPLOAD FILE", e.getMessage());
                    }


                }

                Call<Void> promocaoCall = retrofit.getPromocaoService().create(promocao);
                dialog.show();

                promocaoCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dialog.cancel();
                        dialog.dismiss();
                        Toast.makeText(getContext(), R.string.success_create_promocao, Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.cancel();
                        dialog.dismiss();
                        Toast.makeText(getContext(), R.string.error_create_promocao, Toast.LENGTH_SHORT).show();

                        if(t != null){
                            Log.i("ERROR UPLOAD PROMOTION", t.toString()+"lba");
                            Log.i("ERROR UPLOAD PROMOTION", t.getMessage()+"lba");
                            Log.i("ERROR UPLOAD PROMOTION", t.getLocalizedMessage()+"lba");
                        }
                    }
                });
            }
        });

        botao_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                ImageView campoFoto = getView().findViewById(R.id.imageViewPromocaoForm);

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

                byte[] array = byteArrayOutputStream.toByteArray();

                imageBase64 = Base64.encodeToString(array, Base64.DEFAULT);
                Log.i("BASE 64 IMAGE", imageBase64);

                campoFoto.setImageBitmap(imageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getContext(), "Picture was not taken 1 ", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(getContext(), "Picture was not taken 2 ", Toast.LENGTH_SHORT);
            }
        }
    }

}
