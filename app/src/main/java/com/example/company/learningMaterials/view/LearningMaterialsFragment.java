package com.example.company.learningMaterials.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.company.R;
import com.example.company.authentication.presenter.AuthenticationPresenter;
import com.example.company.learningMaterials.interfaces.LearningMaterialsInterface;
import com.example.company.learningMaterials.model.Item;
import com.example.company.learningMaterials.presenter.LearningMaterialsListViewAdapter;
import com.example.company.learningMaterials.presenter.LearningMaterialsPresenter;
import com.example.company.permissionAccess.interfaces.PermissionAccessInterface;
import com.example.company.permissionAccess.presenter.PermissionAccessPresenter;
import com.example.company.snackbar.SnackbarPresenter;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class LearningMaterialsFragment extends Fragment implements LearningMaterialsInterface.View, View.OnClickListener, PermissionAccessInterface.View {

    private static int CHOOSE_FILE_CODE = 1;

    private static LearningMaterialsFragment learningMaterialsFragment;

    private LearningMaterialsInterface.Presenter learningMaterialsPresenter;

    private ListView learningMaterialsListView;

    private List<Item> items;

    private View view;

    private TextView openFolderTextView;
    private TextView parentFolderTextView;

    private LinearLayout upToRootFolderLayout;
    private View foldersLayoutView;

    private ProgressBar listViewProgressBar;
    private ProgressBar mainActivityProgressBar;
    private ProgressBar mainActivityUploadDownloadProgressBar;

    private FloatingActionButton addElementButton;

    private PermissionAccessInterface.Presenter permissionAccessPresenter;

    private LearningMaterialsListViewAdapter learningMaterialsListViewAdapter;

    public static LearningMaterialsFragment getInstance() {
        if (learningMaterialsFragment == null) {
            learningMaterialsFragment = new LearningMaterialsFragment();
        }
        return learningMaterialsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLearningMaterialsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning_materials, container, false);

        setView(view);

        setListViewProgressBar();

        setAddElementButton();
        setMainActivityProgressBar();
        setPermissionAccessPresenter();
        checkUserHasAccessForModifyLearningMaterials();

        learningMaterialsPresenter.setView(this);
        learningMaterialsPresenter.setContext(getContext());

        SnackbarPresenter.getInstance().setView(view);


        learningMaterialsListView = view.findViewById(R.id.learning_materials_listview);

        setOpenFolderTextView();
        setParentFolderTextView();
        setUpToRootFolderLayout();


        setMainActivityUploadDownloadProgressBar();


        setEnableUpToRootFolder(false);
        setVisibleUpToRootFolder(false);

        setParentFolderName("");
        setOpenFolderName(getDefaultFolderName());

        onClickItemListView();


        return view;
    }

    private void setListViewProgressBar() {
        listViewProgressBar = getView().findViewById(R.id.learning_materials_list_view_progressBar);
    }

    private void setMainActivityProgressBar() {
        mainActivityProgressBar = getActivity().findViewById(R.id.main_activity_progressBar);
    }

    private void setMainActivityUploadDownloadProgressBar() {
        mainActivityUploadDownloadProgressBar = getActivity().findViewById(R.id.main_activity_upload_download_progressBar);
    }

    @Override
    public void setVisibleListViewProgressBar(final boolean isVisible) {
        if (getActivity() != null)Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listViewProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setVisibleMainActivityUploadDownloadProgressBar(final boolean isVisible) {
        if (getActivity() != null) getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityUploadDownloadProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setMainActivityUploadDownloadProgressBarValue(final int value) {
        if (getActivity() != null) getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityUploadDownloadProgressBar.setProgress(value);
            }
        });
    }

    @Override
    public void setMainActivityUploadDownloadProgressBarMaxValue(final int value) {
        if (getActivity() != null) getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityUploadDownloadProgressBar.setMax(value);
            }
        });
    }

    private void setAddElementButton() {
        addElementButton = getView().findViewById(R.id.learning_materials_add_element_button);
        addElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogCreate();
            }
        });
    }

    private void showAlertDialogCreate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create, null);
        builder.setView(view);

        LinearLayout createNewFolder = view.findViewById(R.id.dialog_create_create_new_folder_layout);
        LinearLayout uploadFile = view.findViewById(R.id.dialog_create_upload_file_layout);

        final AlertDialog alert = builder.create();
        alert.show();

        createNewFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
                showAlertDialogCreateNewFolder();
            }
        });


        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
                showChooseFileActivity();
            }
        });
    }

    private void showAlertDialogCreateNewFolder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_newfolder, null);

        final EditText folderNameEditText = view.findViewById(R.id.daloge_create_new_folder_folder_name_editText);

        builder.setView(folderNameEditText);

        builder.setView(view);
        builder.setPositiveButton(R.string.title_dialog_create_new_folder_create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                learningMaterialsPresenter.createFolder(folderNameEditText.getText().toString());
            }
        });

        builder.setNegativeButton(R.string.title_dialog_create_new_folder_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog alert = builder.create();

        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        alert.show();

        folderNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    setEnablePositiveButton(alert, true);
                } else {
                    setEnablePositiveButton(alert, false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void showChooseFileActivity() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent = Intent.createChooser(intent, "");
        startActivityForResult(intent, CHOOSE_FILE_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_FILE_CODE) {
            Uri selectedFileUri = data.getData();

            if (selectedFileUri != null) {
                File selectedFile = new File(selectedFileUri.getPath());

                learningMaterialsPresenter.uploadFile(selectedFile);
            }
        }
    }

    public void showAlertDialogDelete(final Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete, null);
        builder.setView(view);

        LinearLayout delete = view.findViewById(R.id.dialog_delete_layout);

        final AlertDialog alert = builder.create();
        alert.show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
                if (item.getFile() != null) {
                    learningMaterialsPresenter.deleteFile(item.getFile().getIdPliku());
                } else if (item.getFolder() != null) {
                    learningMaterialsPresenter.deleteFolder(item.getFolder().getIdKatalogu());
                }
            }
        });
    }

    private void setEnablePositiveButton(AlertDialog alert, boolean isEnabled) {
        if (isEnabled) {
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        } else {
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorInactive));
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }

    private void setLearningMaterialsPresenter() {
        learningMaterialsPresenter = LearningMaterialsPresenter.getInstance();
    }

    private void setOpenFolderTextView() {
        openFolderTextView = getView().findViewById(R.id.learning_materials_item_folder_root_name_textView);
    }

    private void setParentFolderTextView() {
        parentFolderTextView = getView().findViewById(R.id.learning_materials_item_back_to_folder_root_name_textView);
    }

    @Override
    public void setOpenFolderName(String folderName) {
        openFolderTextView.setText(folderName);
    }

    @Override
    public String getOpenFolderName() {
        return openFolderTextView.getText().toString();
    }

    private String getParentFolderName() {
        return parentFolderTextView.getText().toString();
    }

    @Override
    public void setParentFolderName(String parentFolderName) {
        parentFolderTextView.setText(parentFolderName);
        if (parentFolderName == null) {
            parentFolderTextView.setText(getDefaultFolderName());
        } else {
            parentFolderTextView.setText(parentFolderName);
        }
    }

    private void setParentFolderName(Integer parentFolder) {

        if (parentFolder == null) {
            parentFolderTextView.setText(getDefaultFolderName());
        } else {
            parentFolderTextView.setText(getOpenFolderName());
        }
    }

    private void setUpToRootFolderLayout() {
        upToRootFolderLayout = getView().findViewById(R.id.up_to_root_folder_layout);
        upToRootFolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (learningMaterialsPresenter.getCurrentFolderParentFolderID() == null) {
                    setOpenFolderName(getDefaultFolderName());
                    learningMaterialsPresenter.getCompanyFoldersFromRoot();
                } else {
                    learningMaterialsPresenter.setCurrentFolderParentFolderID(learningMaterialsPresenter.getCurrentFolderParentFolderID());

                    setOpenFolderName(getParentFolderName());
                    setParentFolderName(learningMaterialsPresenter.getFolderName(learningMaterialsPresenter.getFolderParentFolderID(learningMaterialsPresenter.getCurrentFolderParentFolderID())));

                    getFoldersInsideParentFolder(learningMaterialsPresenter.getCurrentFolderParentFolderID());

                    learningMaterialsPresenter.setCurrentFolderParentFolderID(learningMaterialsPresenter.getFolderParentFolderID(learningMaterialsPresenter.getCurrentFolderParentFolderID()));
                }
            }
        });
    }

    @Override
    public void setEnableUpToRootFolder(final boolean isEnable) {
        if (getActivity() != null)  getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                upToRootFolderLayout.setEnabled(isEnable);
            }
        });
    }

    @Override
    public void setVisibleUpToRootFolder(final boolean isVisible) {
        if (getActivity() != null)  getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                upToRootFolderLayout.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);

            }
        });
    }

    @Override
    public void showAllCompanyFolders() {
        if (getActivity() != null) getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Item[] items = learningMaterialsPresenter.getItems();

                learningMaterialsListViewAdapter = new LearningMaterialsListViewAdapter(getActivity(), items, learningMaterialsPresenter.isAccessForModifyLearningMaterials());
                learningMaterialsListView.setAdapter(learningMaterialsListViewAdapter);
            }
        });

        onClickItemListView();
    }

    @Override
    public void showCreatedFolderSuccessSnackbar() {
        SnackbarPresenter.getInstance().showSnackbar(getResources().getString(R.string.title_dialog_create_new_folder_success));
    }

    @Override
    public void showDeletedSuccessSnackbar() {
        SnackbarPresenter.getInstance().showSnackbar(getResources().getString(R.string.title_dialog_delete_success));
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private String getDefaultFolderName() {
        return String.format("Folder %d kompanii", AuthenticationPresenter.getInstance().getAuthenticationUser().getCompanyNumber());
    }

    private void onClickItemListView() {
        learningMaterialsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (checkIsFolder(position)) {
                    getFoldersInsideChosenFolder(position);
                    learningMaterialsPresenter.setCurrentFolderParentFolderID(learningMaterialsPresenter.getItems()[position].getFolder().getIdKataloguNadrzednego());
                    setParentFolderName(learningMaterialsPresenter.getItems()[position].getFolder().getIdKataloguNadrzednego());
                    setOpenFolderName(learningMaterialsPresenter.getItems()[position].getFolder().getNazwa());
                } else {
                    downloadFile(position);
                }
            }
        });
    }

    private boolean checkIsFolder(int position) {
        return learningMaterialsPresenter.getItems()[position].getFolder() != null;
    }

    private void getFoldersInsideParentFolder(int parentFolderID) {
        learningMaterialsPresenter.getCompanyFoldersFromChosenFolder(parentFolderID);
    }

    private void getFoldersInsideChosenFolder(int position) {
        learningMaterialsPresenter.getCompanyFoldersFromChosenFolder(learningMaterialsPresenter.getItems()[position].getFolder().getIdKatalogu());
    }

    private void downloadFile(int position) {
        String extension = substringExtension(learningMaterialsPresenter.getItems()[position].getFile().getRozszerzenie());
        learningMaterialsPresenter.downloadFile(learningMaterialsPresenter.getItems()[position].getFile().getIdPliku(), extension);
    }

    private String substringExtension(String extension) {
        return extension.substring(1);
    }

    @Override
    public void onClick(View v) {

    }

    private void setPermissionAccessPresenter() {
        permissionAccessPresenter = PermissionAccessPresenter.getInstance();
        permissionAccessPresenter.setView(this);
    }

    private void checkUserHasAccessForModifyLearningMaterials() {
        permissionAccessPresenter.checkUserHasAccessForModifyLearningMaterials();
    }

    @Override
    public void showMessagesNavigationMenuItem(boolean isVisible) {
    }

    @Override
    public void setVisibleProgressBar(final boolean isVisible) {
        if (getActivity() != null) {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mainActivityProgressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    @Override
    public void setVisibleLearningMaterialsAddButton(final boolean isVisible) {
        if (getActivity() != null) {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @SuppressLint("RestrictedApi")
                @Override
                public void run() {
                    addElementButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

}