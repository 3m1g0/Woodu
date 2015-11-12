package in.woodu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;

import in.woodu.adapter.ItemListAdapter;
import in.woodu.model.FurnitureItem;
import in.woodu.utils.Utils;
import in.woodu.widget.Toolbar;

public class ItemsListActivity extends AppCompatActivity {

    private ItemListAdapter cardViewAdapter;
    private FurnitureItem[] mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView rv = (ListView) findViewById(R.id.items_list);
        String mCategory = getIntent().getStringExtra("category");
        String contextPath = getIntent().getStringExtra("contextPath");
        toolbar.disbleLogoTitle();
        toolbar.setTitle(mCategory);
        File dir = new File(Utils.ROOT_DIR + contextPath);
        File files[] = null;
        if (dir.exists()) {
            files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return new File(file, s).getName().endsWith(".properties");
                }
            });
            if (files != null) {
                mDataset = new FurnitureItem[files.length];
                cardViewAdapter = new ItemListAdapter(mDataset, ItemsListActivity.this);
                rv.setAdapter(cardViewAdapter);
                buildData(dir, files);
            }
        }
    }

    private void buildData(File dir, File[] files) {
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                mDataset[i] = Utils.readProperties(dir, files[i].getName());
                cardViewAdapter.notifyDataSetChanged();
            }
        }
    }
}
