package com.example.company.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Kompania {
  @SerializedName("nrKompanii")
  private Integer nrKompanii = null;

  @SerializedName("idDowodcy")
  private Integer idDowodcy = null;

  @SerializedName("_Dowodca")
  private Zolnierz dowodca = null;

  @SerializedName("_Plutony")
  private List<Pluton> plutony = null;

  @SerializedName("_Katalogi")
  private List<Katalog> katalogi = null;

  @SerializedName("_Prosby")
  private List<Prosba> prosby = null;

  @SerializedName("_Zolnierze")
  private List<Zolnierz> zolnierze = null;

  public Kompania nrKompanii(Integer nrKompanii) {
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

  public Kompania idDowodcy(Integer idDowodcy) {
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

  public Kompania dowodca(Zolnierz dowodca) {
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

  public Kompania plutony(List<Pluton> plutony) {
    this.plutony = plutony;
    return this;
  }

  public Kompania addPlutonyItem(Pluton plutonyItem) {
    if (this.plutony == null) {
      this.plutony = new ArrayList<Pluton>();
    }
    this.plutony.add(plutonyItem);
    return this;
  }

   /**
   * Get plutony
   * @return plutony
  **/

  public List<Pluton> getPlutony() {
    return plutony;
  }

  public void setPlutony(List<Pluton> plutony) {
    this.plutony = plutony;
  }

  public Kompania katalogi(List<Katalog> katalogi) {
    this.katalogi = katalogi;
    return this;
  }

  public Kompania addKatalogiItem(Katalog katalogiItem) {
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

  public Kompania prosby(List<Prosba> prosby) {
    this.prosby = prosby;
    return this;
  }

  public Kompania addProsbyItem(Prosba prosbyItem) {
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

  public Kompania zolnierze(List<Zolnierz> zolnierze) {
    this.zolnierze = zolnierze;
    return this;
  }

  public Kompania addZolnierzeItem(Zolnierz zolnierzeItem) {
    if (this.zolnierze == null) {
      this.zolnierze = new ArrayList<Zolnierz>();
    }
    this.zolnierze.add(zolnierzeItem);
    return this;
  }

   /**
   * Get zolnierze
   * @return zolnierze
  **/

  public List<Zolnierz> getZolnierze() {
    return zolnierze;
  }

  public void setZolnierze(List<Zolnierz> zolnierze) {
    this.zolnierze = zolnierze;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Kompania kompania = (Kompania) o;
    return Objects.equals(this.nrKompanii, kompania.nrKompanii) &&
        Objects.equals(this.idDowodcy, kompania.idDowodcy) &&
        Objects.equals(this.dowodca, kompania.dowodca) &&
        Objects.equals(this.plutony, kompania.plutony) &&
        Objects.equals(this.katalogi, kompania.katalogi) &&
        Objects.equals(this.prosby, kompania.prosby) &&
        Objects.equals(this.zolnierze, kompania.zolnierze);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nrKompanii, idDowodcy, dowodca, plutony, katalogi, prosby, zolnierze);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Kompania {\n");

    sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
    sb.append("    idDowodcy: ").append(toIndentedString(idDowodcy)).append("\n");
    sb.append("    dowodca: ").append(toIndentedString(dowodca)).append("\n");
    sb.append("    plutony: ").append(toIndentedString(plutony)).append("\n");
    sb.append("    katalogi: ").append(toIndentedString(katalogi)).append("\n");
    sb.append("    prosby: ").append(toIndentedString(prosby)).append("\n");
    sb.append("    zolnierze: ").append(toIndentedString(zolnierze)).append("\n");
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

