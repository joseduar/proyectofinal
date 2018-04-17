package net.unadeca.ana.myrest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;


import net.unadeca.ana.myrest.util.Pedidos;
import net.unadeca.ana.myrest.R;
import net.unadeca.ana.myrest.fragments.FragImage;
import net.unadeca.ana.myrest.subclases.RunImage;
import net.unadeca.ana.myrest.util.Adapter;
import net.unadeca.ana.myrest.util.ImageAdapter;
import net.unadeca.ana.myrest.util.Session;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


        /**
         * The {@link android.support.v4.view.PagerAdapter} that will provide
         * fragments for each of the sections. We use a
         * {@link FragmentPagerAdapter} derivative, which will keep every
         * loaded fragment in memory. If this becomes too memory intensive, it
         * may be best to switch to a
         * {@link android.support.v4.app.FragmentStatePagerAdapter}.
         */
        private Adapter mSectionsPagerAdapter;
        private static Context QuickContext;
        private static RecyclerView recyclerView;
        private Session session;
        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            session = new Session(this);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            QuickContext = this;
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new Adapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            TabLayout tabLayout = findViewById(R.id.tabs);
            mSectionsPagerAdapter.addFragment(new ImageFragment(), "Food Favory");
            mSectionsPagerAdapter.addFragment(new FragImage(), "Galerias");

            mViewPager.setAdapter(mSectionsPagerAdapter);
            tabLayout.setupWithViewPager(mViewPager);


             FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
             fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent pedido = new Intent(MainActivity.this, Pedidos.class);
                startActivity(pedido);

             }
            });

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
                //borrar esto asta finish
            }else if(id == R.id.logout){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        session.logoutUser();
                        finish();

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).setMessage("seguro que quiere cerrar la secion");
                builder.show();


            }

            return super.onOptionsItemSelected(item);
        }

        /**
         * A placeholder fragment containing a simple view.
         */

        public static class ImageFragment extends Fragment {

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(QuickContext);
                layoutManager.setFlexWrap(FlexWrap.WRAP);
                layoutManager.setAlignItems(AlignItems.BASELINE);
                layoutManager.setJustifyContent(JustifyContent.CENTER);


                recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                setHasOptionsMenu(true);
                return rootView;
            }

            @Override
            public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                setupRecyclerView();
            }

            @Override
            public void onResume() {
                super.onResume();
            }

        }

        // en el main tengo que poner desde aqui paara abajo
        public static void setupRecyclerView() {
            List<RunImage> imagenes = new ArrayList<>();
            RunImage imagen = new RunImage();
            imagen.url = "https://cdn.pixabay.com/photo/2018/03/31/19/29/schnitzel-3279045_960_720.jpg";
            imagen.author = "Restaurant";
            imagen.name = "Schnitzel a tan solo $5.00";


            RunImage imagen1 = new RunImage();
            imagen1.url = "https://cdn.pixabay.com/photo/2018/03/31/07/43/bread-3277473_960_720.jpg";
            imagen1.author = "Restaurant";
            imagen1.name = "bread hoy ha $ 10.00";

            RunImage imagen2 = new RunImage();
            imagen2.url = "https://cdn.pixabay.com/photo/2017/12/10/14/47/piza-3010062_960_720.jpg";
            imagen2.author = "restaurant";
            imagen2.name = "Pizza $ 20.00";

            RunImage imagen3 = new RunImage();
            imagen3.url = "https://cdn.pixabay.com/photo/2018/03/30/18/20/lamb-cutlet-3276084_960_720.jpg";
            imagen3.author = "Restaurant";
            imagen3.name = "lamb-clut $ 35.00";
            imagenes.add(imagen2);
            imagenes.add(imagen3);




            // for (int a = 0; a < 10; a++) {
            imagenes.add(imagen);
            // }

            //  for (int a = 0; a < 10; a++) {
            imagenes.add(imagen1);
            //  }

            ImageAdapter adapter = new ImageAdapter(imagenes, QuickContext);
            recyclerView.setAdapter(adapter);
        }
}





