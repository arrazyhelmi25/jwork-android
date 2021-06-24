package helmiarrazy.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Kelas LoginActivity, digunakan untuk melakukan login sebagai jobseeker
 *
 * @author Helmi Arrazy
 * @version 26-05-2021
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Method untuk meninisialisasi Activity Login
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        TextView tvRegister = findViewById(R.id.tvRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        ImageButton btnShowHideLogin = findViewById(R.id.btnShowHideLogin);

        // Konfigurasi respons jika tombol login ditekan
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Membuat suatu kondisi dimana email tidak boleh kosong, disertai pesan error yang ditampilkan melalui toast
                if(email.isEmpty()){
                    etEmail.setError("Email Tidak Boleh Kosong!");
                    etEmail.requestFocus();
                    return;
                }

                // Membuat suatu kondisi dimana password tidak boleh kosong, disertai pesan error yang ditampilkan melalui toast
                if(password.isEmpty()){
                    etPassword.setError("Password Tidak Boleh Kosong!");
                    etPassword.requestFocus();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // Jika kredensial login (email dan password) yang digunakan untuk login sudah terdaftar didatabase, maka login berhasil, dan akan berpindah ke main activity. Data jobseeker id juga ikut dikirimkan ke main activity.
                            if (jsonObject != null) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                loginIntent.putExtra("jobseekerId", jsonObject.getInt("id"));
                                loginIntent.addFlags(loginIntent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(loginIntent);
                                finish();
                            }
                        }
                        // Jika kredensial login tidak ditemukan di database, maka login gagal
                        catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//                            e.printStackTrace();
                        }
                    }
                };

                //Volley Request untuk Login Request
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        // Jika tulisan "Register Now" ditekan, maka akan berpindah ke Register Activity
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        // Membuat kondisi jika gambar mata atau "eye" ditekan, maka password akan muncul, jika gambar berhenti ditekan maka password akan disembunyikan lagi
        btnShowHideLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;

                    case MotionEvent.ACTION_DOWN:
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                }
                return false;
            }
        });
    }
}