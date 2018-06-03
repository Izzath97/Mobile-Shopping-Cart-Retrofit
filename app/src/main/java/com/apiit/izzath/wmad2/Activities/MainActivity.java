package com.apiit.izzath.wmad2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apiit.izzath.wmad2.Models.Cart;
import com.apiit.izzath.wmad2.Models.Product;
import com.apiit.izzath.wmad2.Models.TagProduct;
import com.apiit.izzath.wmad2.Models.Tags;
import com.apiit.izzath.wmad2.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        if(Tags.listAll(Tags.class).isEmpty()){

            Gson gson1=new Gson();
            String tags="[{\"tagid\":\"1\",\"tagName\":\"White\"},{\"tagid\":\"2\",\"tagName\":\"Black\"},{\"tagid\":\"3\",\"tagName\":\"Blue\"},{\"tagid\":\"4\",\"tagName\":\"Red\"},{\"tagid\":\"5\",\"tagName\":\"Yellow\"},{\"tagid\":\"6\",\"tagName\":\"Purple\"},{\"tagid\":\"7\",\"tagName\":\"Pink\"},{\"tagid\":\"8\",\"tagName\":\"men\"},{\"tagid\":\"9\",\"tagName\":\"boy\"},{\"tagid\":\"10\",\"tagName\":\"women\"},{\"tagid\":\"11\",\"tagName\":\"watch\"},{\"tagid\":\"12\",\"tagName\":\"trouser\"},{\"tagid\":\"13\",\"tagName\":\"shirt\"},{\"tagid\":\"14\",\"tagName\":\"t-shirt\"},{\"tagid\":\"15\",\"tagName\":\"short\"},{\"tagid\":\"16\",\"tagName\":\"bikini\"},{\"tagid\":\"17\",\"tagName\":\"sari\"},{\"tagid\":\"18\",\"tagName\":\"v neck\"},{\"tagid\":\"19\",\"tagName\":\"round neck\"}]";
            Type lits=new TypeToken<List<Tags>>(){}.getType();
            List<Tags> tag=gson1.fromJson(tags,lits);
            Tags.saveInTx(tag);
        }
  if(Product.listAll(Product.class).isEmpty()){

    Gson gson=new Gson();

    String jso="[{\"Name\":\"T-Shirt\",\"ShortDescription\":\"V_Neck T_Shirt\",\"LongDescription\":\"Dazzle in style over the weekends in this blue polo striped T-shirt from Allen Solly. Tailored for a comfort fit, this design is crafted from cotton. Team this half sleeve tee with blue denims and sneakers for your Friday night outs.\",\"Catagory\":\"1\",\"Price\":750,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSSPfPlty2vYN66EJRAgQ9nSq_PcpmyRR3YtEFAli2fODUqYf2\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSSPfPlty2vYN66EJRAgQ9nSq_PcpmyRR3YtEFAli2fODUqYf2\",\"Tag\":[3,8,18]},{\"Name\":\"T-Shirt\",\"ShortDescription\":\"R_Neck T_Shirt\",\"LongDescription\":\"This blue crew-neck T-shirt from Allen Solly is a spunky pick for casual wear. Made from cotton, this printed design is tailored for a comfort fit. Sport this half sleeve tee with blue denims for a leisurely timeout with friends.\",\"Catagory\":\"1\",\"Price\":750,\"Quantity\":30,\"Active\":true,\"ScaledImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"FullImage\":\"http://www.freepngimg.com/download/tshirt/4-2-t-shirt-png-file.png\",\"Tag\":[3,8,19]},{\"Name\":\"Shirt\",\"ShortDescription\":\"Plain Shirt\",\"LongDescription\":\"Look smart and trendy in this blue denim shirt from Solly Jeans Co by Allen Solly. Crafted from pure cotton, this design is tailored for a slim fit and features a fade effect. Style this full sleeve piece with chinos and slip-ons for a Sunday brunch\",\"Catagory\":\"2\",\"Price\":1750,\"Quantity\":20,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdYvY9mHkKZwJDYXB52_bKC69prB0wdb4iniEtq_y-DWgLYamz0w\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdYvY9mHkKZwJDYXB52_bKC69prB0wdb4iniEtq_y-DWgLYamz0w\",\"Tag\":[3,8,13]},{\"Name\":\"Sari\",\"ShortDescription\":\"Blue Sari\",\"LongDescription\":\"Blue Design Sari\",\"Catagory\":\"1\",\"Price\":7500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://www.snipstock.com/thumb.php?src=https://www.snipstock.com/assets/cdn/png/1d8389d8931b86a7c54b6224d8dc0fa8.png&w=800\",\"FullImage\":\"https://www.snipstock.com/thumb.php?src=https://www.snipstock.com/assets/cdn/png/1d8389d8931b86a7c54b6224d8dc0fa8.png&w=800\",\"Tag\":[4,6,10,17]},{\"Name\":\"Shorts\",\"ShortDescription\":\"Denium Shorts\",\"LongDescription\":\"Leave a lasting impression on onlookers in these khaki cargo shorts from Solly Jeans Co by Allen Solly. Made from cotton stretch, these shorts can be paired with a trendy shirt and loafers.\",\"Catagory\":\"2\",\"Price\":1100,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://www.lockharttactical.com/media/com_hikashop/upload/sp01ft-01.png\",\"FullImage\":\"https://www.lockharttactical.com/media/com_hikashop/upload/sp01ft-01.png\",\"Tag\":[2,8,15]},{\"Name\":\"Sunglass\",\"ShortDescription\":\"Shades\",\"LongDescription\":\"Blue Shades\",\"Catagory\":\"1\",\"Price\":750,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3ByOrtqFOeJuK1fXroL_8bL8KipzP0PSSjjkPuquFEh4wQAEx\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3ByOrtqFOeJuK1fXroL_8bL8KipzP0PSSjjkPuquFEh4wQAEx\",\"Tag\":[3,8,13]},{\"Name\":\"Shalwar\",\"ShortDescription\":\"Shalwar with shawl\",\"LongDescription\":\"Blue Shalwar with shawl\",\"Catagory\":\"1\",\"Price\":5500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://commons.wikimedia.org/wiki/File:JPEG_example_JPG_RIP_100.jpg\",\"FullImage\":\"https://commons.wikimedia.org/wiki/File:JPEG_example_JPG_RIP_100.jpg\",\"Tag\":[3,8,13]},{\"Name\":\"Denium\",\"ShortDescription\":\"Pathch Denium\",\"LongDescription\":\"Lend a smart finish to your weekend ensemble with these dark-washed blue denims from Solly Jeans Co by Allen Solly. Tailored for a slim fit, this pair is crafted from a poly-cotton blend with a hint of stretch. Wear it with a white shirt and loafers to complete your ensemble.\",\"Catagory\":\"1\",\"Price\":2690,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR5e-ZjNAbMhqJKygjhFwzwmS0QErxPUnLfskdLAMtPNTeA8PAvg\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR5e-ZjNAbMhqJKygjhFwzwmS0QErxPUnLfskdLAMtPNTeA8PAvg\",\"Tag\":[3,8,9,12]},{\"Name\":\"Pijamas\",\"ShortDescription\":\"Nighty Pijamas\",\"LongDescription\":\"NIghty Pijama Kit\",\"Catagory\":\"1\",\"Price\":1500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9Xz755huiCs3ct_ZlRcEhDR8xT-ADpKwUan77JUINGlKN8HWL\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9Xz755huiCs3ct_ZlRcEhDR8xT-ADpKwUan77JUINGlKN8HWL\",\"Tag\":[7,10]},{\"Name\":\"Watch\",\"ShortDescription\":\"Mens Watch\",\"LongDescription\":\"Casual Mens Watch\",\"Catagory\":\"2\",\"Price\":4500,\"Quantity\":50,\"Active\":true,\"ScaledImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpU5-g7KBVJu44ifkl3-fcZEmHtgw17k1oB3tYurcuMVbIWQsfJA\",\"FullImage\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpU5-g7KBVJu44ifkl3-fcZEmHtgw17k1oB3tYurcuMVbIWQsfJA\",\"Tag\":[3,8,13]}]";


    Type listType = new TypeToken<List<Product>>() {}.getType();

    List<Product> products = gson.fromJson(jso ,listType);

    for (Product product: products ) {
        Product.saveInTx(products);
        for (String s:product.getTag()) {
            Tags tag=Tags.findById(Tags.class, Long.valueOf(s));
            TagProduct tagproduct=new TagProduct(product,s);
            tagproduct.save();

        }
    }






}
List<Cart> cc=Cart.listAll(Cart.class);
        List<Product> pp=Product.listAll(Product.class);
//else{}
        Intent intent=new Intent(MainActivity.this,login.class);
        startActivity(intent);
        finish();
    }


//int time=4000;

}
