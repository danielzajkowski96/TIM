package com.example.company.API.api;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiClient;
import com.example.company.API.ApiException;
import com.example.company.API.ApiResponse;
import com.example.company.API.Configuration;
import com.example.company.API.Pair;
import com.example.company.API.ProgressRequestBody;
import com.example.company.API.ProgressResponseBody;
import com.example.company.API.model.DTOSystemUser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthApi {
    private ApiClient apiClient;

    public AuthApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuthApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for authorize
     * @param login  (optional)
     * @param password  (optional)
     * @param firebaseToken  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call authorizeCall(String login, String password, String firebaseToken, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Auth";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (login != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("login", login));
        if (password != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("password", password));
        if (firebaseToken != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("firebaseToken", firebaseToken));

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call authorizeValidateBeforeCall(String login, String password, String firebaseToken, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {


        com.squareup.okhttp.Call call = authorizeCall(login, password, firebaseToken, progressListener, progressRequestListener);
        return call;

    }

    /**
     * User Authentication and Authorization
     * System SUPER USER CREDENTIALS  Login: SuperUser  Pass: aaa
     * @param login  (optional)
     * @param password  (optional)
     * @param firebaseToken  (optional)
     * @return DTOSystemUser
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DTOSystemUser authorize(String login, String password, String firebaseToken) throws ApiException {
        ApiResponse<DTOSystemUser> resp = authorizeWithHttpInfo(login, password, firebaseToken);
        return resp.getData();
    }

    /**
     * User Authentication and Authorization
     * System SUPER USER CREDENTIALS  Login: SuperUser  Pass: aaa
     * @param login  (optional)
     * @param password  (optional)
     * @param firebaseToken  (optional)
     * @return ApiResponse&lt;DTOSystemUser&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DTOSystemUser> authorizeWithHttpInfo(String login, String password, String firebaseToken) throws ApiException {
        com.squareup.okhttp.Call call = authorizeValidateBeforeCall(login, password, firebaseToken, null, null);
        Type localVarReturnType = new TypeToken<DTOSystemUser>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * User Authentication and Authorization (asynchronously)
     * System SUPER USER CREDENTIALS  Login: SuperUser  Pass: aaa
     * @param login  (optional)
     * @param password  (optional)
     * @param firebaseToken  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call authorizeAsync(String login, String password, String firebaseToken, final ApiCallback<DTOSystemUser> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = authorizeValidateBeforeCall(login, password, firebaseToken, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DTOSystemUser>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
