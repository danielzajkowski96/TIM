package com.example.company.learningMaterials.interfaces;

import android.content.Context;

import com.example.company.API.model.DTOFolderContent;
import com.example.company.learningMaterials.model.Item;

import java.io.File;
import java.util.UUID;

public interface LearningMaterialsInterface {

    interface View {
        void showAllCompanyFolders();

        void setEnableUpToRootFolder(final boolean isEnable);

        void setVisibleUpToRootFolder(final boolean isVisible);

        void setParentFolderName(String parentFolderName);

        void setOpenFolderName(String folderName);

        String getOpenFolderName();

        void setVisibleListViewProgressBar(final boolean isVisible);

        void setVisibleMainActivityUploadDownloadProgressBar(final boolean isVisible);

        void setMainActivityUploadDownloadProgressBarValue(final int value);

        void setMainActivityUploadDownloadProgressBarMaxValue(final int value);

        void showCreatedFolderSuccessSnackbar();

        void showDeletedSuccessSnackbar();
    }

    interface Presenter {
        void setView(LearningMaterialsInterface.View view);

        void setContext(Context context);

        void getCompanyFoldersFromRoot();

        DTOFolderContent getAllCompanyFolders();

        void getOtherCompanyFolders();

        Item[] getItems();

        void getCompanyFoldersFromChosenFolder(int chosenFolder);

        void createFolder(String name);

        void uploadFile(File file);

        //void downloadFile(UUID fileId, String extension);

        void downloadFile(String fileId, String extension);

        //void deleteFile(UUID fileId);

        void deleteFile(String fileId);

        void deleteFolder(Integer folderID);

        Integer getCurrentFolderParentFolderID();

        void setCurrentFolderParentFolderID(Integer currentFolderParentFolderID);

        Integer getFolderParentFolderID(Integer folderID);

        String getFolderName(Integer folderID);

        void addItemPositionToDelete(Item item);

        void removeItemPositionToDelete(Item item);

        boolean isAccessForModifyLearningMaterials();

        void setAccessForModifyLearningMaterials(boolean accessForModifyLearningMaterials);
    }
}
