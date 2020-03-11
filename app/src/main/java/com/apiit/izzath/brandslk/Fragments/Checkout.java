package com.apiit.izzath.brandslk.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Interface.CartService;
import com.apiit.izzath.brandslk.Interface.ProductService;
import com.apiit.izzath.brandslk.Interface.UserService;
import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.Models.Order;
import com.apiit.izzath.brandslk.Models.OrderItemWrapper;
import com.apiit.izzath.brandslk.Models.Register;
import com.apiit.izzath.brandslk.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Izzath on 5/29/2018.
 */

public class Checkout extends Fragment{
    TextView txtquantity,txtprice,txtname,txtemail;
    EditText number,cvc,address;
private   double price;
String username;
private int quantity;
Button buy;
    List<Cart> userCart;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout, container, false);
        txtname=(TextView)view.findViewById(R.id.name);
        txtemail=(TextView)view.findViewById(R.id.email);
        txtquantity=(TextView)view.findViewById(R.id.cartname);
        txtprice=(TextView)view.findViewById(R.id.allprice);
        number=(EditText)view.findViewById(R.id.number);
        cvc=(EditText)view.findViewById(R.id.cvc);
        address=(EditText)view.findViewById(R.id.address);
        final SharedPreferences sp= getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);

        final String username=sp.getString("User","");

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(CartService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);

        Call<List<Cart>> call = cartService.getCartItems(username);

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                Cart c = null;
                List<Cart> carts = response.body();
Register user=null;
                for (Cart cart:carts) {
        double prices= ( (cart.getProduct().getPrice())*(cart.getQuantity()) );
        price=price+prices;
        user=cart.getUser();
        quantity=quantity+cart.getQuantity();

        }
                txtprice.setText("Rs :" + String.valueOf(price));
                txtquantity.setText("Total Items :" + String.valueOf(quantity));


                txtname.setText( "Name   : "+ user.getFirstName());
                txtemail.setText("E-Mail : "+user.getEmailAddress());
                txtprice.setText("Rs : "+ String.valueOf(price));
                txtquantity.setText("Total Items : "+String.valueOf(quantity));

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });

      //  Register user = null;
      //  final List<Cart> car= Cart.findWithQuery(Cart.class, "Select * from Cart where user = ? and status = ? ", id.toString(),"Pending");

//




        buy=(Button)view.findViewById(R.id.pay);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cnumber=number.getText().toString();
                String cvcnumber=cvc.getText().toString();
                String addres=address.getText().toString();

                if(cnumber.isEmpty()||cvcnumber.isEmpty()||addres.isEmpty()){
                    Toast.makeText(getContext(), "Fill The Fields to Purchase Items", Toast.LENGTH_SHORT).show();
                }
                else{

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(UserService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    CartService cartService = retrofit.create(CartService.class);
                    Call<List<Cart>> nestedCall = cartService.getCartItems(sp.getString("User",""));

                    nestedCall.enqueue(new Callback<List<Cart>>() {
                        @Override
                        public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                            List<Cart> orderItems = response.body();
                            userCart=orderItems;
                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Register user = new Register();
                            user.setUsername(username);
                           //// user.setUid(uid);

                            Order order = new Order();
                            order.setComment("");
                            order.setOrder_date(date);
                            order.setOrderItems(orderItems);
                            order.setStatus("purchased");
                            order.setUser(user);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ProductService.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            CartService orderService = retrofit.create(CartService.class);

                            OrderItemWrapper orderItemWrapper = new OrderItemWrapper();
                            orderItemWrapper.setOrderItemList(userCart);

                            Call<Void> addAsOrder = orderService.orderConfirmed(username,order);

                            addAsOrder.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(ProductService.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    ProductService productsService = retrofit.create(ProductService.class);

                                    OrderItemWrapper orderItemWrapper = new OrderItemWrapper();
//                                    for (Cart u:userCart
//                                         ) {
//                                        u.setStatus("Purchased");
//                                    }
                                    orderItemWrapper.setOrderItemList(userCart);
                                    Call<Void> updateProducts;
                                    updateProducts = productsService.updateProducts(orderItemWrapper);
                                    updateProducts.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {

//                                                Toast.makeText(getActivity(), quantity+" Products Purchased!", Toast.LENGTH_LONG).show();
//                                                Fragment fragment= new PurchaseHistoryFragment();
//                                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                                ft.replace(R.id.fragment_container, fragment);
//                                                ft.commit();

                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable throwable) {
                                            System.out.println("asd");
                                        }
                                    });

//
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable throwable) {
                                    System.out.println("asd");
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<Cart>> call, Throwable throwable) {

                        }
                    });



                    //   for (Cart cart:car) {
//                        Purchase purchase=new Purchase();
//                        Calendar calendar = Calendar.getInstance();
//                        cart.setStatus("Purchased");
//                  //      cart.save();
//                        purchase.setCart(cart);
//                        purchase.setDate(date);
//                        purchase.setNumber(cnumber);
//                        purchase.setAddress(addres);
//                        purchase.save();
//                        List<Purchase> pp2=Purchase.listAll(Purchase.class);
//                        List<Purchase> pps2=Purchase.listAll(Purchase.class);
//                }



                }
                Toast.makeText(getContext(), "Purchase Successful", Toast.LENGTH_SHORT).show();

                Fragment fragment=new Home();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.aaa,fragment).commit();


            }
        });
        return  view;
    }

    }
