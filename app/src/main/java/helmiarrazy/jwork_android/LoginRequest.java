package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas LoginRequest, digunakan oleh jobseeker agar bisa melakukan login.
 * Login Request dilakukan melalui URL API dari Jobseeker Controller.
 *
 * @author Helmi Arrazy
 * @version 26-05-2021
 */
public class LoginRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/jobseeker/login";
    private Map<String, String> params;


    /**
     * Constructor LoginRequest, berfungsi untuk melakukan login berdasarkan input parameter yang diberikan.
     * Fungsi sebenarnya adalah untuk mendapatkan data jobseeker berdasarkan data kredensial login (email dan password) yang diberikan.
     *
     * @param email sebagai inputan email dari jobseeker yang datanya ingin didapatkan
     * @param password sebagai inputan password dari jobseeker yang datanya ingin didapatkan
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }


    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request login
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
