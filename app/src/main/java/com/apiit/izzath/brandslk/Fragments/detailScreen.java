package com.apiit.izzath.brandslk.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apiit.izzath.brandslk.Adapters.ReviewAdapter;
import com.apiit.izzath.brandslk.Activities.login;
import com.apiit.izzath.brandslk.Interface.CartService;
import com.apiit.izzath.brandslk.Interface.JsonPlaceHolderApi;
import com.apiit.izzath.brandslk.Interface.ReviewService;
import com.apiit.izzath.brandslk.Interface.UserService;
import com.apiit.izzath.brandslk.Models.Cart;
import com.apiit.izzath.brandslk.Models.Favorites;
import com.apiit.izzath.brandslk.Models.Product;
import com.apiit.izzath.brandslk.Models.Register;

import com.apiit.izzath.brandslk.Models.Retrofit;
import com.apiit.izzath.brandslk.Models.Reviews;
import com.apiit.izzath.brandslk.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class detailScreen extends Fragment {
    TextView testing, price, description, longDescription, quantityview;
    EditText quantity, comment;
    int id;
    int productids;
    int cartid;
    RatingBar rb;
    Button bcomment, cart;
    float value;
    Register user;
    Product products;
    ImageView img;
    ListView listview;
    Retrofit retrofit = new Retrofit();

    JsonPlaceHolderApi alplaService = retrofit.returnApi();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_product, container, false);
        final Bundle bundles = this.getArguments();

        final int productid1 = bundles.getInt("productid");
        id = productid1;
        img = (ImageView) view.findViewById(R.id.imageView7);
        Button wish = (Button) view.findViewById(R.id.wish);
        cart = (Button) view.findViewById(R.id.cart);
        Button share = (Button) view.findViewById(R.id.button2);
        testing = (TextView) view.findViewById(R.id.name);
        price = (TextView) view.findViewById(R.id.price);
        quantityview = (TextView) view.findViewById(R.id.qname);
        quantity = (EditText) view.findViewById(R.id.cartname);
        description = (TextView) view.findViewById(R.id.description);
        longDescription = (TextView) view.findViewById(R.id.longDescription);
        comment = (EditText) view.findViewById(R.id.comment);
        bcomment = (Button) view.findViewById(R.id.bcomment);
        rb = (RatingBar) view.findViewById(R.id.ratingBar);

        listview = (ListView) view.findViewById(R.id.reviewview);     // ListView For view Reviews

        listReviews();
        if (bundles != null) {
            products = returnProduct(productid1);
        }


        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                value = v;
            }
        });

        bcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String user = sp.getString("User", "");
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
                String comments = comment.getText().toString();
                addReviews(user, products, value, comments);
            }
        });


        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(ReviewService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String user = sp.getString("User", "");
                UserService userService = retrofit.create(UserService.class);
                Call<Register> call2 = userService.getUser(user);
                call2.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {

                        if (response.isSuccessful()) {
                        }
                        final Register users = response.body();
                        Favorites favorites = new Favorites(products, users);
                        addWishList(favorites);

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        System.out.println("Failed to Load Products");
                        call.cancel();
                    }
                });
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity.getText().toString().isEmpty()) {
                    quantity.setError("Enter Quantity to ADD TO Cart");
                } else {
                    SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                    final String user = sp.getString("User", "");

                    //  products=returnProduct(productid1);         //Product Object is returned
                    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(CartService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    CartService cartService = retrofit.create(CartService.class);

                    Call<List<Cart>> call = cartService.getCartItems(user);

                    call.enqueue(new Callback<List<Cart>>() {
                        @Override
                        public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                            Cart c = null;
                            List<Cart> cart1 = response.body();

                            for (Cart cart : cart1) {
                                if (cart.getProduct().getPid()==(products.getPid()) && cart.getRegister().getUsername().equals(user)&&cart.getStatus().equals("cart")) {

                                        productids = cart.getProduct().getPid();
                                        cartid = cart.getId();
                                        c = cart;

                                }
                            }
                            final int enterquantity = Integer.parseInt(quantity.getText().toString());
                            if (enterquantity <= products.getQuantity()) {
                                if (products.getPid() == (productids)) {
                                    Cart cartfind; //= Cart.findById(Cart.class, cartid);
                                    Toast.makeText(getContext(), "The Product Successfully Added to the Cart", Toast.LENGTH_SHORT).show();
                                    int quantitycart = c.getQuantity();
                                    int newQuantity = (quantitycart + enterquantity);
                                    updateCart(newQuantity, c, user);
//                        cartfind.setStatus("Pending");
////                        cartfind.setTotal((quantitycart + enterquantity)*products.getPrice());
////                        cartfind.save();
                                } else {
                                    addToCart(enterquantity, user);

//                        Cart item = new Cart(rg, products, sp.getLong("id", 10), enterquantity, "Pending",(enterquantity*products.getPrice()));
//                        item.save();
                                    Toast.makeText(getContext(), "The Product Successfully Added to the Cart", Toast.LENGTH_SHORT).show();
                                }

                                int updatequantity = ((products.getQuantity()) - (enterquantity));
                                products.setQuantity(updatequantity);
                                products.save();
                            } else {
                                quantity.setError("Invalid Quantity");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Cart>> call, Throwable t) {

                        }
                    });

                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product ll = returnProduct(productid1);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String sharing = ll.getScaledImage().toString();
                share.putExtra(Intent.EXTRA_SUBJECT, sharing);
                share.putExtra(Intent.EXTRA_TEXT, sharing);
                startActivity(Intent.createChooser(share, "Share Via"));
            }
        });
        return view;


    }

    public Product returnProduct(int id) {
        Call<Product> call = alplaService.getProduct(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {
                }
                final Product pp = response.body();
                products = pp;
                if (products.isActive() == false) {
                    cart.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                    cart.setEnabled(false);
                }
                price.setText(Double.toString(products.getPrice()));
                testing.setText(products.getName());
                description.setText(products.getShortDescription());
                longDescription.setText(products.getLongDescription());
                Picasso.get().load(products.getScaledImage()).into(img);
                quantityview.setText(Integer.toString(products.getQuantity()));
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(getContext());
                        dialog.setTitle("Purchase History Detail");
                        dialog.setContentView(R.layout.image);
                        ImageView date = (ImageView) dialog.findViewById(R.id.dimage);
                        Picasso.get().load(products.getScaledImage()).into(date);
                        dialog.show();
                    }
                });

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });
        return products;
    }

    public Register returnUser(String User) {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<Register> call2 = userService.getUser(User);
        call2.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                }
                final Register users = response.body();
                user = users;

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });
        return user;
    }


    public void addWishList(Favorites favorites) {
        Call<Favorites> call2 = alplaService.addWishList(favorites);
        call2.enqueue(new Callback<Favorites>() {
            @Override
            public void onResponse(Call<Favorites> call, Response<Favorites> response) {
                Toast.makeText(getContext(), "WishList ADDed", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Favorites> call, Throwable throwable) {
                Toast.makeText(getContext(), "WishList ADDed punda wesa", Toast.LENGTH_LONG).show();
            }
        });


    }


    public void listReviews() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ReviewService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReviewService reviewService = retrofit.create(ReviewService.class);

        Call<List<Reviews>> call = reviewService.getReview(id);

        call.enqueue(new Callback<List<Reviews>>() {
            @Override
            public void onResponse(Call<List<Reviews>> call, Response<List<Reviews>> response) {
                List<Reviews> reviews = response.body();
                final ReviewAdapter rv = new ReviewAdapter(getContext(), reviews);
                listview.setAdapter(rv);
            }

            @Override
            public void onFailure(Call<List<Reviews>> call, Throwable t) {

            }
        });

    }


    public void addReviews(final String user, final Product products, final float value, final String comments) {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ReviewService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<Register> call2 = userService.getUser(user);
        call2.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                }
                final Register users = response.body();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                Reviews reviews = new Reviews(users, products, value, comments, date);
                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(ReviewService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final ReviewService reviewService = retrofit.create(ReviewService.class);
                Call<Reviews> call2 = reviewService.addReview(reviews);
                call2.enqueue(new Callback<Reviews>() {
                    @Override
                    public void onResponse(Call<Reviews> call, Response<Reviews> response) {

                        listReviews();

                    }

                    @Override
                    public void onFailure(Call<Reviews> call, Throwable throwable) {
                        listReviews();
                    }
                });
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });

    }

    public void updateCart(int quantity, Cart cart, String username) { // method to update quantity

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(CartService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartService cartService = retrofit.create(CartService.class);

        cart.setQuantity(quantity);

        Call<Cart> call = cartService.updateCartItem(username, quantity, productids);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Toast.makeText(getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable throwable) {

            }
        });
    }

    public void addToCart(final int quantity, final String user) {

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<Register> call2 = userService.getUser(user);
        call2.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                if (response.isSuccessful()) {
                }
                final Register users = response.body();
                Cart orderItem = new Cart();
                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(ReviewService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CartService orderItemService = retrofit.create(CartService.class);

                Product product = new Product();
                product.setPid(productids);


                orderItem.setStatus("cart");
                orderItem.setQuantity(quantity);
                orderItem.setProduct(products);
                orderItem.setUser(users);
                Call<Cart> calls = orderItemService.addToCart(orderItem);

                calls.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        Toast.makeText(getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable throwable) {


                    }
                });

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println("Failed to Load Products");
                call.cancel();
            }
        });


    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mContext = context;
//    }

}




