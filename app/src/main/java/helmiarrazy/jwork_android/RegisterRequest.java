package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Kelas ResgisterRequest, digunakan untuk melakukan register data jobseeker baru.
 * Proses registrasi atau penambahan data jobseeker baru kedalam database dilakukan melalui URL API dari Jobseeker Controller.
 *
 * @author Helmi Arrazy
 * @version 26-05-2021
 */
public class RegisterRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/jobseeker/register";
    private Map<String, String> params;


    /**
     * Constructor RegisterRequest, berfungsi untuk menambahkan data jobseeker baru berdasarkan input parameter yang diberikan.
     *
     * @param name sebagai inputan nama dari jobseeker yang baru
     * @param email sebagai inputan email dari jobseeker yang baru
     * @param password sebagai inputan password dari jobseeker yang baru
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }


    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request register
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return params;
    }
}
