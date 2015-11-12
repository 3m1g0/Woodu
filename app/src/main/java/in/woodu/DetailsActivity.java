package in.woodu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import in.woodu.fragment.DescriptionFragment;
import in.woodu.fragment.PropertiesFragment;
import in.woodu.widget.SlidingTabLayout;
import in.woodu.widget.Toolbar;

public class DetailsActivity extends AppCompatActivity {

    private ViewPager mPager;
    private Toolbar toolbar;
    private String title;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
                Snackbar.make(view, "Opens Unity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        title = getIntent().getStringExtra("title");
        uri = getIntent().getStringExtra("uri");

        toolbar.disbleLogoTitle();
        toolbar.setTitle(title);

        mPager = (ViewPager) findViewById(R.id.details_pager);
        ScreenSlidePagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(mPagerAdapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.details_pager_header);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mPager);

//        TextView name = (TextView) findViewById(R.id.details_item_name);
        setTitle(title.toUpperCase());
        ImageView image = (ImageView) findViewById(R.id.details_image);
        image.setImageURI(Uri.parse(uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_try_now) {
//            Intent intent = new Intent(DetailsActivity.this, UnityPlayerNativeActivity.class);
//            intent.putExtra("uri", uri);
//            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private String titles[] = {"Description", "Properties"};

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return DescriptionFragment.newInstance(null, null);
            else
                return PropertiesFragment.newInstance(null, null);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
