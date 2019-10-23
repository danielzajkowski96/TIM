package com.example.company.learningMaterials.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.company.API.model.DTOFolderContent;
import com.example.company.API.model.Katalog;
import com.example.company.R;
import com.example.company.learningMaterials.model.Item;
import com.example.company.learningMaterials.view.LearningMaterialsFragment;

import java.util.List;

public class LearningMaterialsListViewAdapter extends ArrayAdapter<Item> {

    private Context context;
    private Item[] items;
    private boolean accessForModifyLearningMaterials;

    public LearningMaterialsListViewAdapter(Context context, Item[] items, boolean accessForModifyLearningMaterials) {
        super(context, R.layout.item_folder_listview, items);
        this.context = context;
        this.items = items;
        this.accessForModifyLearningMaterials = accessForModifyLearningMaterials;
    }

    @Override
    public int getPosition(@Nullable Item item) {
        return super.getPosition(item);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.item_folder_listview, parent, false);
        }


        TextView nameTextView = row.findViewById(R.id.learning_materials_item_name_textView);
        TextView dateTextView = row.findViewById(R.id.learning_materials_item_date_textView);
        ImageView folderImageView = row.findViewById(R.id.learning_materials_item_imageView);
        ImageView settingsImageView = row.findViewById(R.id.learning_materials_item_settings);
        settingsImageView.setVisibility(accessForModifyLearningMaterials ? View.VISIBLE : View.GONE);

        if (items[position].getFolder() != null) {
            nameTextView.setText(items[position].getFolder().getNazwa());
            folderImageView.setImageResource(R.drawable.ic_folder_foreground);
        } else if (items[position].getFile() != null) {
            nameTextView.setText(String.format("%s%s", items[position].getFile().getOpis(), items[position].getFile().getRozszerzenie()));
            folderImageView.setImageResource(R.drawable.ic_file_universal_foreground);
        }

        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LearningMaterialsFragment.getInstance().showAlertDialogDelete(items[position]);
            }
        });

        return row;
    }

}
