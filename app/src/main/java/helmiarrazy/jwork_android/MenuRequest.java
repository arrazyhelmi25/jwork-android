package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas MenuRequest, berfungsi untuk mendapatkan semua informasi yang ada didalam database Job, termasuk informasi dari Recruiternya.
 * Proses request ini dilakukan melalui URL API dari Job Controller.
 *
 * @author Helmi Arrazy
 * @version 26-05-2021
 */
public class MenuRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/job";
    private Map<String, String> params;

    /**
     * Constructor MenuRequest, berfungsi untuk mendapatkan semua data job yang ada didatabase job, termasuk informasi mengenai recruiternya.
     *
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
    }


    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request menu atau data job
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
