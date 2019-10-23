package com.example.company.API.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Zolnierz {
    @SerializedName("idOsoby")
    private Integer idOsoby = null;

    @SerializedName("nrKompanii")
    private Integer nrKompanii = null;

    @SerializedName("nrPlutonu")
    private Integer nrPlutonu = null;

    @SerializedName("imie")
    private String imie = null;

    @SerializedName("nazwisko")
    private String nazwisko = null;

    @SerializedName("funkcyjny")
    private Boolean funkcyjny = null;

    @SerializedName("_Kompania")
    private Kompania kompania = null;

    @SerializedName("_Pluton")
    private Pluton pluton = null;

    @SerializedName("_Kompanie")
    private List<Kompania> kompanie = null;

    @SerializedName("_Plutony")
    private List<Pluton> plutony = null;

    @SerializedName("_ZatwierdzeniaProsb")
    private List<Prosba> zatwierdzeniaProsb = null;

    @SerializedName("_ZgloszoneProsby")
    private List<Prosba> zgloszoneProsby = null;

    public Zolnierz idOsoby(Integer idOsoby) {
      this.idOsoby = idOsoby;
      return this;
    }

    /**
     * Get idOsoby
     * @return idOsoby
     **/

    public Integer getIdOsoby() {
      return idOsoby;
    }

    public void setIdOsoby(Integer idOsoby) {
      this.idOsoby = idOsoby;
    }

    public Zolnierz nrKompanii(Integer nrKompanii) {
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

    public Zolnierz nrPlutonu(Integer nrPlutonu) {
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

    public Zolnierz imie(String imie) {
      this.imie = imie;
      return this;
    }

    /**
     * Get imie
     * @return imie
     **/

    public String getImie() {
      return imie;
    }

    public void setImie(String imie) {
      this.imie = imie;
    }

    public Zolnierz nazwisko(String nazwisko) {
      this.nazwisko = nazwisko;
      return this;
    }

    /**
     * Get nazwisko
     * @return nazwisko
     **/

    public String getNazwisko() {
      return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
      this.nazwisko = nazwisko;
    }

    public Zolnierz funkcyjny(Boolean funkcyjny) {
      this.funkcyjny = funkcyjny;
      return this;
    }

    /**
     * Get funkcyjny
     * @return funkcyjny
     **/

    public Boolean isFunkcyjny() {
      return funkcyjny;
    }

    public void setFunkcyjny(Boolean funkcyjny) {
      this.funkcyjny = funkcyjny;
    }

    public Zolnierz kompania(Kompania kompania) {
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

    public Zolnierz pluton(Pluton pluton) {
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

    public Zolnierz kompanie(List<Kompania> kompanie) {
      this.kompanie = kompanie;
      return this;
    }

    public Zolnierz addKompanieItem(Kompania kompanieItem) {
      if (this.kompanie == null) {
        this.kompanie = new ArrayList<Kompania>();
      }
      this.kompanie.add(kompanieItem);
      return this;
    }

    /**
     * Get kompanie
     * @return kompanie
     **/

    public List<Kompania> getKompanie() {
      return kompanie;
    }

    public void setKompanie(List<Kompania> kompanie) {
      this.kompanie = kompanie;
    }

    public Zolnierz plutony(List<Pluton> plutony) {
      this.plutony = plutony;
      return this;
    }

    public Zolnierz addPlutonyItem(Pluton plutonyItem) {
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

    public Zolnierz zatwierdzeniaProsb(List<Prosba> zatwierdzeniaProsb) {
      this.zatwierdzeniaProsb = zatwierdzeniaProsb;
      return this;
    }

    public Zolnierz addZatwierdzeniaProsbItem(Prosba zatwierdzeniaProsbItem) {
      if (this.zatwierdzeniaProsb == null) {
        this.zatwierdzeniaProsb = new ArrayList<Prosba>();
      }
      this.zatwierdzeniaProsb.add(zatwierdzeniaProsbItem);
      return this;
    }

    /**
     * Get zatwierdzeniaProsb
     * @return zatwierdzeniaProsb
     **/

    public List<Prosba> getZatwierdzeniaProsb() {
      return zatwierdzeniaProsb;
    }

    public void setZatwierdzeniaProsb(List<Prosba> zatwierdzeniaProsb) {
      this.zatwierdzeniaProsb = zatwierdzeniaProsb;
    }

    public Zolnierz zgloszoneProsby(List<Prosba> zgloszoneProsby) {
      this.zgloszoneProsby = zgloszoneProsby;
      return this;
    }

    public Zolnierz addZgloszoneProsbyItem(Prosba zgloszoneProsbyItem) {
      if (this.zgloszoneProsby == null) {
        this.zgloszoneProsby = new ArrayList<Prosba>();
      }
      this.zgloszoneProsby.add(zgloszoneProsbyItem);
      return this;
    }

    /**
     * Get zgloszoneProsby
     * @return zgloszoneProsby
     **/

    public List<Prosba> getZgloszoneProsby() {
      return zgloszoneProsby;
    }

    public void setZgloszoneProsby(List<Prosba> zgloszoneProsby) {
      this.zgloszoneProsby = zgloszoneProsby;
    }


    @Override
    public boolean equals(java.lang.Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Zolnierz zolnierz = (Zolnierz) o;
      return Objects.equals(this.idOsoby, zolnierz.idOsoby) &&
              Objects.equals(this.nrKompanii, zolnierz.nrKompanii) &&
              Objects.equals(this.nrPlutonu, zolnierz.nrPlutonu) &&
              Objects.equals(this.imie, zolnierz.imie) &&
              Objects.equals(this.nazwisko, zolnierz.nazwisko) &&
              Objects.equals(this.funkcyjny, zolnierz.funkcyjny) &&
              Objects.equals(this.kompania, zolnierz.kompania) &&
              Objects.equals(this.pluton, zolnierz.pluton) &&
              Objects.equals(this.kompanie, zolnierz.kompanie) &&
              Objects.equals(this.plutony, zolnierz.plutony) &&
              Objects.equals(this.zatwierdzeniaProsb, zolnierz.zatwierdzeniaProsb) &&
              Objects.equals(this.zgloszoneProsby, zolnierz.zgloszoneProsby);
    }

    @Override
    public int hashCode() {
      return Objects.hash(idOsoby, nrKompanii, nrPlutonu, imie, nazwisko, funkcyjny, kompania, pluton, kompanie, plutony, zatwierdzeniaProsb, zgloszoneProsby);
    }


    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Zolnierz {\n");

      sb.append("    idOsoby: ").append(toIndentedString(idOsoby)).append("\n");
      sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
      sb.append("    nrPlutonu: ").append(toIndentedString(nrPlutonu)).append("\n");
      sb.append("    imie: ").append(toIndentedString(imie)).append("\n");
      sb.append("    nazwisko: ").append(toIndentedString(nazwisko)).append("\n");
      sb.append("    funkcyjny: ").append(toIndentedString(funkcyjny)).append("\n");
      sb.append("    kompania: ").append(toIndentedString(kompania)).append("\n");
      sb.append("    pluton: ").append(toIndentedString(pluton)).append("\n");
      sb.append("    kompanie: ").append(toIndentedString(kompanie)).append("\n");
      sb.append("    plutony: ").append(toIndentedString(plutony)).append("\n");
      sb.append("    zatwierdzeniaProsb: ").append(toIndentedString(zatwierdzeniaProsb)).append("\n");
      sb.append("    zgloszoneProsby: ").append(toIndentedString(zgloszoneProsby)).append("\n");
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

