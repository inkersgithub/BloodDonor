package in.inkers.blooddonor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sreekkutty on 9/11/17.
 */

public class BloodGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvList;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodgroup);

        rvList=findViewById(R.id.rvlist);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();

        for(int i=0;i<10;i++){
            ListItem listItem=new ListItem("Name "+i,"Place "+i);
            listItems.add(listItem);
        }

        adapter=new ListAdapter(listItems,this);
        rvList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
