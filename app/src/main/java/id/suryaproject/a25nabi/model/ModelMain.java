package id.suryaproject.a25nabi.model;

import java.io.Serializable;

public class ModelMain implements Serializable {
    String name ;
    String name_arab;
    String thn_kelahiran;
    String usia;
    String description;
    String tmp;
    String image_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_arab() {
        return name_arab;
    }

    public void setName_arab(String name_arab) {
        this.name_arab = name_arab;
    }

    public String getThn_kelahiran() {
        return thn_kelahiran;
    }

    public void setThn_kelahiran(String thn_kelahiran) {
        this.thn_kelahiran = thn_kelahiran;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
