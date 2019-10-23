package com.example.company.API.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


public class DTONotificationForm {
  @SerializedName("companyId")
  private Integer companyId = null;

  @SerializedName("platoonId")
  private Integer platoonId = null;

  @SerializedName("onlyAssistants")
  private Boolean onlyAssistants = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("body")
  private String body = null;

  public DTONotificationForm companyId(Integer companyId) {
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

  public DTONotificationForm platoonId(Integer platoonId) {
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

  public DTONotificationForm onlyAssistants(Boolean onlyAssistants) {
    this.onlyAssistants = onlyAssistants;
    return this;
  }

   /**
   * Get onlyAssistants
   * @return onlyAssistants
  **/

  public Boolean isOnlyAssistants() {
    return onlyAssistants;
  }

  public void setOnlyAssistants(Boolean onlyAssistants) {
    this.onlyAssistants = onlyAssistants;
  }

  public DTONotificationForm title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public DTONotificationForm body(String body) {
    this.body = body;
    return this;
  }

   /**
   * Get body
   * @return body
  **/

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTONotificationForm dtONotificationForm = (DTONotificationForm) o;
    return Objects.equals(this.companyId, dtONotificationForm.companyId) &&
        Objects.equals(this.platoonId, dtONotificationForm.platoonId) &&
        Objects.equals(this.onlyAssistants, dtONotificationForm.onlyAssistants) &&
        Objects.equals(this.title, dtONotificationForm.title) &&
        Objects.equals(this.body, dtONotificationForm.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, platoonId, onlyAssistants, title, body);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTONotificationForm {\n");

    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    platoonId: ").append(toIndentedString(platoonId)).append("\n");
    sb.append("    onlyAssistants: ").append(toIndentedString(onlyAssistants)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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

