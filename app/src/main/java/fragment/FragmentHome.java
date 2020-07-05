package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.kithnkin.yummytreat.R;
import com.kithnkin.yummytreat.SpacesItemDecoration;

import java.util.ArrayList;

import adapter.HomeAdapter;
import bean.DessertBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    RecyclerView recyclerView;
    private Firebase firebase;
    private View view;
    private String url = "https://yummy-treat.firebaseio.com/Dessert/";
    ArrayList<DessertBean> dessertBeanArrayList = new ArrayList<DessertBean>();

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebase.setAndroidContext(this.getActivity());
        firebase = new Firebase(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_home, container, false);
        Query queryRef = firebase.orderByChild("type").equalTo("recent");
        if (queryRef != null) {
            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot != null) {
                                DessertBean dessertBean = snapshot.getValue(DessertBean.class);
                                dessertBeanArrayList.add(dessertBean);
                                Log.e("Getting Size", dessertBeanArrayList.size() + "");
                            }
                        }
                        setupRecycler(dessertBeanArrayList, view);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.e("Firebase Error", firebaseError.getMessage());
                }
            });
        }
        return view;
    }

    private void setupRecycler(ArrayList<DessertBean> dessertBeanArrayList, View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerHome);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), dessertBeanArrayList);
        recyclerView.setAdapter(homeAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }
}
