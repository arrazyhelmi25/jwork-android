package helmiarrazy.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Kelas ApplyJobActivity, yaitu activity untuk mengapply suatu pekerjaan (Job)
 *
 * @author Helmi Arrazy
 * @version 17-06-2021
 */
public class ApplyJobActivity extends AppCompatActivity {
    //Instance Variable
    private int jobseekerId;
    private int jobId;
    private String jobName;
    private String jobCategory;
    private double jobFee;
    private int bonus;
    private  String selectedPayment;


    /**
     * Method untuk meninisialisasi Activity Apply Job
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Menampilkan layout xml activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);


        //Mendapatkan informasi dari value yang disimpan pada variable extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jobId = extras.getInt("job_id");
            jobName = extras.getString("job_name");
            jobCategory = extras.getString("job_category");
            jobFee = extras.getInt("job_fee");
            jobseekerId = extras.getInt("jobseekerId");
        }

        //TextView (tv)
        final TextView tvJobName = findViewById(R.id.job_name);
        final TextView tvJobCategory = findViewById(R.id.job_category);
        final TextView tvJobFee = findViewById(R.id.job_fee);
        final TextView tvTextCode = findViewById(R.id.textCode);
        final TextView tvTotalFee = findViewById(R.id.total_fee);

        //EditText (et)
        final EditText etReferralCode = findViewById(R.id.referral_code);

        //RadioGroup
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //Button
        final Button btnHitung = findViewById(R.id.hitung);
        final Button btnApply = findViewById(R.id.btnApply);


        // Komponen yang dihide terlebih dahulu
        btnApply.setVisibility(View.INVISIBLE);
        tvTextCode.setVisibility(View.INVISIBLE);
        etReferralCode.setVisibility(View.INVISIBLE);


        // Menset isi data untuk beberapa variable
        tvJobName.setText(jobName);
        tvJobCategory.setText(jobCategory);
        tvJobFee.setText("Rp. " + String.valueOf(jobFee));
        tvTotalFee.setText("Rp. 0");


        // Konfigruasi respons jika radio group button ditekan
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rbId = findViewById(checkedId);
                String payMethod = rbId.getText().toString().trim();
                switch (payMethod) {
                    // Case jika memilih metode pembayaran ewallet
                    case "E-wallet":
                        tvTextCode.setVisibility(View.VISIBLE);
                        etReferralCode.setVisibility(View.VISIBLE);
                        break;

                    // Case jika memilih metode pembayaran bank
                    case "Bank":
                        tvTextCode.setVisibility(View.INVISIBLE);
                        etReferralCode.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });


        // Konfigurasi respons ketika tombol hitung (count) ditekan
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                switch (checkedId) {

                    case R.id.bank:
                        tvTotalFee.setText(String.valueOf(jobFee));
                        break;

                    case R.id.ewallet:
                        // Mendapatkan string referral code
                        String refCode = etReferralCode.getText().toString();

                        // Listener Referral Coe
                        final Response.Listener<String> bonusResponse = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Jika referral code kosong, maka nilai total fee = job fee
                                if (refCode.isEmpty()) {
                                    tvTotalFee.setText(String.valueOf(jobFee));

                                // Jika referral code ada inputnya atau tidak kosong
                                } else {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);

                                        // Mendapatkan data bonus dari referral code
                                        int extraFee = jsonResponse.getInt("extraFee");
                                        int minTotalFee = jsonResponse.getInt("minTotalFee");
                                        boolean bonusStatus = jsonResponse.getBoolean("active");

                                        // Jika referral code berasal dari bonus status yang tidak aktif (false) maka akan ditampilkan pesan toast seperti di bawah ini
                                        if (!bonusStatus) {
                                            Toast.makeText(ApplyJobActivity.this, "Bonus Tidak Tersedia!", Toast.LENGTH_LONG).show();

                                        // Jika referral code berasal dari bonus yang statusnya active (true)
                                        } else if (bonusStatus) {

                                            // Referral code tidak dapat digunakan, jika extrafee dan mintotalfee lebih besar dari jobfee
                                            if (jobFee < extraFee || jobFee < minTotalFee) {
                                                Toast.makeText(ApplyJobActivity.this, "Referral Code Tidak Bisa Digunakan Karena Tidak Memenuhi Persyaratan!", Toast.LENGTH_LONG).show();
                                            }

                                            // Referral Code dapat digunakan
                                            else {
                                                Toast.makeText(ApplyJobActivity.this, "Referral Code Berhasil Digunakan!", Toast.LENGTH_LONG).show();
                                                //Set Total Price
                                                tvTotalFee.setText(String.valueOf(jobFee + extraFee));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        // Jika tidak ada referral code yang diinputkan ternyata tidak ada didalam database
                                        Toast.makeText(ApplyJobActivity.this, "Referral Code Tidak Ditemukan!", Toast.LENGTH_LONG).show();
                                        tvTotalFee.setText(String.valueOf(jobFee));
                                    }
                                }

                            }
                        };
                        Response.ErrorListener errorPromo = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error", "Error Occured", error);
                            }
                        };

                        //Volley Request untuk Bonus Request
                        BonusRequest bonusRequest = new BonusRequest(refCode, bonusResponse);
                        RequestQueue queue = Volley.newRequestQueue(ApplyJobActivity.this);
                        queue.add(bonusRequest);
                        break;

                }
                btnHitung.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.VISIBLE);
            }
        });


        // Respons ketika tombol apply ditekan
        btnApply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                ApplyJobRequest request = null;

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //Jika data ada dan tidak kosong maka apply berhasil
                            if (jsonObject != null) {
                                Toast.makeText(ApplyJobActivity.this, "Apply Successful!", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            // Jika data kosong maka job tidak berhasil di apply
                            else {
                                Toast.makeText(ApplyJobActivity.this, "Apply Failed!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                        // Jika adanya kesalahan lain
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ApplyJobActivity.this, "Apply Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                if(selectedRadioId == R.id.bank) {
                    //Volley Request untuk Apply Job Request
                    request = new ApplyJobRequest(String.valueOf(jobId), String.valueOf(jobseekerId), responseListener);
                    RequestQueue q = Volley.newRequestQueue(ApplyJobActivity.this);
                    q.add(request);
                }

                else if(selectedRadioId == R.id.ewallet) {
                    //Volley Request untuk Apply Job Request
                    request = new ApplyJobRequest(String.valueOf(jobId), String.valueOf(jobseekerId), etReferralCode.getText().toString(), responseListener);
                    RequestQueue q = Volley.newRequestQueue(ApplyJobActivity.this);
                    q.add(request);
                }
            }
        });
    }
}