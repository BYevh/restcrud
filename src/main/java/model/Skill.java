package model;

public class Skill implements Comparable<Skill> {
    private Long id;
    private String nameOfSkill;

    public Skill(Long id, String nameOfSkill) {
        this.id = id;
        this.nameOfSkill = nameOfSkill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfSkill() {
        return nameOfSkill;
    }

    public void setNameOfSkill(String nameOfSkill) {
        this.nameOfSkill = nameOfSkill;
    }

    public int compareTo(Skill o) {
        return id.compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "\nSkill { " +
                "id=" + id +
                ", nameOfSkill='" + nameOfSkill + '\'' +
                "}";
    }
}
