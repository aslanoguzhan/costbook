package com.example.costbook.retrofit;

import com.example.costbook.pojo.AppUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CostPost {
    @Headers("Content-Type: application/json")
    @POST("/cost/saveCost")
    Call<String> getSaveCost(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/garden/saveGarden")
    Call<String> getSaveGarden(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/garden/saveGardenproduct")
    Call<String> saveGardenproduct(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/garden/updatemygarden")
    Call<Void> updateGarden(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/garden/deletegarden")
    Call<Void> deleteGarden(@Query("gardenID") int gardenID,@Query("userID") int userID);

    @Headers("Content-Type: application/json")
    @POST("/product/updateproduction")
    Call<String> updateproduct(String s, @Query("productID") int productID, @Query("totalProduction") int totalProduction);

    @Headers("Content-Type: application/json")
    @POST("/cost/deleteCost")
    Call<Long> deleteCost(@Query("costId") long costId);

    @Headers("Content-Type: application/json")
    @POST("/product/saveProduct")
    Call<String> getSaveProduct(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/cost/deleteProduct")
    Call<Long> deleteProduct(@Query("productId") long productId);

    @Headers("Content-Type:application/json")
    @POST("/user/insertuser")
    Call<Object> insertUser(@Body AppUser users);

    @Headers("Content-Type: application/json")
    @POST("/user/changeusername")
    Call<String> changeusername(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("/user/listcosts")
    Call<List<AppUser>> listAllCosts(@Body AppUser users);



}
