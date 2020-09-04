package com.idankorenisraeli.soccerbattle;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopTenTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTenTable extends Fragment {

    TableLayout table;
    TableEntry[] entries = new TableEntry[10];
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
        table = fragmentView.findViewById(R.id.top_ten_table);

        entries[0] = new TableEntry("Idan", 6, new LatLng(32.817280,34.988762));
        entries[1] = new TableEntry("Shay", 5, new LatLng(32.817280,32.986762));
        entries[2] = new TableEntry("Bobo", 7, new LatLng(31.617270,35.981762));
        entries[3] = new TableEntry("Momo", 8, new LatLng(31.217270,35.581762));
        entries[4] = new TableEntry("Kobi", 4, new LatLng(32.417270,34.581762));


        addAllEntries();
        return fragmentView;
    }

    private void addAllEntries(){
        for(TableEntry entry : entries){
            if(entry!=null)
                addRow(entry);
        }
    }

    private void addRow(TableEntry entry){
        View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.layout_table_row, table, false);

        TextView rank = tableRow.findViewById(R.id.table_row_rank);
        TextView name = tableRow.findViewById(R.id.table_row_name);
        TextView turns = tableRow.findViewById(R.id.table_row_turns);
        TextView location = tableRow.findViewById(R.id.table_row_location);

        rank.setText("" + ++lastTableRank);
        name.setText(entry.getName());
        turns.setText("" + entry.getTurns());
        location.setText(getAddressFromLocation(entry.getLocation()));

        table.addView(tableRow);
    }


    // Converting the location to an address
    private String getAddressFromLocation(LatLng location) {
        try {
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

    public TableEntry getEntryByRank(int rank) throws InputMismatchException {
        if(rank > 10 || rank < 1){
            throw new InputMismatchException("Table ranks are between 1 and 10");
        }
        return entries[rank-1];
        // For example - when trying to get the 3rd rank entry details, its the [2]nd index in array.
    }

    public TableLayout getTableView(){
        return table;
    }
}