package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas BonusRequest, berfungsi untuk mendapatkan informasi sebuah Bonus berdasarkan referral
 * code nya, melalui URL API dari Bonus Controller.
 *
 * @author Helmi Arrazy
 * @version 17-06-2021
 */
public class BonusRequest extends StringRequest {
    // Instance Variable
    private static final String URL = "http://10.0.2.2:8080/bonus/";
    private Map<String,String> params;


    /**
     * Constructor BonusRequest, berfungsi untuk mendapatkan data bonus berdasarkan parameter referral code yang diinputkan
     *
     * @param refCode sebagai inputan referral code dari bonus yang akan ditampilkan
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public BonusRequest(String refCode, Response.Listener<String> listener) {
        super(Method.GET, URL+refCode, listener, null);
        params = new HashMap<>();
    }

    /**
     * Method getParams untuk mengembalikan parameter Map yang digunakan untuk request bonus
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return params;
    }
}
