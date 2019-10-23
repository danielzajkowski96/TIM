package com.example.company.API.api;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiClient;
import com.example.company.API.ApiException;
import com.example.company.API.ApiResponse;
import com.example.company.API.Configuration;
import com.example.company.API.Pair;
import com.example.company.API.ProgressRequestBody;
import com.example.company.API.ProgressResponseBody;
import com.example.company.API.model.Zolnierz;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoldierApi {
    private ApiClient apiClient;

    public SoldierApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SoldierApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getSoldierDetails
     * @param soldierId Soldier ID (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getSoldierDetailsCall(Integer soldierId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Soldier/{soldierId}"
            .replaceAll("\\{" + "soldierId" + "\\}", apiClient.escapeString(soldierId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "Bearer" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getSoldierDetailsValidateBeforeCall(Integer soldierId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'soldierId' is set
        if (soldierId == null) {
            throw new ApiException("Missing the required parameter 'soldierId' when calling getSoldierDetails(Async)");
        }


        com.squareup.okhttp.Call call = getSoldierDetailsCall(soldierId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get detailed information about the soldier
     *
     * @param soldierId Soldier ID (required)
     * @return Zolnierz
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Zolnierz getSoldierDetails(Integer soldierId) throws ApiException {
        ApiResponse<Zolnierz> resp = getSoldierDetailsWithHttpInfo(soldierId);
        return resp.getData();
    }

    /**
     * Get detailed information about the soldier
     *
     * @param soldierId Soldier ID (required)
     * @return ApiResponse&lt;Zolnierz&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Zolnierz> getSoldierDetailsWithHttpInfo(Integer soldierId) throws ApiException {
        com.squareup.okhttp.Call call = getSoldierDetailsValidateBeforeCall(soldierId, null, null);
        Type localVarReturnType = new TypeToken<Zolnierz>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get detailed information about the soldier (asynchronously)
     *
     * @param soldierId Soldier ID (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getSoldierDetailsAsync(Integer soldierId, final ApiCallback<Zolnierz> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getSoldierDetailsValidateBeforeCall(soldierId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Zolnierz>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
