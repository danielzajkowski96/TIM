package com.example.company.API.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Plik
 */

public class Plik {

    @SerializedName("idPliku")
    private String idPliku = null;

    @SerializedName("idKatalogu")
    private Integer idKatalogu = null;

    @SerializedName("rozszerzenie")
    private String rozszerzenie = null;

    @SerializedName("naglowek")
    private String naglowek = null;

    @SerializedName("opis")
    private String opis = null;

    @SerializedName("dodano")
    private OffsetDateTime dodano = null;

    @SerializedName("_Katalog")
    private Katalog _katalog = null;

    @SerializedName("nrKompanii")
    private Integer nrKompanii = null;

    @SerializedName("_Kompania")
    private Kompania _kompania = null;
    public Plik idPliku(String idPliku) {
        this.idPliku = idPliku;
        return this;
    }



    /**
     * Get idPliku
     * @return idPliku
     **/

    public String getIdPliku() {
        return idPliku;
    }
    public void setIdPliku(String idPliku) {
        this.idPliku = idPliku;
    }
    public Plik idKatalogu(Integer idKatalogu) {
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
    public Plik rozszerzenie(String rozszerzenie) {
        this.rozszerzenie = rozszerzenie;
        return this;
    }



    /**
     * Get rozszerzenie
     * @return rozszerzenie
     **/

    public String getRozszerzenie() {
        return rozszerzenie;
    }
    public void setRozszerzenie(String rozszerzenie) {
        this.rozszerzenie = rozszerzenie;
    }
    public Plik naglowek(String naglowek) {
        this.naglowek = naglowek;
        return this;
    }



    /**
     * Get naglowek
     * @return naglowek
     **/

    public String getNaglowek() {
        return naglowek;
    }
    public void setNaglowek(String naglowek) {
        this.naglowek = naglowek;
    }
    public Plik opis(String opis) {
        this.opis = opis;
        return this;
    }



    /**
     * Get opis
     * @return opis
     **/

    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public Plik dodano(OffsetDateTime dodano) {
        this.dodano = dodano;
        return this;
    }



    /**
     * Get dodano
     * @return dodano
     **/

    public OffsetDateTime getDodano() {
        return dodano;
    }
    public void setDodano(OffsetDateTime dodano) {
        this.dodano = dodano;
    }
    public Plik _katalog(Katalog _katalog) {
        this._katalog = _katalog;
        return this;
    }



    /**
     * Get _katalog
     * @return _katalog
     **/

    public Katalog getKatalog() {
        return _katalog;
    }
    public void setKatalog(Katalog _katalog) {
        this._katalog = _katalog;
    }
    public Plik nrKompanii(Integer nrKompanii) {
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
    public Plik _kompania(Kompania _kompania) {
        this._kompania = _kompania;
        return this;
    }



    /**
     * Get _kompania
     * @return _kompania
     **/

    public Kompania getKompania() {
        return _kompania;
    }
    public void setKompania(Kompania _kompania) {
        this._kompania = _kompania;
    }
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plik plik = (Plik) o;
        return Objects.equals(this.idPliku, plik.idPliku) &&
                Objects.equals(this.idKatalogu, plik.idKatalogu) &&
                Objects.equals(this.rozszerzenie, plik.rozszerzenie) &&
                Objects.equals(this.naglowek, plik.naglowek) &&
                Objects.equals(this.opis, plik.opis) &&
                Objects.equals(this.dodano, plik.dodano) &&
                Objects.equals(this._katalog, plik._katalog) &&
                Objects.equals(this.nrKompanii, plik.nrKompanii) &&
                Objects.equals(this._kompania, plik._kompania);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idPliku, idKatalogu, rozszerzenie, naglowek, opis, dodano, _katalog, nrKompanii, _kompania);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Plik {\n");

        sb.append("    idPliku: ").append(toIndentedString(idPliku)).append("\n");
        sb.append("    idKatalogu: ").append(toIndentedString(idKatalogu)).append("\n");
        sb.append("    rozszerzenie: ").append(toIndentedString(rozszerzenie)).append("\n");
        sb.append("    naglowek: ").append(toIndentedString(naglowek)).append("\n");
        sb.append("    opis: ").append(toIndentedString(opis)).append("\n");
        sb.append("    dodano: ").append(toIndentedString(dodano)).append("\n");
        sb.append("    _katalog: ").append(toIndentedString(_katalog)).append("\n");
        sb.append("    nrKompanii: ").append(toIndentedString(nrKompanii)).append("\n");
        sb.append("    _kompania: ").append(toIndentedString(_kompania)).append("\n");
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