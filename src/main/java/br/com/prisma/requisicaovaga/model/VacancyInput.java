package br.com.prisma.requisicaovaga.model;

import java.util.Objects;

public class VacancyInput {

    private String id;
    private String title;
    private String location;
    private String typeSituation;
    private int code;
    private String typeReason;

    public VacancyInput() {
    }

    public VacancyInput(String id, String title, String location, String typeSituation, int code, String typeReason) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.typeSituation = typeSituation;
        this.code = code;
        this.typeReason = typeReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeSituation() {
        return typeSituation;
    }

    public void setTypeSituation(String typeSituation) {
        this.typeSituation = typeSituation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTypeReason() {
        return typeReason;
    }

    public void setTypeReason(String typeReason) {
        this.typeReason = typeReason;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VacancyInput other = (VacancyInput) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancyInput{" + "id=" + id + ", title=" + title + ", location=" + location + ", typeSituation=" + typeSituation + ", code=" + code + ", typeReason=" + typeReason + '}';
    }

}
