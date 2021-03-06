package org.cptjmg.consultaprocesso.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

import org.cptjmg.consultaprocesso.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

        public static ProgressDialog showLoadingDialog(Context context) {
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.show();
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            return progressDialog;
        }

        public static Map<String, String> getTimelineDate(String date){ //ex 07/03/2017
            Map<String, String> map = new HashMap<>();
            GregorianCalendar gc = new GregorianCalendar();
            String[] monthNames = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"};
            try {
                gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                int diaDoMes = gc.get(Calendar.DAY_OF_MONTH);
                map.put("dia", diaDoMes + " " +monthNames[gc.get(Calendar.MONTH)]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("ano", gc.get(Calendar.YEAR) + "");
            return map;
        }

    public static void vibrate(Context context, int mills) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(mills, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(mills);
        }
    }
}
