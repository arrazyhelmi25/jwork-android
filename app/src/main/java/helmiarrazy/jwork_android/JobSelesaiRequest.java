package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas JobSelesaiRequest, berfungsi untuk memberikan atau mengubah status dari suatu invoice menjadi selesai (Finished).
 * Perubahan status invocie dilakukan melalui URL API dari Invoice Controller.
 *
 * @author Helmi Arrazy
 * @version 19-06-2021
 */
public class JobSelesaiRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/invoice/invoiceStatus/";
    private Map<String, String> params;
    private String invoiceStatus = "Finished";


    /**
     * Constructor JobSelesaiRequest, berfungsi untuk mengubah status dari invoice berdasarkan input parameter yang diberikan.
     *
     * @param invoiceId sebagai inputan id dari invoice yang akan diubah statusnya
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public JobSelesaiRequest (String invoiceId, Response.Listener<String> listener) {
        super(Method.PUT, URL+invoiceId, listener, null);
        params = new HashMap<>();
        params.put("id", invoiceId);
        params.put("status", invoiceStatus);
    }


    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request status invoice
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
