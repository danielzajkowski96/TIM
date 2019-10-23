package com.example.company.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DTOSystemUser {
  @SerializedName("userId")
  private Integer userId = null;

  @SerializedName("userName")
  private String userName = null;

  @SerializedName("token")
  private String token = null;

  @SerializedName("roleNames")
  private List<String> roleNames = null;

  @SerializedName("soldier")
  private Zolnierz soldier = null;

  public DTOSystemUser userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Corresponds to Zolnierz.IdOsoby
   * @return userId
   **/

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public DTOSystemUser userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
   **/

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public DTOSystemUser token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
   **/

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public DTOSystemUser roleNames(List<String> roleNames) {
    this.roleNames = roleNames;
    return this;
  }

  public DTOSystemUser addRoleNamesItem(String roleNamesItem) {
    if (this.roleNames == null) {
      this.roleNames = new ArrayList<String>();
    }
    this.roleNames.add(roleNamesItem);
    return this;
  }

  /**
   * Get roleNames
   * @return roleNames
   **/

  public List<String> getRoleNames() {
    return roleNames;
  }

  public void setRoleNames(List<String> roleNames) {
    this.roleNames = roleNames;
  }

  public DTOSystemUser soldier(Zolnierz soldier) {
    this.soldier = soldier;
    return this;
  }

  /**
   * Get soldier
   * @return soldier
   **/

  public Zolnierz getSoldier() {
    return soldier;
  }

  public void setSoldier(Zolnierz soldier) {
    this.soldier = soldier;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTOSystemUser dtOSystemUser = (DTOSystemUser) o;
    return Objects.equals(this.userId, dtOSystemUser.userId) &&
            Objects.equals(this.userName, dtOSystemUser.userName) &&
            Objects.equals(this.token, dtOSystemUser.token) &&
            Objects.equals(this.roleNames, dtOSystemUser.roleNames) &&
            Objects.equals(this.soldier, dtOSystemUser.soldier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, token, roleNames, soldier);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTOSystemUser {\n");

    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    roleNames: ").append(toIndentedString(roleNames)).append("\n");
    sb.append("    soldier: ").append(toIndentedString(soldier)).append("\n");
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

