package com.muhsantech.covid19tracker.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.muhsantech.covid19tracker.AffectedCountries;
import com.muhsantech.covid19tracker.R;
import com.muhsantech.covid19tracker.models.CountryModel;

import java.util.ArrayList;
import java.util.List;


public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private final Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public MyCustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item, countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListFiltered = countryModelList;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_custom_item, parent, false);

        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageview = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());

        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageview);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults filterResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                }
                else {
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for (CountryModel itemsModel: countryModelList){
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }

                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;

                    }

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                countryModelListFiltered = (List<CountryModel>) filterResults.values;

                AffectedCountries.countryModelList = (List<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
