package com.idankorenisraeli.soccerbattle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopTenTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTenTable extends Fragment {

    TableLayout table;
    TableEntry[] rows = new TableEntry[10];
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


        addRow(new TableEntry("Idan", 6, new LatLng(0.5,0.5)));




        return fragmentView;
    }

    private void addRow(TableEntry entry){
        View tableRow = LayoutInflater.from(getActivity()).inflate(R.layout.layout_top_ten_row, table, false);


        TextView rank = tableRow.findViewById(R.id.table_row_rank);
        TextView name = tableRow.findViewById(R.id.table_row_name);
        TextView turns = tableRow.findViewById(R.id.table_row_turns);
        TextView location = tableRow.findViewById(R.id.table_row_location);

        rank.setText("" + ++lastTableRank);
        name.setText(entry.getName());
        turns.setText("" + entry.getTurns());
        location.setText("Israel");

        table.addView(tableRow);
    }

}