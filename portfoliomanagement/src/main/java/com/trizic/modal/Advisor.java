package com.trizic.modal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advisor {

    public Advisor() {}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long advisorId;

    private String advisorName;

    private String userName;

    private String password;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "advisor")
    private List<Model> modelList = new ArrayList<>();;


    public Long getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(Long advisorId) {
        this.advisorId = advisorId;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addModel(Model model) {
        model.setAdvisor(this);
        modelList.add(model);
    }

    @Override
    public String toString() {
        return "Advisor{" +
                "advisorId=" + advisorId +
                ", advisorName='" + advisorName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
