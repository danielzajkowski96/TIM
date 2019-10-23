package com.example.company.learningMaterials.model;

import com.example.company.API.model.Katalog;
import com.example.company.API.model.Plik;

public class Item {

    private Katalog folder;
    private Plik file;

    public Item() {
    }

    public Item(Katalog folder) {
        this.folder = folder;
    }

    public Item(Plik file) {
        this.file = file;
    }

    public Item(Katalog folder, Plik file) {
        this.folder = folder;
        this.file = file;
    }

    public Katalog getFolder() {
        return folder;
    }

    public void setFolder(Katalog folder) {
        this.folder = folder;
    }

    public Plik getFile() {
        return file;
    }

    public void setFile(Plik file) {
        this.file = file;
    }
}
