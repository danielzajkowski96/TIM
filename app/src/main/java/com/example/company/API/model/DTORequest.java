package com.example.company.API.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;


public class DTORequest {
  @SerializedName("companyId")
  private Integer companyId = null;

  @SerializedName("platoonId")
  private Integer platoonId = null;

  /**
   * Gets or Sets requestType
   */
  @JsonAdapter(RequestTypeEnum.Adapter.class)
  public enum RequestTypeEnum {
    NUMBER_0(0),

    NUMBER_1(1),

    NUMBER_2(2),

    NUMBER_3(3),

    NUMBER_4(4);

    private Integer value;

    RequestTypeEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static RequestTypeEnum fromValue(String text) {
      for (RequestTypeEnum b : RequestTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<RequestTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final RequestTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public RequestTypeEnum read(final JsonReader jsonReader) throws IOException {
        Integer value = jsonReader.nextInt();
        return RequestTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("requestType")
  private RequestTypeEnum requestType = null;

  public DTORequest companyId(Integer companyId) {
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

  public DTORequest platoonId(Integer platoonId) {
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

  public DTORequest requestType(RequestTypeEnum requestType) {
    this.requestType = requestType;
    return this;
  }

   /**
   * Get requestType
   * @return requestType
  **/

  public RequestTypeEnum getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestTypeEnum requestType) {
    this.requestType = requestType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DTORequest dtORequest = (DTORequest) o;
    return Objects.equals(this.companyId, dtORequest.companyId) &&
        Objects.equals(this.platoonId, dtORequest.platoonId) &&
        Objects.equals(this.requestType, dtORequest.requestType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, platoonId, requestType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DTORequest {\n");

    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    platoonId: ").append(toIndentedString(platoonId)).append("\n");
    sb.append("    requestType: ").append(toIndentedString(requestType)).append("\n");
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

