package helmiarrazy.jwork_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Kelas SelesaiJobActivity, berfungsi untuk menampilkan data invoice atau pekerjaan yang sudah di apply pada Activity Apply Job.
 *
 * @author Helmi Arrazy
 * @version 19-06-2021
 */
public class SelesaiJobActivity extends AppCompatActivity {
    // Instance Variable
    private static int jobseekerId;
    private int invoiceId;
    private String jobseekerName;
    private String invoiceDate;
    private String paymentType;
    private String invoiceStatus;
    private String refCode;
    private String jobName;
    private int jobFee;
    private int totalFee;
    private JSONObject bonus;

    // Textview
    private TextView staticInvoiceId;
    private TextView staticJobseekerName;
    private TextView staticInvoiceDate;
    private TextView staticPaymentType;
    private TextView staticInvoiceStatus;
    private TextView staticRefCode;
    private TextView staticJobNameSelesai;
    private TextView staticTotalFeeSelesai;

    private TextView jobseeker_name;
    private TextView invoice_date;
    private TextView payment_type;
    private TextView invoice_status;
    private TextView ref_code;
    private TextView job_name_selesai;
    private TextView fee_selesai;
    private TextView total_fee_selesai;

    // Button
    private Button btnCancel;
    private Button btnFinish;

    /**
     * Method untuk meninisialisasi Selesai Job Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_job_activty);

        staticInvoiceId = findViewById(R.id.staticInvoiceId);
        staticJobseekerName = findViewById(R.id.staticJobseekerName);
        staticInvoiceDate= findViewById(R.id.staticInvoiceDate);
        staticPaymentType = findViewById(R.id.staticPaymentType);
        staticInvoiceStatus = findViewById(R.id.staticInvoiceStatus);
        staticRefCode = findViewById(R.id.staticRefCode);
        staticJobNameSelesai = findViewById(R.id.staticJobNameSelesai);
        staticTotalFeeSelesai = findViewById(R.id.staticTotalFeeSelesai);

        jobseeker_name = findViewById(R.id.jobseeker_name);
        invoice_date= findViewById(R.id.invoice_date);
        payment_type = findViewById(R.id.payment_type);
        invoice_status = findViewById(R.id.invoice_status);
        ref_code = findViewById(R.id.ref_code);
        job_name_selesai = findViewById(R.id.job_name_selesai);
        fee_selesai = findViewById(R.id.fee_selesai);
        total_fee_selesai = findViewById(R.id.total_fee_selesai);

        btnCancel = findViewById(R.id.btnCancel);
        btnFinish = findViewById(R.id.btnFinish);


        //Mendapatkan informasi dari value yang disimpan pada variable extras
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            jobseekerId = extras.getInt("jobseekerId");
        }

        //initial component visibility
        staticInvoiceId.setVisibility(View.INVISIBLE);
        staticJobseekerName.setVisibility(View.INVISIBLE);
        staticInvoiceDate.setVisibility(View.INVISIBLE);
        staticPaymentType.setVisibility(View.INVISIBLE);
        staticInvoiceStatus.setVisibility(View.INVISIBLE);
        staticRefCode.setVisibility(View.INVISIBLE);
        staticRefCode.setVisibility(View.INVISIBLE);
        staticJobNameSelesai.setVisibility(View.INVISIBLE);
        staticTotalFeeSelesai.setVisibility(View.INVISIBLE);

        jobseeker_name.setVisibility(View.INVISIBLE);
        invoice_date.setVisibility(View.INVISIBLE);
        payment_type.setVisibility(View.INVISIBLE);
        invoice_status.setVisibility(View.INVISIBLE);
        ref_code.setVisibility(View.INVISIBLE);
        job_name_selesai.setVisibility(View.INVISIBLE);
        fee_selesai.setVisibility(View.INVISIBLE);
        total_fee_selesai.setVisibility(View.INVISIBLE);

        btnCancel.setVisibility(View.INVISIBLE);
        btnFinish.setVisibility(View.INVISIBLE);

        // Memanggil method fetchJob
        fetchJob();


        // Konfigurasi Respons jika tombol cancel ditekan
        // Status dari invoice yang sebelumnya Ongoing akan berubah menjadi Cancelled
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Response.Listener<String> cancelListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(SelesaiJobActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //Volley Request untuk batalRequest
                Toast.makeText(SelesaiJobActivity.this, "Apply Job di Batalkan!", Toast.LENGTH_LONG).show();
                JobBatalRequest batalRequest = new JobBatalRequest(String.valueOf(invoiceId), cancelListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
                queue.add(batalRequest);
            }
        });


        // Konfigurasi jika tombol finish ditekan
        // Status invoice akan berubah dari Ongoing menjadi Finished
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Response.Listener<String> doneListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(SelesaiJobActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //Volley Request untuk selesaiRequest
                Toast.makeText(SelesaiJobActivity.this, "Apply Job Selesai!", Toast.LENGTH_LONG).show();
                JobSelesaiRequest selesaiRequest = new JobSelesaiRequest(String.valueOf(invoiceId), doneListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
                queue.add(selesaiRequest);
            }
        });

    }


    /**
     * Method fetchJob, berfungsi untuk mendapatkan data invoice dengan menggunakan id jobseeker
     *
     */
    private void fetchJob(){
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Jika database invoice kosong, maka akan ditampilkan pesan error melalui toast. Kemudian activity akan langsung berubah ke Main Activity lagi.
                if (response.isEmpty()) {
                    Toast.makeText(SelesaiJobActivity.this, "Belum Ada Job yang di Apply!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SelesaiJobActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                // Jika ada data didalam database invoice, maka semua komponen layout yang sebelumnya di hiddden akan ditampilkan (visible)
                else {
                    staticInvoiceId.setVisibility(View.VISIBLE);
                    staticJobseekerName.setVisibility(View.VISIBLE);
                    staticInvoiceDate.setVisibility(View.VISIBLE);
                    staticPaymentType.setVisibility(View.VISIBLE);
                    staticInvoiceStatus.setVisibility(View.VISIBLE);
                    staticRefCode.setVisibility(View.VISIBLE);
                    staticRefCode.setVisibility(View.VISIBLE);
                    staticJobNameSelesai.setVisibility(View.VISIBLE);
                    staticTotalFeeSelesai.setVisibility(View.VISIBLE);

                    jobseeker_name.setVisibility(View.VISIBLE);
                    invoice_date.setVisibility(View.VISIBLE);
                    payment_type.setVisibility(View.VISIBLE);
                    invoice_status.setVisibility(View.VISIBLE);
                    ref_code.setVisibility(View.VISIBLE);
                    job_name_selesai.setVisibility(View.VISIBLE);
                    fee_selesai.setVisibility(View.VISIBLE);
                    total_fee_selesai.setVisibility(View.VISIBLE);

                    btnCancel.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.VISIBLE);
                }

                try {
                    //Proses mendapatkan semua data yang berhubungan dengan invoice
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++) {
                        JSONObject jsonInvoice = jsonResponse.getJSONObject(i);
                        invoiceStatus = jsonInvoice.getString("invoiceStatus");
                        invoiceId = jsonInvoice.getInt("id");
                        invoiceDate = jsonInvoice.getString("date");
                        paymentType = jsonInvoice.getString("paymentType");
                        totalFee = jsonInvoice.getInt ("totalFee");
                        refCode = "---";
                        try{
                            // Proses mendapatkan data referral code jika saat apply job ada menginputkan referral code
                            bonus = jsonInvoice.getJSONObject("bonus");
                            refCode = bonus.getString("referralCode");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // Menset variable layout dengan data invoice yang sesuai
                        staticInvoiceId.setText(String.valueOf(invoiceId));
                        invoice_date.setText(invoiceDate.substring(0,10));
                        payment_type.setText(paymentType);
                        total_fee_selesai.setText("Rp. " +String.valueOf(totalFee));
                        invoice_status.setText(invoiceStatus);
                        ref_code.setText(refCode);


                        // Proses mendapatkan data jobseeker
                        JSONObject jsonCustomer = jsonInvoice.getJSONObject("jobseeker");
                        jobseekerName = jsonCustomer.getString("name");
                        jobseeker_name.setText(jobseekerName);

                        // Proses mendapatkan data job
                        JSONArray jsonJobs = jsonInvoice.getJSONArray("jobs");
                        for (int j=0; j<jsonJobs.length(); j++) {
                            JSONObject jsonJobObj = jsonJobs.getJSONObject(j);
                            jobName = jsonJobObj.getString("name");
                            job_name_selesai.setText(jobName);

                            jobFee = jsonJobObj.getInt("fee");
                            fee_selesai.setText("Rp. " +String.valueOf(jobFee));
                        }
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //Volley Request untuk fetchRequest
        JobFetchRequest fetchRequest = new JobFetchRequest(String.valueOf(jobseekerId), responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiJobActivity.this);
        queue.add(fetchRequest);
    }

}