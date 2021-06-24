package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Kelas JobFecthRequest, berfungsi untuk mendapatkan data invoice berdasarkan Id Jobseeker.
 * Proses untuk mendapatkan data invocie dilakukan melalui URL API dari Invoice Controller.
 *
 * @author Helmi Arrazy
 * @version 19-06-2021
 */
public class JobFetchRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/invoice/jobseeker/";
    private Map<String, String> params;


    /**
     * Constructor JobFetchRequest, berfungsi untuk mendapatkan data invoice berdasarkan input parameter yang diberikan.
     *
     * @param jobseekerId sebagai inputan id jobseeker untuk mendapatkan data invoice
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public JobFetchRequest(String jobseekerId, Response.Listener<String> listener) {
        super(Method.GET, URL+jobseekerId, listener, null);
        params = new HashMap<>();
    }

    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request invoice
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return params;
    }
}
