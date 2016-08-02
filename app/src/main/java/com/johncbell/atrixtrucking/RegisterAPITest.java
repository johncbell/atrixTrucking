package com.johncbell.atrixtrucking;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Belal and johncbell on 04/25/2016.
 */
public interface RegisterAPITest {
    @FormUrlEncoded
    @POST("/insert_test_atrix.php")
    public void insertUser(
            @Field("trucksID") String trucksID,
            @Field("tripReportNumber") String tripReportNumber,
            @Field("enteredDate") String enteredDate,
            @Field("emptyMilage") String emptyMilage,
            @Field("loadedMilage") String loadedMilage,
            @Field("estMilage") String estMilage,
            @Field("startState") String startState,
            @Field("startTerminal") String startTerminal,
            @Field("endState") String endState,
            @Field("endTerminal") String endTerminal,
            Callback<Response> callback);
}