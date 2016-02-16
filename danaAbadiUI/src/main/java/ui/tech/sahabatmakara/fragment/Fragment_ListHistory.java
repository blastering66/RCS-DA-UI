package ui.tech.sahabatmakara.fragment;

import java.util.ArrayList;

import ui.tech.sahabatmakara.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import ui.tech.sahabatmakara.adapters.CustomAdapter_ListHistory;
import ui.tech.sahabatmakara.entities.RowData_History;

public class Fragment_ListHistory extends Fragment{
	ArrayList<RowData_History> objects;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.layout_listview, null);
		ListView lv = (ListView)v.findViewById(R.id.lv);
		
		objects = new ArrayList<RowData_History>();
//		objects.add(new RowData_History("", "Beasiswa Fakultas Teknik", "Rp. 1.000.000"));
		
		CustomAdapter_ListHistory adapter = new CustomAdapter_ListHistory(getActivity(), 0, objects);
		lv.setAdapter(adapter);
		
		return v;
	}

}
