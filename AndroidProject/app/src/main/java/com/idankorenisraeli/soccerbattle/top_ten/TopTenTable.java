package com.idankorenisraeli.soccerbattle.top_ten;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.reflect.TypeToken;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.common.SharedPrefsManager;
import com.idankorenisraeli.soccerbattle.game.GameResult;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopTenTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTenTable extends Fragment {
    private static final int TABLE_SIZE = 10;

    TableLayout table;
    ArrayList<GameResult> allResults;
    GameResult[] tableResults = new GameResult[TABLE_SIZE];
    int lastTableRank = 0;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopTenTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopTenTable.
     */
    // TODO: Rename and change types and number of parameters
    public static TopTenTable newInstance(String param1, String param2) {
        TopTenTable fragment = new TopTenTable();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup fragmentView = (ViewGroup) inflater.inflate(R.layout.fragment_top_ten_table, container, false);
        table = fragmentView.findViewById(R.id.top_ten_LAY_table);

        initEntries();
        addAllEntries();
        return fragmentView;
    }


    // Getting sp saved results and extracting top 10 entries out of this.
    private void initEntries(){
        SharedPrefsManager prefs = SharedPrefsManager.getInstance();
        TypeToken<ArrayList<GameResult>> token = new TypeToken<ArrayList<GameResult>>() {};
        allResults = prefs.getArray(SharedPrefsManager.KEYS.SP_ALL_RESULTS, token);
        if(allResults!=null) {
            allResults.sort(new SortByTurns());

            for (int i = 0; i < tableResults.length; i++) {
                if (i > allResults.size() - 1)
                    break;
                tableResults[i] = allResults.get(i); // Copying part of all the results
            }
        }
    }

    private void addAllEntries(){
        for(GameResult entry : tableResults){
            if(entry!=null)
                addRow(entry);
        }
    }

    private void addRow(GameResult entry){
        View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.layout_table_row, table, false);

        TextView rank = tableRow.findViewById(R.id.table_row_LBL_rank);
        TextView name = tableRow.findViewById(R.id.table_row_LBL_name);
        TextView turns = tableRow.findViewById(R.id.table_row_LBL_turns);
        TextView location = tableRow.findViewById(R.id.table_row_LBL_location);

        rank.setText(String.valueOf(++lastTableRank));
        name.setText(entry.getName());
        turns.setText(String.valueOf(entry.getTurns()));
        location.setText(getAddressFromLocation(entry.getLocation()));

        table.addView(tableRow);
    }


    // Converting the location (lat,lang) to a real address string
    private String getAddressFromLocation(LatLng location) {
        try {
            if(location == null)
                return "N/A"; // Couldn't detect location.
            Geocoder coder = new Geocoder(getActivity());
            List<Address> addressList = coder.getFromLocation(location.latitude, location.longitude, 1);
            if (addressList.size() == 0)
                throw new IOException("Address could not be found");
            Address address = addressList.get(0);
            StringBuilder result = new StringBuilder();
            if (address.getLocality() != null) {
                result.append(address.getLocality());
                result.append(", ");
            }
            if (address.getCountryName() != null) {
                result.append(address.getCountryName());
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.FLOOR);
        return "(" + df.format(location.latitude) + ", " + df.format(location.longitude) + ")";
    }

    public GameResult getEntryByRank(int rank) throws InputMismatchException {
        if(rank > 10 || rank < 1){
            throw new InputMismatchException("Table ranks are between 1 and 10");
        }
        return tableResults[rank-1];
        // For example - when trying to get the 3rd rank entry details, its the [2]nd index in array.
    }

    public TableLayout getTableView(){
        return table;
    }
}