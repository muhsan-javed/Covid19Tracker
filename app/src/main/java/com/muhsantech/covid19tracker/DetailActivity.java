package com.muhsantech.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;

    TextView tvCountry ,tvCase, tvTodayC, tvDeath, tvToDD, tvRecover, tvActives, tvCriticalCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent  = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Detail of " +AffectedCountries.countryModelList.get(positionCountry).getCountry() );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry = findViewById(R.id.tvCountry);
        tvCase =findViewById(R.id.tvCase);
        tvTodayC =findViewById(R.id.tvTodayC);
        tvDeath =findViewById(R.id.tvDeath);
        tvToDD =findViewById(R.id.tvToDD);
        tvRecover =findViewById(R.id.tvRecover);
        tvActives =findViewById(R.id.tvActives);
        tvCriticalCases =findViewById(R.id.tvCriticalCases);


        tvCountry.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        tvCase.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tvTodayC.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tvDeath.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
        tvToDD.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeaths());
        tvRecover.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tvActives.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
        tvCriticalCases.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}