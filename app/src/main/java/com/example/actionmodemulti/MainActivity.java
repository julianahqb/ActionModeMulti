package com.example.actionmodemulti;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AbsListView.MultiChoiceModeListener {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> selecionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lista);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        adapter.add("Item 1");
        adapter.add("Item 2");
        adapter.add("Item 3");
        adapter.add("Item 4");
        adapter.add("Item 5");
        adapter.add("Item 6");
        adapter.add("Item 7");
        adapter.add("Item 8");

        listView.setAdapter(adapter);

        listView.setMultiChoiceModeListener(this);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.act_delete){
            for(String s: selecionados){
                adapter.remove(s);
            }
            actionMode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        int count = listView.getChildCount();

        for(int i=0; i<count; i++){
            View view = listView.getChildAt(i);
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        selecionados.clear();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
        String s = adapter.getItem(i);
        View view = listView.getChildAt(i);

        if(b){
            view.setBackgroundColor(Color.BLUE);
            selecionados.add(s);
        }
        else{
            view.setBackgroundColor(Color.TRANSPARENT);
            selecionados.remove(s);
        }
    }

}