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

public class Katalog {
    @SerializedName("idKatalogu")
    private Integer idKatalogu = null;

    @SerializedName("idKataloguNadrzednego")
    private Integer idKataloguNadrzednego = null;

    @SerializedName("nrKompanii")
    private Integer nrKompanii = null;

    @SerializedName("nrPlutonu")
    private Integer nrPlutonu = null;

    @SerializedName("nazwa")
    private String nazwa = null;

    @SerializedName("_Kompania")
    private Kompania kompania = null;

    @SerializedName("_Pluton")
    private Pluton pluton = null;

    @SerializedName("_KatalogNadrzedny")
    private Katalog katalogNadrzedny = null;

    @SerializedName("_KatalogiPodrzedne")
    private List<Katalog> katalogiPodrzedne = null;

    @SerializedName("_Pliki")
    private List<Plik> pliki = null;

    public Katalog idKatalogu(Integer idKatalogu) {
        this.idKatalogu = idKatalogu;
        return this;
    }

    /**
     * Get idKatalogu
     * @return idKatalogu
     **/

    public Integer getIdKatalogu() {
        return idKatalogu;
    }

    public void setIdKatalogu(Integer idKatalogu) {
        this.idKatalogu = idKatalogu;
    }

    public Katalog idKataloguNadrzednego(Integer idKataloguNadrzednego) {
        this.idKataloguNadrzednego = idKataloguNadrzednego;
        return this;
    }

    /**
     * Get idKataloguNadrzednego
     * @return idKataloguNadrzednego
     **/

    public Integer getIdKataloguNadrzednego() {
        return idKataloguNadrzednego;
    }

    public void setIdKataloguNadrzednego(Integer idKataloguNadrzednego) {
        this.idKataloguNadrzednego = idKataloguNadrzednego;
    }

    public Katalog nrKompanii(Integer nrKompanii) {
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

    public Katalog nrPlutonu(Integer nrPlutonu) {
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

    public Katalog nazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    /**
     * Get nazwa
     * @return nazwa
     **/

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Katalog kompania(Kompania kompania) {
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

    public Katalog pluton(Pluton pluton) {
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

    public Katalog katalogNadrzedny(Katalog katalogNadrzedny) {
        this.katalogNadrzedny = katalogNadrzedny;
        return this;
    }

    /**
     * Get katalogNadrzedny
     * @return katalogNadrzedny
     **/

    public Katalog getKatalogNadrzedny() {
        return katalogNadrzedny;
    }

    public void setKatalogNadrzedny(Katalog katalogNadrzedny) {
        this.katalogNadrzedny = katalogNadrzedny;
    }

    public Katalog katalogiPodrzedne(List<Katalog> katalogiPodrzedne) {
        this.katalogiPodrzedne = katalogiPodrzedne;
        return this;
    }

    public Katalog addKatalogiPodrzedneItem(Katalog katalogiPodrzedneItem) {
        if (this.katalogiPodrzedne == null) {
            this.katalogiPodrzedne = new ArrayList<Katalog>();
        }
        this.katalogiPodrzedne.add(katalogiPodrzedneItem);
        return this;
    }

    /**
     * Get katalogiPodrzedne
     * @return katalogiPodrzedne
     **/

    public List<Katalog> getKatalogiPodrzedne() {
        return katalogiPodrzedne;
    }

    public void setKatalogiPodrzedne(List<Katalog> katalogiPodrzedne) {
        this.katalogiPodrzedne = katalogiPodrzedne;
    }

    public Katalog pliki(List<Plik> pliki) {
        this.pliki = pliki;
        return this;
    }

    public Katalog addPlikiItem(Plik plikiItem) {
        if (this.pliki == null) {
            this.pliki = new ArrayList<Plik>();
        }
        this.pliki.add(plikiItem);
        return this;
    }

    /**
     * Get pliki
     * @return pliki
     **/

    public List<Plik> getPliki() {
        return pliki;
    }

    public void setPliki(List<Plik> pliki) {
        this.pliki = pliki;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Katalog katalog = (Katalog) o;
        return Objects.equals(this.idKatalogu, katalog.idKatalogu) &&
                Objects.equals(this.idKataloguNadrzednego, katalog.idKataloguNadrzednego) &&
                Objects.equals(this.nrKompanii, katalog.nrKompanii) &&
                Objects.equals(this.nrPlutonu, katalog.nrPlutonu) &&
                Objects.equals(this.nazwa, katalog.nazwa) &&
                Objects.equals(this.kompania, katalog.kompania) &&
                Objects.equals(this.pluton, katalog.pluton) &&
                Objects.equals(this.katalogNadrzedny, katalog.katalogNadrzedny) &&
                Objects.equals(this.katalogiPodrzedne, katalog.katalogiPodrzedne) &&
                Objects.equals(this.pliki, katalog.pliki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKatalogu, idKataloguNadrzednego, nrKompanii, nrPlutonu, nazwa, kompania, pluton, katalogNadrzedny, katalogiPodrzedne, pliki);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Katalog {\n");

        sb.append("    idKatalogu: ").append(toIndentedString(idKatalogu)).append("\n");
        sb.append("    idKataloguNadrzednego: ").append(toIndentedString(idKataloguNadrzednego)).append("\n");
        sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
        sb.append("    nrPlutonu: ").append(toIndentedString(nrPlutonu)).append("\n");
        sb.append("    nazwa: ").append(toIndentedString(nazwa)).append("\n");
        sb.append("    kompania: ").append(toIndentedString(kompania)).append("\n");
        sb.append("    pluton: ").append(toIndentedString(pluton)).append("\n");
        sb.append("    katalogNadrzedny: ").append(toIndentedString(katalogNadrzedny)).append("\n");
        sb.append("    katalogiPodrzedne: ").append(toIndentedString(katalogiPodrzedne)).append("\n");
        sb.append("    pliki: ").append(toIndentedString(pliki)).append("\n");
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

