package helmiarrazy.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Kelas ResgisterActivity, digunakan untuk melakukan login sebagai jobseeker
 *
 * @author Helmi Arrazy
 * @version 26-05-2021
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Method untuk meninisialisasi Register Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etNameR = findViewById(R.id.etNameR);
        EditText etEmailR = findViewById(R.id.etEmailR);
        EditText etPasswordR = findViewById(R.id.etPasswordR);
        Button btnRegister = findViewById(R.id.btnRegister);
        ImageButton btnShowHide = findViewById(R.id.btnShowHideRegister);


        // Konfigruasi respons jika tombol register ditekan
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNameR.getText().toString();
                String email = etEmailR.getText().toString();
                String password = etPasswordR.getText().toString();

                // Membuat suatu kondisi dimana nama tidak boleh kosong, disertai pesan error yang ditampilkan melalui toast
                if(name.isEmpty()){
                    etNameR.setError("Nama Tidak Boleh Kosong!");
                    etNameR.requestFocus();
                    return;
                }

                // Membuat suatu kondisi dimana email tidak boleh kosong, disertai pesan error yang ditampilkan melalui toast
                if(email.isEmpty()){
                    etEmailR.setError("Email Tidak Boleh Kosong!");
                    etEmailR.requestFocus();
                    return;
                }

                // Membuat suatu kondisi dimana password tidak boleh kosong, disertai pesan error yang ditampilkan melalui toast
                if(password.isEmpty()){
                    etPasswordR.setError("Password Tidak Boleh Kosong!");
                    etPasswordR.requestFocus();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);

                            // Kondisi Jika Register Berhasil
                            if (jsonObject != null) {
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                finish();
                            }
                        }
                        // Kondisi jika register gagal
                        catch (JSONException e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                //Volley Request untuk Register Request
                RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


        // Tombol dengan gambar mata 'eyes', jika ditekan maka password akan ditampilkan, jika tommbol berhenti ditekan password akan disembunyikan lagi
        btnShowHide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        etPasswordR.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;

                    case MotionEvent.ACTION_DOWN:
                        etPasswordR.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                }
                return false;
            }
        });
    }
}