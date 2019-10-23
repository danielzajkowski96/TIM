package com.example.company.API.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;


public class Prosba {
  @SerializedName("idProsby")
  private Integer idProsby = null;

  @SerializedName("nrKompanii")
  private Integer nrKompanii = null;

  @SerializedName("nrPlutonu")
  private Integer nrPlutonu = null;

  @SerializedName("idZatwierdzajacego")
  private Integer idZatwierdzajacego = null;

  @SerializedName("idZglaszajacego")
  private Integer idZglaszajacego = null;

  @SerializedName("obsluzona")
  private Boolean obsluzona = null;

  /**
   * Gets or Sets typProsby
   */
  @JsonAdapter(TypProsbyEnum.Adapter.class)
  public enum TypProsbyEnum {
    NUMBER_0(0),

    NUMBER_1(1),

    NUMBER_2(2),

    NUMBER_3(3),

    NUMBER_4(4);

    private Integer value;

    TypProsbyEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypProsbyEnum fromValue(String text) {
      for (TypProsbyEnum b : TypProsbyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<TypProsbyEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypProsbyEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypProsbyEnum read(final JsonReader jsonReader) throws IOException {
        Integer value = jsonReader.nextInt();
        return TypProsbyEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("typProsby")
  private TypProsbyEnum typProsby = null;

  @SerializedName("_Zatwierdzajacy")
  private Zolnierz zatwierdzajacy = null;

  @SerializedName("_Zglaszajacy")
  private Zolnierz zglaszajacy = null;

  @SerializedName("_Kompania")
  private Kompania kompania = null;

  @SerializedName("_Pluton")
  private Pluton pluton = null;

  public Prosba idProsby(Integer idProsby) {
    this.idProsby = idProsby;
    return this;
  }

   /**
   * Get idProsby
   * @return idProsby
  **/

  public Integer getIdProsby() {
    return idProsby;
  }

  public void setIdProsby(Integer idProsby) {
    this.idProsby = idProsby;
  }

  public Prosba nrKompanii(Integer nrKompanii) {
    this.nrKompanii = nrKompanii;
    return this;
  }

   /**
   * Get nrKompanii
   * @return nrKompanii
  **/

  public Integer getNrKompanii() {
    return nrKompanii;
  }

  public void setNrKompanii(Integer nrKompanii) {
    this.nrKompanii = nrKompanii;
  }

  public Prosba nrPlutonu(Integer nrPlutonu) {
    this.nrPlutonu = nrPlutonu;
    return this;
  }

   /**
   * Get nrPlutonu
   * @return nrPlutonu
  **/

  public Integer getNrPlutonu() {
    return nrPlutonu;
  }

  public void setNrPlutonu(Integer nrPlutonu) {
    this.nrPlutonu = nrPlutonu;
  }

  public Prosba idZatwierdzajacego(Integer idZatwierdzajacego) {
    this.idZatwierdzajacego = idZatwierdzajacego;
    return this;
  }

   /**
   * Get idZatwierdzajacego
   * @return idZatwierdzajacego
  **/

  public Integer getIdZatwierdzajacego() {
    return idZatwierdzajacego;
  }

  public void setIdZatwierdzajacego(Integer idZatwierdzajacego) {
    this.idZatwierdzajacego = idZatwierdzajacego;
  }

  public Prosba idZglaszajacego(Integer idZglaszajacego) {
    this.idZglaszajacego = idZglaszajacego;
    return this;
  }

   /**
   * Get idZglaszajacego
   * @return idZglaszajacego
  **/

  public Integer getIdZglaszajacego() {
    return idZglaszajacego;
  }

  public void setIdZglaszajacego(Integer idZglaszajacego) {
    this.idZglaszajacego = idZglaszajacego;
  }

  public Prosba obsluzona(Boolean obsluzona) {
    this.obsluzona = obsluzona;
    return this;
  }

   /**
   * Get obsluzona
   * @return obsluzona
  **/

  public Boolean isObsluzona() {
    return obsluzona;
  }

  public void setObsluzona(Boolean obsluzona) {
    this.obsluzona = obsluzona;
  }

  public Prosba typProsby(TypProsbyEnum typProsby) {
    this.typProsby = typProsby;
    return this;
  }

   /**
   * Get typProsby
   * @return typProsby
  **/

  public TypProsbyEnum getTypProsby() {
    return typProsby;
  }

  public void setTypProsby(TypProsbyEnum typProsby) {
    this.typProsby = typProsby;
  }

  public Prosba zatwierdzajacy(Zolnierz zatwierdzajacy) {
    this.zatwierdzajacy = zatwierdzajacy;
    return this;
  }

   /**
   * Get zatwierdzajacy
   * @return zatwierdzajacy
  **/

  public Zolnierz getZatwierdzajacy() {
    return zatwierdzajacy;
  }

  public void setZatwierdzajacy(Zolnierz zatwierdzajacy) {
    this.zatwierdzajacy = zatwierdzajacy;
  }

  public Prosba zglaszajacy(Zolnierz zglaszajacy) {
    this.zglaszajacy = zglaszajacy;
    return this;
  }

   /**
   * Get zglaszajacy
   * @return zglaszajacy
  **/

  public Zolnierz getZglaszajacy() {
    return zglaszajacy;
  }

  public void setZglaszajacy(Zolnierz zglaszajacy) {
    this.zglaszajacy = zglaszajacy;
  }

  public Prosba kompania(Kompania kompania) {
    this.kompania = kompania;
    return this;
  }

   /**
   * Get kompania
   * @return kompania
  **/

  public Kompania getKompania() {
    return kompania;
  }

  public void setKompania(Kompania kompania) {
    this.kompania = kompania;
  }

  public Prosba pluton(Pluton pluton) {
    this.pluton = pluton;
    return this;
  }

   /**
   * Get pluton
   * @return pluton
  **/

  public Pluton getPluton() {
    return pluton;
  }

  public void setPluton(Pluton pluton) {
    this.pluton = pluton;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Prosba prosba = (Prosba) o;
    return Objects.equals(this.idProsby, prosba.idProsby) &&
        Objects.equals(this.nrKompanii, prosba.nrKompanii) &&
        Objects.equals(this.nrPlutonu, prosba.nrPlutonu) &&
        Objects.equals(this.idZatwierdzajacego, prosba.idZatwierdzajacego) &&
        Objects.equals(this.idZglaszajacego, prosba.idZglaszajacego) &&
        Objects.equals(this.obsluzona, prosba.obsluzona) &&
        Objects.equals(this.typProsby, prosba.typProsby) &&
        Objects.equals(this.zatwierdzajacy, prosba.zatwierdzajacy) &&
        Objects.equals(this.zglaszajacy, prosba.zglaszajacy) &&
        Objects.equals(this.kompania, prosba.kompania) &&
        Objects.equals(this.pluton, prosba.pluton);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idProsby, nrKompanii, nrPlutonu, idZatwierdzajacego, idZglaszajacego, obsluzona, typProsby, zatwierdzajacy, zglaszajacy, kompania, pluton);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Prosba {\n");

    sb.append("    idProsby: ").append(toIndentedString(idProsby)).append("\n");
    sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
    sb.append("    nrPlutonu: ").append(toIndentedString(nrPlutonu)).append("\n");
    sb.append("    idZatwierdzajacego: ").append(toIndentedString(idZatwierdzajacego)).append("\n");
    sb.append("    idZglaszajacego: ").append(toIndentedString(idZglaszajacego)).append("\n");
    sb.append("    obsluzona: ").append(toIndentedString(obsluzona)).append("\n");
    sb.append("    typProsby: ").append(toIndentedString(typProsby)).append("\n");
    sb.append("    zatwierdzajacy: ").append(toIndentedString(zatwierdzajacy)).append("\n");
    sb.append("    zglaszajacy: ").append(toIndentedString(zglaszajacy)).append("\n");
    sb.append("    kompania: ").append(toIndentedString(kompania)).append("\n");
    sb.append("    pluton: ").append(toIndentedString(pluton)).append("\n");
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

