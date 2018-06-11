package com.apiit.izzath.wmad2.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Fragments.Aboutus;
import com.apiit.izzath.wmad2.Fragments.AddCart;
import com.apiit.izzath.wmad2.Fragments.CancelOrders;
import com.apiit.izzath.wmad2.Fragments.Home;
import com.apiit.izzath.wmad2.Fragments.ManageAccounts;
import com.apiit.izzath.wmad2.Fragments.Profile;
import com.apiit.izzath.wmad2.Fragments.PurchaseHistory;
import com.apiit.izzath.wmad2.Fragments.default_home;
import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Favorites;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.Purchase;
import com.apiit.izzath.wmad2.R;
import com.apiit.izzath.wmad2.grid.home1;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView cart,wish,purchases,cancel;
    ArrayList<Purchase> purchaseArrayAdapter=new ArrayList<Purchase>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Fragment fragment=new Home();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.aaa,fragment).commit();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cart=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.addcart));

        wish=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.wish));

        cancel=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.cancel));



        purchases=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.purchase));
        initializeCountDrawer();


    }
    private void initializeCountDrawer(){
        //Gravity property aligns the text
        cart.setGravity(Gravity.CENTER_VERTICAL);
        cart.setTypeface(null, Typeface.BOLD);
        cart.setTextColor(getResources().getColor(R.color.blueone));
        SharedPreferences sp = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        Long id = sp.getLong("id", 10);
        List<Cart> cartlist = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Pending");
        cart.setText(String.valueOf(cartlist.size()));


        List<Favorites> wishlist = Favorites.findWithQuery(Favorites.class, "Select * from Favorites where user = ?  ", id.toString());

        wish.setGravity(Gravity.CENTER_VERTICAL);
        wish.setTypeface(null,Typeface.BOLD);
        wish.setTextColor(getResources().getColor(R.color.blueone));
        wish.setText(String.valueOf(wishlist.size()));

        List<Cart> cartlistCancled = Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(), "Canceled");

        cancel.setGravity(Gravity.CENTER_VERTICAL);
        cancel.setTypeface(null,Typeface.BOLD);
        cancel.setTextColor(getResources().getColor(R.color.blueone));
        cancel.setText(String.valueOf(cartlistCancled.size()));

        final List<Purchase> purchase=Purchase.listAll(Purchase.class);
        for (Purchase purchases:purchase
                ) {

            if(purchases.getCart().getRegister().getId().equals(id)){
                purchaseArrayAdapter.add(purchases);
            }
        }
        purchases.setGravity(Gravity.CENTER_VERTICAL);
        purchases.setTypeface(null,Typeface.BOLD);
        purchases.setTextColor(getResources().getColor(R.color.blueone));
        purchases.setText(String.valueOf(purchaseArrayAdapter.size()));
    }

    public void home(){
        Fragment fragment=new Home();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.aaa,fragment).commit();
        setTitle("Style Omega");

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if( fragment instanceof Home){
                super.onBackPressed();
            }
            else {
               home();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);

        return true;

        }
    private List<Product> filter(List<Product> p1,String query){
    query=query.toLowerCase();
    List<Product> filtermodel=new ArrayList<>();
        for (Product pp:
           p1  ) {
            String text=pp.getName().toLowerCase();
            if(text.startsWith(query)){
                filtermodel.add(pp);

            }
        }
        return filtermodel;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.home) {
             fragment=new Home();
            FragmentManager fm=getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.aaa,fragment).commit();

            setTitle("Style Omega");
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.cata) {
            fragment= new default_home();
            Toast.makeText(this, "Catagories", Toast.LENGTH_SHORT).show();
            setTitle("Style Omega");
        }
        else if (id == R.id.account) {
            fragment= new Profile();
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            setTitle("Profile");
        }
        else if (id == R.id.addcart) {
            fragment= new AddCart();
            Toast.makeText(this, "Cart Details", Toast.LENGTH_SHORT).show();
            setTitle("Orders");

        }
        else if (id == R.id.wish) {
                 fragment=new home1();
            setTitle("Favorites");


        }

        else if (id == R.id.purchase) {
                fragment=new PurchaseHistory();
                setTitle("Purchase History");
                Toast.makeText(this, "Purchase history", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.logout) {
            SharedPreferences sp =this.getApplicationContext().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            finish();
            Intent intent=new Intent(Drawer.this,login.class);
            startActivity(intent);
           // Toast.makeText(this, "x=dilrukshi", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.settings) {
            fragment=new ManageAccounts();
            setTitle("Settings");
        }
        else if (id == R.id.about) {
            fragment=new Aboutus();
            setTitle("About Us");
        }
        else if (id == R.id.cancel) {
            fragment=new CancelOrders();
            setTitle("Cancelled Orders");
        }
        if(fragment!=null){

            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.aaa,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
