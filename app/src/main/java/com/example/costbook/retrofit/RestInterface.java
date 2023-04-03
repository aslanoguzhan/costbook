package com.example.costbook.retrofit;


import com.example.costbook.pojo.AppUser;
import com.example.costbook.pojo.Costs;
import com.example.costbook.pojo.CustomUser;
import com.example.costbook.pojo.Gardens;
import com.example.costbook.pojo.MounthCosts;
import com.example.costbook.pojo.MyCost;
import com.example.costbook.pojo.MyGarden;
import com.example.costbook.pojo.MyProduct;
import com.example.costbook.pojo.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestInterface {


    @GET("/user/userinfo")
    Call<Void> userinfo(@Query("userID") int userID);

    @GET("/user/getAllmygarden")
    Call<List<MyGarden>> getAllmygarden(@Query("userID") int userID);

    @GET("/user/getAllmyproduct")
    Call<List<MyProduct>> getAllmyproduct(@Query("userID") int userID);



    @GET("/user/getAllmycost")
    Call<List<MyCost>> getAllmycost(@Query("userID") int userID);


    @GET("/cost/totalcost")
    Call<Object> getAllCost(@Query("userID") int userID);


    @GET("/cost/mounhtcost")
    Call<List<MounthCosts>> getMountcost(@Query("productID") int productID, @Query("gardenID") int gardenID);

    @GET("/cost/mounhtfuelcost")
    Call<Object> getMounthFuelcost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/garden/getAllgarden")
    Call<List<Gardens>> getGarden(@Query("userID") int userID);

    @GET("/garden/gardenlist")
    Call<List<Gardens>>getMygarden(@Query("userID") int userID);

    @GET("/garden/gardenlistproduct")
    Call<List<Products>>getGardensproduct(@Query("gardenID") int gardenID, @Query("userID") int userID);


    @GET("/product/allProducts")
    Call<Object> getAllProduct(@Query("gardenID") int gardenID);

    @GET("/product/listallproduct")
    Call<List<Products>> listAllproduct();

    @GET("/product/allCategoryproduct")
    Call<Object> getCategoryProduct(@Query("productID") int productID,@Query("gardenID") int gardenID);


    @GET("/product/productpercost")
    Call<Double> getUnitCost(@Query("productID") int productID,@Query("gardenID") int gardenID);


    @GET("/user/checkstandard")
    Call<CustomUser> checkStandard(@Query("email") String email, @Query("password") String password);

    @GET("/user/checkusercode")
    Call<Void> checkusercode(@Query("email") String email,@Query("code") String code);

    @GET("/user/getUsercosts")
    Call<List<AppUser>> getAllCosts(@Query("page") int page);

    @GET("/user/getCategorizecosts")
    Call<List<AppUser>> getAllCategorizes(@Query("page") int page, String message);

    @GET("/user/getcategoryinfo")
    Call<List<Costs>> getcategoryinfo(@Query("email") String email, @Query("password") String password);


    @GET("/cost/categorycost")
    Call<Object> CategoryCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    ////kategori bazlı masraf interface////////////////////////////////

    @GET("/cost/machinecost")
    Call<Object> getMachineCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/fuelcost")
    Call<Object> getFuelCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/seedcost")
    Call<Object> getSeedCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/irrigationcost")
    Call<Object> getİrrigationCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/organicfertilizerscost")
    Call<Object> getOrganicfertilizersCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/laborforcecost")
    Call<Object> getLaborforceCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/chemicalfertilizerscost")
    Call<Object> getChemicalfertilizersCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/cost/presticidecost")
    Call<Object> getPresticideCost(@Query("productID") int productID,@Query("gardenID") int gardenID);

    @GET("/user/resetpassword")
    Call<Void> resetpassword(@Query("email") String email);

    @GET("/user/setpassword")
    Call<Void> setpassword(@Query("email") String email,@Query("token") String token,@Query("newpw") String newpw);

    @GET("/garden/listGardenproduct")
    Call<ArrayList<Gardens>>getProducts();

    @Headers("Content-Type:application/json")
    @POST("/user/insertuser")
    Call<Void> insertUser(@Body AppUser user);

}
