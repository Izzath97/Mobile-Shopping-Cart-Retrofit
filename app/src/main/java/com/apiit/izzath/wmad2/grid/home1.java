package com.apiit.izzath.wmad2.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class home1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home1);
     // List<Product> product = Product.listAll(Product.class);

     //  Product.deleteAll(Product.class);
        Gson gson=new Gson();

        String jsoni="[{\"Id\":1,\"Name\":\"T-Shirt\",\"ShortDescription\":\"V_Neck T_Shirt\",\"LongDescription\":\"Blue V-Neck T-Shirt\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":750,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSSPfPlty2vYN66EJRAgQ9nSq_PcpmyRR3YtEFAli2fODUqYf2\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSSPfPlty2vYN66EJRAgQ9nSq_PcpmyRR3YtEFAli2fODUqYf2\"},{\"Id\":2,\"Name\":\"T-Shirt\",\"ShortDescription\":\"R_Neck T_Shirt\",\"LongDescription\":\"Black R-Neck T-Shirt\",\"Catagory\":1,\"Sub-Catogary\":\"Shirt\",\"Price\":750,\"Quantity\":30,\"Active\":true,\"ScaledImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"FullImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\"},{\"Id\":3,\"Name\":\"Shirt\",\"ShortDescription\":\"Plain Shirt\",\"LongDescription\":\"Plain L/S Shirt\",\"Catagory\":2,\"Sub-Catogary\":\"Shirt\",\"Price\":1750,\"Quantity\":20,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdYvY9mHkKZwJDYXB52_bKC69prB0wdb4iniEtq_y-DWgLYamz0w\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdYvY9mHkKZwJDYXB52_bKC69prB0wdb4iniEtq_y-DWgLYamz0w\"},{\"Id\":4,\"Name\":\"Sari\",\"ShortDescription\":\"Blue Sari\",\"LongDescription\":\"Blue Design Sari\",\"Catagory\":1,\"Sub-Catogary\":\"Sari\",\"Price\":7500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://www.snipstock.com/thumb.php?src=https://www.snipstock.com/assets/cdn/png/1d8389d8931b86a7c54b6224d8dc0fa8.png&w=800\",\"FullImage\":\"https://www.snipstock.com/thumb.php?src=https://www.snipstock.com/assets/cdn/png/1d8389d8931b86a7c54b6224d8dc0fa8.png&w=800\"},{\"Id\":5,\"Name\":\"Shorts\",\"ShortDescription\":\"Denium Shorts\",\"LongDescription\":\"Blue Denim Shorts\",\"Catagory\":1,\"Sub-Catogary\":\"Short\",\"Price\":1100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://www.lockharttactical.com/media/com_hikashop/upload/sp01ft-01.png\",\"FullImage\":\"https://www.lockharttactical.com/media/com_hikashop/upload/sp01ft-01.png\"},{\"Id\":6,\"Name\":\"Sunglass\",\"ShortDescription\":\"Shades\",\"LongDescription\":\"Blue Shades\",\"Catagory\":1,\"Sub-Catogary\":\"Other\",\"Price\":750,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3ByOrtqFOeJuK1fXroL_8bL8KipzP0PSSjjkPuquFEh4wQAEx\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3ByOrtqFOeJuK1fXroL_8bL8KipzP0PSSjjkPuquFEh4wQAEx\"},{\"Id\":7,\"Name\":\"Shalwar\",\"ShortDescription\":\"Shalwar with shawl\",\"LongDescription\":\"Blue Shalwar with shawl\",\"Catagory\":1,\"Sub-Catogary\":\"Shalwar\",\"Price\":5500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://i.pinimg.com/originals/76/b8/10/76b8106ba0f1307fa98532e0a6afd52c.png\",\"FullImage\":\"https://i.pinimg.com/originals/76/b8/10/76b8106ba0f1307fa98532e0a6afd52c.png\"},{\"Id\":8,\"Name\":\"Denium\",\"ShortDescription\":\"Pathch Denium\",\"LongDescription\":\"Blue Pathch Denium\",\"Catagory\":1,\"Sub-Catogary\":\"Denium\",\"Price\":2690,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR5e-ZjNAbMhqJKygjhFwzwmS0QErxPUnLfskdLAMtPNTeA8PAvg\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR5e-ZjNAbMhqJKygjhFwzwmS0QErxPUnLfskdLAMtPNTeA8PAvg\"},{\"Id\":9,\"Name\":\"Pijamas\",\"ShortDescription\":\"Nighty Pijamas\",\"LongDescription\":\"NIghty Pijama Kit\",\"Catagory\":1,\"Sub-Catogary\":\"Pijama\",\"Price\":1500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9Xz755huiCs3ct_ZlRcEhDR8xT-ADpKwUan77JUINGlKN8HWL\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9Xz755huiCs3ct_ZlRcEhDR8xT-ADpKwUan77JUINGlKN8HWL\"},{\"Id\":10,\"Name\":\"Watch\",\"ShortDescription\":\"Mens Watch\",\"LongDescription\":\"Casual Mens Watch\",\"Catagory\":1,\"Sub-Catogary\":\"Watch\",\"Price\":4500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpU5-g7KBVJu44ifkl3-fcZEmHtgw17k1oB3tYurcuMVbIWQsfJA\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpU5-g7KBVJu44ifkl3-fcZEmHtgw17k1oB3tYurcuMVbIWQsfJA\"}]";


        Type listType = new TypeToken<List<Product>>() {}.getType();
        final List<Product> products = gson.fromJson(jsoni ,listType);
        Product.saveInTx(products);
        GridView gridview = (GridView) findViewById(R.id.gridview1);
        gridview.setAdapter(new Adapter1(products,this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(home1.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
