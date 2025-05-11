package guru.qa.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GlossaryInner getGlossary() {
        return glossary;
    }

    public void setGlossary(GlossaryInner glossary) {
        this.glossary = glossary;
    }

    private String title;

    @SerializedName("ID")
    private Integer id;
    private GlossaryInner glossary;

}
