package model;

import model.Skill;

import java.util.Set;

public class Developer {
     private Long id;
     private String name;
     private Set<Skill> skill;
     private Account account;

     public Developer(Long id, String name, Set<Skill> skill, Account account) {
          this.id = id;
          this.name = name;
          this.skill = skill;
          this.account = account;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Set<Skill> getSkill() {
          return skill;
     }

     public void setSkill(Set<Skill> skill) {
          this.skill = skill;
     }

     public Account getAccount() {
          return account;
     }

     public void setAccount(Account account) {
          this.account = account;
     }

     @Override
     public String toString() {
          return "Developer{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", skill=" + skill.toString() +
                  ", account=" + account +
                  '}';
     }
}
