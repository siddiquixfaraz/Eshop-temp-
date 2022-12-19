package com.example.e_shop.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_shop.R;
import com.example.e_shop.adapters.CategoryAdapter;
import com.example.e_shop.adapters.ProductAdapter;
import com.example.e_shop.databinding.ActivityMainBinding;
import com.example.e_shop.model.Category;
import com.example.e_shop.model.Product;
import com.example.e_shop.utility.Constants;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories ;

    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query", text.toString());
                startActivity(intent);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        initCategory();
        initProducts();
        initSlider();

    }

    private void initSlider() {
//        binding.carousel.addData(new CarouselItem(
//                "https://tutorials.mianasad.com/ecommerce/uploads/news/Genuine%20Leather.jpg",""));
//        binding.carousel.addData(new CarouselItem(
//                "https://tutorials.mianasad.com/ecommerce/uploads/news/Handicraft%20Brass%20Items.jpg",""));
//        binding.carousel.addData(new CarouselItem(
//                "https://tutorials.mianasad.com/ecommerce/uploads/news/Gems%20and%20Jewellery.jpg",""));
//        binding.carousel.addData(new CarouselItem(
//                "https://static.vecteezy.com/system/resources/previews/000/205/356/original/vector-diwali-festival-offers-voucher-banner-design-background.jpg",""));
        getRecentOffers();
    }

    void initCategory(){
        categories = new ArrayList<>();
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666764677958.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666757988573.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666757988573.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666757988573.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666764677958.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666763480942.png","#9A3838","gaghsb",1));
//        categories.add(new Category("Sports","https://tutorials.mianasad.com/ecommerce/uploads/category/1666760883041.png","#9A3838","gaghsb",1));
        categoryAdapter = new CategoryAdapter(this,categories);

        getCategory();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void getCategory(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    if(mainObj.getString("status").equals("success")){
                        JSONArray categoriesArray = mainObj.getJSONArray("categories");
                        for (int i = 0; i<categoriesArray.length(); i++){
                            JSONObject object = categoriesArray.getJSONObject(i);
                            Category category = new Category(
                                    object.getString("name"),
                                   Constants.CATEGORIES_IMAGE_URL+ object.getString("icon"),
                                    object.getString("color"),
                                    object.getString("brief"),
                                    object.getInt("id")
                            );
                            categories.add(category);
                        }
                        categoryAdapter.notifyDataSetChanged();

                    }else {
                        //do nothing
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    void initProducts(){
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(this,products);

        getProducts();

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);

    }

    void  getProducts(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url =  Constants.GET_PRODUCTS_URL ;
        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj = new JSONObject(response);
                    if(mainObj.getString("status").equals("success")){
                        JSONArray productsArray = mainObj.getJSONArray("products");
                        for (int i=0; i<productsArray.length();i++){
                            JSONObject object = productsArray.getJSONObject(i);
                            Product product = new Product(
                                    object.getString("name"),
                                   Constants.PRODUCTS_IMAGE_URL+ object.getString("image"),
                                    object.getString("status"),
                                    object.getDouble("price"),
                                    object.getDouble("price_discount"),
                                    object.getInt("stock"),
                                    object.getInt("id")
                            );
                            products.add(product);
                        }

                        productAdapter.notifyDataSetChanged();

                    }else {
                        //do nothing
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    void getRecentOffers() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if(object.getString("status").equals("success")) {
                    JSONArray offerArray = object.getJSONArray("news_infos");
                    for(int i =0; i < offerArray.length(); i++) {
                        JSONObject childObj =  offerArray.getJSONObject(i);
                        binding.carousel.addData(
                                new CarouselItem(
                                        Constants.NEWS_IMAGE_URL + childObj.getString("image"),
                                        childObj.getString("title")
                                )
                        );
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {});
        queue.add(request);
    }

}
