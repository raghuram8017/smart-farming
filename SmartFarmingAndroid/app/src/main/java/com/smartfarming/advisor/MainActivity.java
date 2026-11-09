package com.smartfarming.advisor;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.*;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends Activity {
    private EditText farmer, farm, area, rainfall;
    private Spinner soil, season;
    private TextView output;
    private String report = "";

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        buildScreen();
    }
    private void buildScreen() {
        ScrollView scroll = new ScrollView(this);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(36, 28, 36, 28);
        page.setBackgroundColor(Color.rgb(242, 248, 240));
        scroll.addView(page);
        TextView title = new TextView(this);
        title.setText("🌱 SMART FARMING ADVISOR\nCrop & Irrigation Planner");
        title.setTextSize(23); title.setTextColor(Color.rgb(25, 91, 48)); title.setGravity(17);
        title.setPadding(0, 16, 0, 22); page.addView(title);
        farmer = field(page, "Farmer name"); farm = field(page, "Farm name");
        soil = spinner(page, Soil.values()); season = spinner(page, Season.values());
        area = field(page, "Farm area in acres (e.g. 2)"); rainfall = field(page, "Recent rainfall in mm (e.g. 30)");
        Button calculate = new Button(this); calculate.setText("GET CROP & IRRIGATION PLAN"); page.addView(calculate);
        output = new TextView(this); output.setText("Your farm recommendations will appear here."); output.setTextSize(16);
        output.setTextColor(Color.rgb(35, 55, 38)); output.setBackgroundColor(Color.WHITE); output.setPadding(22, 22, 22, 22);
        page.addView(output, new LinearLayout.LayoutParams(-1, ViewGroup.LayoutParams.WRAP_CONTENT));
        Button save = new Button(this); save.setText("SAVE FARM REPORT"); page.addView(save);
        TextView note = new TextView(this); note.setText("Educational estimates only. Confirm decisions with a local agriculture expert."); note.setTextSize(12); page.addView(note);
        calculate.setOnClickListener(v -> calculatePlan()); save.setOnClickListener(v -> saveReport());
        setContentView(scroll);
    }
    private EditText field(LinearLayout parent, String hint) {
        EditText e = new EditText(this); e.setHint(hint); e.setSingleLine(true);
        parent.addView(e, new LinearLayout.LayoutParams(-1, ViewGroup.LayoutParams.WRAP_CONTENT)); return e;
    }
    private Spinner spinner(LinearLayout parent, Object[] values) {
        Spinner s = new Spinner(this);
        s.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, values));
        parent.addView(s, new LinearLayout.LayoutParams(-1, 52)); return s;
    }
    private void calculatePlan() {
        try {
            String farmerName=farmer.getText().toString().trim(), farmName=farm.getText().toString().trim();
            if (farmerName.isEmpty() || farmName.isEmpty()) throw new IllegalArgumentException("Enter farmer and farm names.");
            double acres=Double.parseDouble(area.getText().toString().trim());
            double rain=Double.parseDouble(rainfall.getText().toString().trim());
            if (acres<=0 || rain<0) throw new IllegalArgumentException("Area must be above zero; rainfall cannot be negative.");
            Soil selectedSoil=(Soil)soil.getSelectedItem(); Season selectedSeason=(Season)season.getSelectedItem();
            Farm details=new Farm(farmerName,farmName,selectedSoil,selectedSeason,acres);
            List<Crop> suitable=new CropAdvisor().suggest(selectedSoil,selectedSeason);
            StringBuilder text=new StringBuilder("FARM DETAILS\nFarmer: ").append(details.getFarmerName()).append("\nFarm: ").append(details.getFarmName())
                .append("\nSoil: ").append(selectedSoil).append("\nSeason: ").append(selectedSeason).append("\nArea: ").append(acres).append(" acres\n\nSUITABLE CROPS\n");
            if (suitable.isEmpty()) text.append("No exact match. Please consult a local agriculture officer.\n");
            else for (Crop c : suitable) text.append("• ").append(c.getName()).append("\n");
            if (!suitable.isEmpty()) {
                Crop chosen=suitable.get(0); double litres=new IrrigationPlanner().calculateLitres(chosen,acres,rain);
                double cost=chosen.getCostPerAcre()*acres, yield=chosen.getYieldPerAcre()*acres;
                text.append("\nSAMPLE PLAN USING FIRST SUITABLE CROP\nCrop: ").append(chosen.getName())
                    .append("\nEstimated irrigation: ").append(String.format("%.0f",litres)).append(" litres")
                    .append("\nEstimated cultivation cost: Rs. ").append(String.format("%.2f",cost))
                    .append("\nExpected yield: ").append(String.format("%.2f",yield)).append(" kg\n")
                    .append(rain>=25 ? "Rainfall entered is significant; check soil moisture before watering." : "Plan irrigation in sessions and check soil moisture before watering.");
            }
            text.append("\n\nNote: sample academic estimates; not a local agronomic recommendation.");
            report=text.toString(); output.setText(report);
        } catch (NumberFormatException ex) { output.setText("Enter valid numbers for farm area and rainfall."); }
          catch (IllegalArgumentException ex) { output.setText(ex.getMessage()); }
    }
    private void saveReport() {
        if (report.isEmpty()) { output.setText("Generate a plan before saving the report."); return; }
        try (FileOutputStream stream=openFileOutput("FarmReport.txt", MODE_PRIVATE)) {
            stream.write(report.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this,"Report saved inside app storage as FarmReport.txt",Toast.LENGTH_LONG).show();
        } catch (Exception ex) { Toast.makeText(this,"Save failed: "+ex.getMessage(),Toast.LENGTH_LONG).show(); }
    }
}
