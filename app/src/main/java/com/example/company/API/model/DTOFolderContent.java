package com.example.company.API.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DTOFolderContent
 */

public class DTOFolderContent {
  @SerializedName("folders")
  private List<Katalog> folders = null;

  @SerializedName("files")
  private List<Plik> files = null;

  public DTOFolderContent folders(List<Katalog> folders) {
    this.folders = folders;
    return this;
  }

  public DTOFolderContent addFoldersItem(Katalog foldersItem) {
    if (this.folders == null) {
      this.folders = new ArrayList<Katalog>();
    }
    this.folders.add(foldersItem);
    return this;
  }

   /**
   * Get folders
   * @return folders
  **/

  public List<Katalog> getFolders() {
    return folders;
  }

  public void setFolders(List<Katalog> folders) {
    this.folders = folders;
  }

  public DTOFolderContent files(List<Plik> files) {
    this.files = files;
    return this;
  }

  public DTOFolderContent addFilesItem(Plik filesItem) {
    if (this.files == null) {
      this.files = new ArrayList<Plik>();
    }
    this.files.add(filesItem);
    return this;
  }

   /**
   * Get files
   * @return files
  **/

  public List<Plik> getFiles() {
    return files;
  }

  public void setFiles(List<Plik> files) {
    this.files = files;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTOFolderContent dtOFolderContent = (DTOFolderContent) o;
    return Objects.equals(this.folders, dtOFolderContent.folders) &&
        Objects.equals(this.files, dtOFolderContent.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(folders, files);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTOFolderContent {\n");

    sb.append("    folders: ").append(toIndentedString(folders)).append("\n");
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

