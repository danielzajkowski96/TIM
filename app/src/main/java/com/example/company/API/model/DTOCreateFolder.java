package com.example.company.API.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * DTOCreateFolder
 */

public class DTOCreateFolder {
  @SerializedName("companyId")
  private Integer companyId = null;

  @SerializedName("platoonId")
  private Integer platoonId = null;

  @SerializedName("rootFolderId")
  private Integer rootFolderId = null;

  @SerializedName("name")
  private String name = null;

  public DTOCreateFolder companyId(Integer companyId) {
    this.companyId = companyId;
    return this;
  }

   /**
   * Get companyId
   * @return companyId
  **/

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  public DTOCreateFolder platoonId(Integer platoonId) {
    this.platoonId = platoonId;
    return this;
  }

   /**
   * Get platoonId
   * @return platoonId
  **/

  public Integer getPlatoonId() {
    return platoonId;
  }

  public void setPlatoonId(Integer platoonId) {
    this.platoonId = platoonId;
  }

  public DTOCreateFolder rootFolderId(Integer rootFolderId) {
    this.rootFolderId = rootFolderId;
    return this;
  }

   /**
   * Get rootFolderId
   * @return rootFolderId
  **/

  public Integer getRootFolderId() {
    return rootFolderId;
  }

  public void setRootFolderId(Integer rootFolderId) {
    this.rootFolderId = rootFolderId;
  }

  public DTOCreateFolder name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTOCreateFolder dtOCreateFolder = (DTOCreateFolder) o;
    return Objects.equals(this.companyId, dtOCreateFolder.companyId) &&
        Objects.equals(this.platoonId, dtOCreateFolder.platoonId) &&
        Objects.equals(this.rootFolderId, dtOCreateFolder.rootFolderId) &&
        Objects.equals(this.name, dtOCreateFolder.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, platoonId, rootFolderId, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTOCreateFolder {\n");

    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    platoonId: ").append(toIndentedString(platoonId)).append("\n");
    sb.append("    rootFolderId: ").append(toIndentedString(rootFolderId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

