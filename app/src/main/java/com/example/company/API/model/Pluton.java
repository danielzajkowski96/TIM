package com.example.company.API.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Pluton
 */

public class Pluton {
  @SerializedName("nrPlutonu")
  private Integer nrPlutonu = null;

  @SerializedName("nrKompanii")
  private Integer nrKompanii = null;

  @SerializedName("idDowodcy")
  private Integer idDowodcy = null;

  @SerializedName("_Dowodca")
  private Zolnierz dowodca = null;

  @SerializedName("_Kompania")
  private Kompania kompania = null;

  @SerializedName("_Prosby")
  private List<Prosba> prosby = null;

  @SerializedName("_Katalogi")
  private List<Katalog> katalogi = null;

  public Pluton nrPlutonu(Integer nrPlutonu) {
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

  public Pluton nrKompanii(Integer nrKompanii) {
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

  public Pluton idDowodcy(Integer idDowodcy) {
    this.idDowodcy = idDowodcy;
    return this;
  }

   /**
   * Get idDowodcy
   * @return idDowodcy
  **/

  public Integer getIdDowodcy() {
    return idDowodcy;
  }

  public void setIdDowodcy(Integer idDowodcy) {
    this.idDowodcy = idDowodcy;
  }

  public Pluton dowodca(Zolnierz dowodca) {
    this.dowodca = dowodca;
    return this;
  }

   /**
   * Get dowodca
   * @return dowodca
  **/

  public Zolnierz getDowodca() {
    return dowodca;
  }

  public void setDowodca(Zolnierz dowodca) {
    this.dowodca = dowodca;
  }

  public Pluton kompania(Kompania kompania) {
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

  public Pluton prosby(List<Prosba> prosby) {
    this.prosby = prosby;
    return this;
  }

  public Pluton addProsbyItem(Prosba prosbyItem) {
    if (this.prosby == null) {
      this.prosby = new ArrayList<Prosba>();
    }
    this.prosby.add(prosbyItem);
    return this;
  }

   /**
   * Get prosby
   * @return prosby
  **/

  public List<Prosba> getProsby() {
    return prosby;
  }

  public void setProsby(List<Prosba> prosby) {
    this.prosby = prosby;
  }

  public Pluton katalogi(List<Katalog> katalogi) {
    this.katalogi = katalogi;
    return this;
  }

  public Pluton addKatalogiItem(Katalog katalogiItem) {
    if (this.katalogi == null) {
      this.katalogi = new ArrayList<Katalog>();
    }
    this.katalogi.add(katalogiItem);
    return this;
  }

   /**
   * Get katalogi
   * @return katalogi
  **/

  public List<Katalog> getKatalogi() {
    return katalogi;
  }

  public void setKatalogi(List<Katalog> katalogi) {
    this.katalogi = katalogi;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pluton pluton = (Pluton) o;
    return Objects.equals(this.nrPlutonu, pluton.nrPlutonu) &&
        Objects.equals(this.nrKompanii, pluton.nrKompanii) &&
        Objects.equals(this.idDowodcy, pluton.idDowodcy) &&
        Objects.equals(this.dowodca, pluton.dowodca) &&
        Objects.equals(this.kompania, pluton.kompania) &&
        Objects.equals(this.prosby, pluton.prosby) &&
        Objects.equals(this.katalogi, pluton.katalogi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nrPlutonu, nrKompanii, idDowodcy, dowodca, kompania, prosby, katalogi);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pluton {\n");

    sb.append("    nrPlutonu: ").append(toIndentedString(nrPlutonu)).append("\n");
    sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
    sb.append("    idDowodcy: ").append(toIndentedString(idDowodcy)).append("\n");
    sb.append("    dowodca: ").append(toIndentedString(dowodca)).append("\n");
    sb.append("    kompania: ").append(toIndentedString(kompania)).append("\n");
    sb.append("    prosby: ").append(toIndentedString(prosby)).append("\n");
    sb.append("    katalogi: ").append(toIndentedString(katalogi)).append("\n");
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

