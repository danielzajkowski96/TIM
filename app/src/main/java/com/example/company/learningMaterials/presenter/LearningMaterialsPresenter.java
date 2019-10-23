package com.example.company.learningMaterials.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import com.example.company.API.ApiCallback;
import com.example.company.API.ApiException;
import com.example.company.API.api.FileApi;
import com.example.company.API.api.FolderApi;
import com.example.company.API.model.DTOCreateFolder;
import com.example.company.API.model.DTOFolderContent;
import com.example.company.API.model.Katalog;
import com.example.company.API.model.Plik;
import com.example.company.BuildConfig;
import com.example.company.R;
import com.example.company.authentication.model.AuthenticationUser;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.learningMaterials.interfaces.LearningMaterialsInterface;
import com.example.company.learningMaterials.model.Item;
import com.example.company.snackbar.SnackbarPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class LearningMaterialsPresenter implements LearningMaterialsInterface.Presenter {

    private static LearningMaterialsPresenter learningMaterialsPresenter;

    private LearningMaterialsInterface.View learningMaterialsView;
    private Context context;

    private FolderApi folderApi;
    private FileApi fileApi;
    private AuthenticationPresenter authenticationPresenter;

    private DTOFolderContent allCompanyFolders;
    private Item[] items;

    private Integer currentFolderID;
    private Integer currentFolderParentFolderID;

    private List<Item> listItemToDelete;

    private boolean accessForModifyLearningMaterials = false;

    private LearningMaterialsPresenter() {
        folderApi = new FolderApi();
        fileApi = new FileApi();
        allCompanyFolders = new DTOFolderContent();
        listItemToDelete = new ArrayList<>();
    }

    public static LearningMaterialsPresenter getInstance() {
        if (learningMaterialsPresenter == null) {
            learningMaterialsPresenter = new LearningMaterialsPresenter();
        }
        return learningMaterialsPresenter;
    }

    @Override
    public void setView(LearningMaterialsInterface.View view) {
        learningMaterialsView = view;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void getCompanyFoldersFromRoot() {
        setCurrentFolderID(null);
        learningMaterialsView.setVisibleListViewProgressBar(true);

        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {
            try {
                if (authenticationUser.getCompanyNumber() != null) {
                    folderApi.getCompanyFoldersAsync(authenticationUser.getCompanyNumber(), null, new ApiCallback<DTOFolderContent>() {
                        @Override
                        public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                        }

                        @Override
                        public void onSuccess(DTOFolderContent result, int statusCode, Map<String, List<String>> responseHeaders) {
                            setItems(result);
                            learningMaterialsView.showAllCompanyFolders();
                            learningMaterialsView.setVisibleUpToRootFolder(false);
                            learningMaterialsView.setVisibleUpToRootFolder(false);
                            setCurrentFolderParentFolderID(null);
                            learningMaterialsView.setVisibleListViewProgressBar(false);
                        }

                        @Override
                        public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                        }

                        @Override
                        public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                        }
                    });
                }

            } catch (ApiException e) {
                e.printStackTrace();
                learningMaterialsView.setVisibleListViewProgressBar(false);
            }
        } else {
            authenticationPresenter.logOut();
        }

    }

    @Override
    public void getCompanyFoldersFromChosenFolder(final int chosenFolder) {
        setCurrentFolderID(chosenFolder);
        learningMaterialsView.setVisibleListViewProgressBar(true);

        authenticationPresenter = AuthenticationPresenter.getInstance();
        final AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {
            try {
                folderApi.getCompanyFoldersAsync(authenticationUser.getCompanyNumber(), chosenFolder, new ApiCallback<DTOFolderContent>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        learningMaterialsView.setVisibleListViewProgressBar(false);
                    }

                    @Override
                    public void onSuccess(DTOFolderContent result, int statusCode, Map<String, List<String>> responseHeaders) {
                        setItems(result);
//                        setCurrentFolderParentFolderID(chosenFolder);
                        learningMaterialsView.setEnableUpToRootFolder(true);
                        learningMaterialsView.setVisibleUpToRootFolder(true);
                        learningMaterialsView.showAllCompanyFolders();

                        learningMaterialsView.setVisibleListViewProgressBar(false);
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
                learningMaterialsView.setVisibleListViewProgressBar(false);
            }
        } else {
            authenticationPresenter.logOut();
        }

    }

    @Override
    public void createFolder(String name) {
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {

            DTOCreateFolder dtoCreateFolder = new DTOCreateFolder();
            dtoCreateFolder.setCompanyId(authenticationUser.getCompanyNumber());
            dtoCreateFolder.setName(name);
            dtoCreateFolder.setRootFolderId(getCurrentFolderID());
            try {
                folderApi.createFolderAsync(dtoCreateFolder, new ApiCallback<Katalog>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                    }

                    @Override
                    public void onSuccess(Katalog result, int statusCode, Map<String, List<String>> responseHeaders) {
                        SnackbarPresenter.getInstance().showSnackbar(context.getResources().getString(R.string.title_dialog_create_new_folder_success_message));

                        learningMaterialsView.showCreatedFolderSuccessSnackbar();

                        if (getCurrentFolderID() != null) {
                            getCompanyFoldersFromChosenFolder(getCurrentFolderID());
                        } else {
                            getCompanyFoldersFromRoot();
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } else {
            authenticationPresenter.logOut();
        }
    }

    @Override
    public void uploadFile(File file) {
        SnackbarPresenter.getInstance().showSnackbar(context.getResources().getString(R.string.title_dialog_upload_start_message));
        try {
            fileApi.uploadFileAsyncAsync(getCurrentFolderID(), null, file, new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    SnackbarPresenter.getInstance().showSnackbar(context.getResources().getString(R.string.title_dialog_upload_success_message));
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                    learningMaterialsView.setVisibleMainActivityUploadDownloadProgressBar(!done);
                    learningMaterialsView.setMainActivityUploadDownloadProgressBarValue((int) (bytesWritten / contentLength) * 100);
                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(String fileId, final String extension) {
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();

        if (authenticationUser != null && authenticationUser.getUserId() != null) {
            try {
                fileApi.getFileAsync(fileId, new ApiCallback<File>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                    }

                    @Override
                    public void onSuccess(File result, int statusCode, Map<String, List<String>> responseHeaders) {
                        openFile(result, extension);
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                        learningMaterialsView.setVisibleMainActivityUploadDownloadProgressBar(!done);
                        learningMaterialsView.setMainActivityUploadDownloadProgressBarValue((int) (bytesRead / contentLength) * 100);
                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } else {
            authenticationPresenter.logOut();
        }
    }

    @Override
    public void deleteFile(String fileId) {
        try {
            fileApi.deleteFileAsyncAsync(fileId, new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    learningMaterialsView.showDeletedSuccessSnackbar();

                    if (getCurrentFolderID() != null) {
                        getCompanyFoldersFromChosenFolder(getCurrentFolderID());
                    } else {
                        getCompanyFoldersFromRoot();
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFolder(Integer folderID) {
        try {
            folderApi.deleteFolderAsync(folderID, new ApiCallback<Void>() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {

                }

                @Override
                public void onSuccess(Void result, int statusCode, Map<String, List<String>> responseHeaders) {
                    learningMaterialsView.showDeletedSuccessSnackbar();

                    if (getCurrentFolderID() != null) {
                        getCompanyFoldersFromChosenFolder(getCurrentFolderID());
                    } else {
                        getCompanyFoldersFromRoot();
                    }
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                }
            });
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItemPositionToDelete(Item item) {
        listItemToDelete.add(item);
    }

    @Override
    public void removeItemPositionToDelete(Item item) {
        listItemToDelete.remove(item);

    }

    private List<Item> getListItemToDelete() {
        return listItemToDelete;
    }

    private void setAllCompanyFolders(DTOFolderContent folders) {
        allCompanyFolders = folders;
    }

    private void openFile(File file, String extension) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri fileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);

        intent.setDataAndType(fileUri, getMimeType(extension));

        intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            context.startActivity(intent);
        }
    }

    private String getMimeType(String extension) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    @Override
    public DTOFolderContent getAllCompanyFolders() {
        return allCompanyFolders;
    }

    @Override
    public Item[] getItems() {
        return items;
    }

    private void setItems(DTOFolderContent result) {
        int i = 0;

        items = new Item[setSizeOfItemsFromResult(result)];

        if (result.getFolders() != null) {
            for (Katalog katalog : result.getFolders()
            ) {
                Item item = new Item();
                item.setFolder(katalog);
                items[i] = item;
                i++;
            }
        }

        if (result.getFiles() != null) {
            for (Plik file : result.getFiles()
            ) {
                Item item = new Item();
                item.setFile(file);
                items[i] = item;
                i++;
            }
        }
    }

    private Item[] addKatalogToItems(Katalog katalog) {
        Item[] newItems = new Item[getItems().length + 1];
        Item[] items = getItems();

        Item item = new Item();
        item.setFolder(katalog);
        newItems[0] = item;

        int i = 1;
        for (Item itemInItems : items
        ) {
            newItems[i] = itemInItems;
            i++;
        }

        return newItems;
    }

    private int setSizeOfItemsFromResult(DTOFolderContent result) {
        if (result.getFolders() != null && result.getFiles() != null) {
            return result.getFiles().size() + result.getFolders().size();
        } else if (result.getFolders() != null) {
            return result.getFolders().size();
        } else if (result.getFiles() != null) {
            return result.getFiles().size();
        } else {
            return 0;
        }
    }

    @Override
    public void getOtherCompanyFolders() {
        authenticationPresenter = AuthenticationPresenter.getInstance();
        AuthenticationUser authenticationUser = authenticationPresenter.getAuthenticationUser();
    }

    @Override
    public Integer getCurrentFolderParentFolderID() {
        return currentFolderParentFolderID;
    }

    @Override
    public void setCurrentFolderParentFolderID(Integer currentFolderParentFolderID) {
        Integer a = currentFolderParentFolderID;

        this.currentFolderParentFolderID = currentFolderParentFolderID;
    }

    private void setCurrentFolderParentFolderID(int chosenFolder) {
        for (Item item : items
        ) {
            if (item.getFolder() != null) {
                if (item.getFolder().getIdKatalogu().equals(chosenFolder)) {

                    setCurrentFolderParentFolderID(item.getFolder().getIdKataloguNadrzednego());
                }
            }
        }
    }

    @Override
    public Integer getFolderParentFolderID(Integer folderID) {
        for (Item item : items
        ) {
            if (item.getFolder() != null) {
                if (item.getFolder().getIdKatalogu().equals(folderID)) {
                    return item.getFolder().getIdKataloguNadrzednego();
                }
            }
        }
        return null;
    }

    @Override
    public String getFolderName(Integer folderID) {
        for (Item item : items
        ) {
            if (item.getFolder() != null) {
                if (item.getFolder().getIdKatalogu().equals(folderID)) {
                    return item.getFolder().getNazwa();
                }
            }
        }
        return null;
    }

    private Integer getCurrentFolderID() {
        return currentFolderID;
    }

    private void setCurrentFolderID(Integer currentFolderID) {
        this.currentFolderID = currentFolderID;
    }

    @Override
    public boolean isAccessForModifyLearningMaterials() {
        return accessForModifyLearningMaterials;
    }

    @Override
    public void setAccessForModifyLearningMaterials(boolean accessForModifyLearningMaterials) {
        this.accessForModifyLearningMaterials = accessForModifyLearningMaterials;
    }
}