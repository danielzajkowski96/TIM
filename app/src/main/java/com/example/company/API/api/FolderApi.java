package com.example.company.API.api;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiClient;
import com.example.company.API.ApiException;
import com.example.company.API.ApiResponse;
import com.example.company.API.Configuration;
import com.example.company.API.Pair;
import com.example.company.API.ProgressRequestBody;
import com.example.company.API.ProgressResponseBody;
import com.example.company.API.model.DTOCreateFolder;
import com.example.company.API.model.DTOFolderContent;
import com.example.company.API.model.Katalog;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FolderApi {
    private ApiClient apiClient;

    public FolderApi() {
        this(Configuration.getDefaultApiClient());
    }

    public FolderApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for createFolder
     * @param form Create folder form (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call createFolderCall(DTOCreateFolder form, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = form;

        // create path and map variables
        String localVarPath = "/api/Folder";

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call createFolderValidateBeforeCall(DTOCreateFolder form, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {


        com.squareup.okhttp.Call call = createFolderCall(form, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Create new folder
     *
     * @param form Create folder form (optional)
     * @return Katalog
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Katalog createFolder(DTOCreateFolder form) throws ApiException {
        ApiResponse<Katalog> resp = createFolderWithHttpInfo(form);
        return resp.getData();
    }

    /**
     * Create new folder
     *
     * @param form Create folder form (optional)
     * @return ApiResponse&lt;Katalog&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Katalog> createFolderWithHttpInfo(DTOCreateFolder form) throws ApiException {
        com.squareup.okhttp.Call call = createFolderValidateBeforeCall(form, null, null);
        Type localVarReturnType = new TypeToken<Katalog>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create new folder (asynchronously)
     *
     * @param form Create folder form (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call createFolderAsync(DTOCreateFolder form, final ApiCallback<Katalog> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = createFolderValidateBeforeCall(form, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Katalog>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for deleteFolder
     * @param folderId Folder ID (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call deleteFolderCall(Integer folderId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Folder/{folderId}"
                .replaceAll("\\{" + "folderId" + "\\}", apiClient.escapeString(folderId.toString()));

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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call deleteFolderValidateBeforeCall(Integer folderId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'folderId' is set
        if (folderId == null) {
            throw new ApiException("Missing the required parameter 'folderId' when calling deleteFolder(Async)");
        }


        com.squareup.okhttp.Call call = deleteFolderCall(folderId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Delte folder
     *
     * @param folderId Folder ID (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteFolder(Integer folderId) throws ApiException {
        deleteFolderWithHttpInfo(folderId);
    }

    /**
     * Delte folder
     *
     * @param folderId Folder ID (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteFolderWithHttpInfo(Integer folderId) throws ApiException {
        com.squareup.okhttp.Call call = deleteFolderValidateBeforeCall(folderId, null, null);
        return apiClient.execute(call);
    }

    /**
     * Delte folder (asynchronously)
     *
     * @param folderId Folder ID (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteFolderAsync(Integer folderId, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = deleteFolderValidateBeforeCall(folderId, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for getCompanyFolders
     * @param companyId company ID (required)
     * @param rootFolder containing folder if any (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getCompanyFoldersCall(Integer companyId, Integer rootFolder, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Folder/{companyId}"
                .replaceAll("\\{" + "companyId" + "\\}", apiClient.escapeString(companyId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (rootFolder != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("rootFolder", rootFolder));

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
    private com.squareup.okhttp.Call getCompanyFoldersValidateBeforeCall(Integer companyId, Integer rootFolder, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'companyId' is set
        if (companyId == null) {
            throw new ApiException("Missing the required parameter 'companyId' when calling getCompanyFolders(Async)");
        }


        com.squareup.okhttp.Call call = getCompanyFoldersCall(companyId, rootFolder, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get company group subfolders and files
     *
     * @param companyId company ID (required)
     * @param rootFolder containing folder if any (optional)
     * @return DTOFolderContent
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DTOFolderContent getCompanyFolders(Integer companyId, Integer rootFolder) throws ApiException {
        ApiResponse<DTOFolderContent> resp = getCompanyFoldersWithHttpInfo(companyId, rootFolder);
        return resp.getData();
    }

    /**
     * Get company group subfolders and files
     *
     * @param companyId company ID (required)
     * @param rootFolder containing folder if any (optional)
     * @return ApiResponse&lt;DTOFolderContent&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DTOFolderContent> getCompanyFoldersWithHttpInfo(Integer companyId, Integer rootFolder) throws ApiException {
        com.squareup.okhttp.Call call = getCompanyFoldersValidateBeforeCall(companyId, rootFolder, null, null);
        Type localVarReturnType = new TypeToken<DTOFolderContent>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get company group subfolders and files (asynchronously)
     *
     * @param companyId company ID (required)
     * @param rootFolder containing folder if any (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getCompanyFoldersAsync(Integer companyId, Integer rootFolder, final ApiCallback<DTOFolderContent> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getCompanyFoldersValidateBeforeCall(companyId, rootFolder, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DTOFolderContent>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getOtherFolders
     * @param companyId company ID (required)
     * @param platoonId platoon ID (optional)
     * @param rootFolder containing folder if any (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getOtherFoldersCall(Integer companyId, Integer platoonId, Integer rootFolder, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Folder";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (companyId != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("companyId", companyId));
        if (platoonId != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("platoonId", platoonId));
        if (rootFolder != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("rootFolder", rootFolder));

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
    private com.squareup.okhttp.Call getOtherFoldersValidateBeforeCall(Integer companyId, Integer platoonId, Integer rootFolder, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'companyId' is set
        if (companyId == null) {
            throw new ApiException("Missing the required parameter 'companyId' when calling getOtherFolders(Async)");
        }


        com.squareup.okhttp.Call call = getOtherFoldersCall(companyId, platoonId, rootFolder, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get other folders
     *
     * @param companyId company ID (required)
     * @param platoonId platoon ID (optional)
     * @param rootFolder containing folder if any (optional)
     * @return List&lt;Katalog&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<Katalog> getOtherFolders(Integer companyId, Integer platoonId, Integer rootFolder) throws ApiException {
        ApiResponse<List<Katalog>> resp = getOtherFoldersWithHttpInfo(companyId, platoonId, rootFolder);
        return resp.getData();
    }

    /**
     * Get other folders
     *
     * @param companyId company ID (required)
     * @param platoonId platoon ID (optional)
     * @param rootFolder containing folder if any (optional)
     * @return ApiResponse&lt;List&lt;Katalog&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<Katalog>> getOtherFoldersWithHttpInfo(Integer companyId, Integer platoonId, Integer rootFolder) throws ApiException {
        com.squareup.okhttp.Call call = getOtherFoldersValidateBeforeCall(companyId, platoonId, rootFolder, null, null);
        Type localVarReturnType = new TypeToken<List<Katalog>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get other folders (asynchronously)
     *
     * @param companyId company ID (required)
     * @param platoonId platoon ID (optional)
     * @param rootFolder containing folder if any (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getOtherFoldersAsync(Integer companyId, Integer platoonId, Integer rootFolder, final ApiCallback<List<Katalog>> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getOtherFoldersValidateBeforeCall(companyId, platoonId, rootFolder, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<Katalog>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for updateFolderName
     * @param folderId Folder ID (required)
     * @param newName New name for a folder (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call updateFolderNameCall(Integer folderId, String newName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/Folder/{folderId}"
                .replaceAll("\\{" + "folderId" + "\\}", apiClient.escapeString(folderId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (newName != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("newName", newName));

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
        return apiClient.buildCall(localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call updateFolderNameValidateBeforeCall(Integer folderId, String newName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'folderId' is set
        if (folderId == null) {
            throw new ApiException("Missing the required parameter 'folderId' when calling updateFolderName(Async)");
        }

        // verify the required parameter 'newName' is set
        if (newName == null) {
            throw new ApiException("Missing the required parameter 'newName' when calling updateFolderName(Async)");
        }


        com.squareup.okhttp.Call call = updateFolderNameCall(folderId, newName, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Rename folder
     *
     * @param folderId Folder ID (required)
     * @param newName New name for a folder (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void updateFolderName(Integer folderId, String newName) throws ApiException {
        updateFolderNameWithHttpInfo(folderId, newName);
    }

    /**
     * Rename folder
     *
     * @param folderId Folder ID (required)
     * @param newName New name for a folder (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> updateFolderNameWithHttpInfo(Integer folderId, String newName) throws ApiException {
        com.squareup.okhttp.Call call = updateFolderNameValidateBeforeCall(folderId, newName, null, null);
        return apiClient.execute(call);
    }

    /**
     * Rename folder (asynchronously)
     *
     * @param folderId Folder ID (required)
     * @param newName New name for a folder (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call updateFolderNameAsync(Integer folderId, String newName, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = updateFolderNameValidateBeforeCall(folderId, newName, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
