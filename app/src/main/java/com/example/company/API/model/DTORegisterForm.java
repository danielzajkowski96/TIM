package com.example.company.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class DTORegisterForm {
  @SerializedName("firstName")
  private String firstName = null;

  @SerializedName("familyName")
  private String familyName = null;

  @SerializedName("password")
  private String password = null;

  public DTORegisterForm firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public DTORegisterForm familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * Get familyName
   * @return familyName
   **/

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public DTORegisterForm password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   **/

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTORegisterForm dtORegisterForm = (DTORegisterForm) o;
    return Objects.equals(this.firstName, dtORegisterForm.firstName) &&
            Objects.equals(this.familyName, dtORegisterForm.familyName) &&
            Objects.equals(this.password, dtORegisterForm.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, familyName, password);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTORegisterForm {\n");

    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

