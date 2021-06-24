package helmiarrazy.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Kelas ApplyJobRequest, berfungsi untuk menambahkan data dari job yang telah diapply kedalam database,
 * dimana data job yang berhasil diapply tersebut akan dimasukkan kedalam database Invoice, melalui
 * URL API dari Invoice Controller.
 *
 * @author Helmi Arrazy
 * @version 17-06-2021
 */
public class ApplyJobRequest extends StringRequest {
    // Instance Variable
    private static final String URL_EWALLET = "http://10.0.2.2:8080/invoice/createEWalletPayment";
    private static final String URL_BANK = "http://10.0.2.2:8080/invoice/createBankPayment";
    private Map<String, String> params;


    /**
     * Constructor ApplyJobRequest, jika payment type yang dipilih adalah Ewallet.
     * Berfungsi untuk membuat data invoice berdasarkan input parameternya.
     *
     * @param jobIdList sebagai inputan data id job
     * @param jobseekerId sebagai inputan data id jobseeker
     * @param refferalCode sebagai inputan data referral code
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public ApplyJobRequest(String jobIdList, String jobseekerId, String refferalCode, Response.Listener<String> listener) {
        super(Method.POST, URL_EWALLET, listener, null);
        params = new HashMap<>();
        params.put("jobIdList", jobIdList);
        params.put("jobseekerId", jobseekerId);
        params.put("referralCode", refferalCode);
    }


    /**
     * Constructor ApplyJobRequest, jika payment type yang dipilih adalah Bank.
     * Berfungsi untuk membuat data invoice berdasarkan input parameternya.
     *
     * @param jobIdList sebagai inputan data id job
     * @param jobseekerId sebagai inputan data id jobseeker
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public ApplyJobRequest(String jobIdList, String jobseekerId, Response.Listener<String> listener) {
        super(Method.POST, URL_BANK, listener, null);
        params = new HashMap<>();
        params.put("jobIdList", jobIdList);
        params.put("jobseekerId", jobseekerId);
        params.put("adminFee", "1000");
    }


    /**
     * Method getParams untuk mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     *
     * @return params yaitu parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
