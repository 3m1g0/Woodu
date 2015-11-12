package in.woodu.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

import in.woodu.R;
import in.woodu.adapter.PropertyListAdapter;

public class PropertiesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private HashMap<String, String> propertyMap = new HashMap<String, String>();
    private String[] keys = {"Material", "Color", "Weight", "Height", "Length", "Width"};
    private String[] values = {"Teak Wood", "Brown", "36 Kgs", "5'3", "3'6", "2'8"};

    public static PropertiesFragment newInstance(String param1, String param2) {
        PropertiesFragment fragment = new PropertiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PropertiesFragment() {
        // Required empty public constructor
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
        View root = inflater.inflate(R.layout.fragment_properties, container, false);
        ListView propertyList = (ListView) root.findViewById(R.id.furniture_properties_list);
        propertyList.setAdapter(new PropertyListAdapter(keys, values, getActivity()));
        return root;
    }

}
