package com.example.company.messages.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.R;
import com.example.company.companyChecker.interfaces.NoCompanyInterface;
import com.example.company.companyChecker.presenter.NoCompanyPresenter;
import com.example.company.main.view.MainActivity;
import com.example.company.messages.interfaces.MessagesInterface;
import com.example.company.messages.presenter.MessagesPresenter;

public class MessagesFragment extends Fragment implements MessagesInterface.View {

    private static MessagesFragment messagesFragment;

    private MessagesInterface.Presenter messagesPresenter;
    private NoCompanyInterface.Presenter noCompanyPresenter;
    private Button sendMessageButton;
    private EditText messageTitleEditText;
    private EditText messageBodyEditText;
    private TextView messagesInfoTextView;

    public MessagesFragment() {
    }

    public static MessagesFragment getInstance() {
        if (messagesFragment == null) {
            messagesFragment = new MessagesFragment();
        }
        return messagesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMessagesPresenter();
        setNoCompanyPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        messagesPresenter.getView(this);
        messagesPresenter.getContext(getContext());

        setComponents(view);
        setSendMessageButton();
        setMessagesInfoTextView(messagesPresenter.getPlatoonId());

        clearTitle();
        clearBody();

        return view;
    }

    private void setComponents(View view) {
        sendMessageButton = view.findViewById(R.id.messages_send_message_button);
        messageTitleEditText = view.findViewById(R.id.messages_title_message_editText);
        messageBodyEditText = view.findViewById(R.id.messages_body_message_editText);
        messagesInfoTextView = view.findViewById(R.id.messages_info_textView);
    }

    private void setMessagesPresenter(){
        messagesPresenter = MessagesPresenter.getInstance();
    }

    private void setNoCompanyPresenter(){
        noCompanyPresenter = NoCompanyPresenter.getInstance();
    }

    @Override
    public void setVisibleMainActivityProgressBar(final boolean isVisible) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.getInstance().setVisibleProgressBar(isVisible);
            }
        });
    }

    @Override
    public void setEnableAllComponents(final boolean isEnable) {
        final ConstraintLayout constraintLayout = getView().findViewById(R.id.fragment_messages);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < constraintLayout.getChildCount(); i++) {
                    View child = constraintLayout.getChildAt(i);

                    child.setEnabled(isEnable);
                }
            }
        });
    }

    private void setSendMessageButton() {
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkTitle() && checkBody()) {
                    setMessageTitle();
                    setMessageBody();
                    setVisibleMainActivityProgressBar(true);
                    setEnableAllComponents(false);
                    messagesPresenter.sendMessage();
                }
            }
        });
    }

    private boolean checkTitle() {
        if (!isEditTextEmpty(messageTitleEditText)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_messages_title));
            setFocusable(messageTitleEditText);
            return false;
        }
    }

    private boolean checkBody() {
        if (!isEditTextEmpty(messageBodyEditText)) {
            return true;
        } else {
            showToast(getResources().getString(R.string.title_messages_body));
            setFocusable(messageBodyEditText);
            return false;
        }
    }

    @Override
    public void clearTitle() {
        messageTitleEditText.setText("");
    }

    @Override
    public void clearBody() {
        messageBodyEditText.setText("");
    }

    private void setMessageTitle() {
        messagesPresenter.setTitle(messageTitleEditText.getText().toString());
    }

    private void setMessageBody() {
        messagesPresenter.setBody(messageBodyEditText.getText().toString());
    }

    private boolean isEditTextEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    private void setFocusable(EditText editText) {
        editText.setFocusable(true);
        editText.requestFocus();
    }

    @Override
    public void showSnackbar(String message) {
        MainActivity.getInstance().showSnackbar(message);
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    private void setMessagesInfoTextView(Integer platoonNumber) {
        messagesInfoTextView.setText(String.format("%s %s plutonu", messagesInfoTextView.getText(), String.valueOf(platoonNumber)));
    }

}
