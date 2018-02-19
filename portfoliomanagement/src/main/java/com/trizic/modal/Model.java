package com.trizic.modal;


import com.trizic.modal.enums.ModelType;
import com.trizic.modal.enums.RebalanceFrequency;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Model {

    public Model(){}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Integer cashHoldingPercentage;
    @NotNull
    private Integer driftPercentage;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RebalanceFrequency rebalanceFrequency;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private List<AssetAllocation> assetAllocationList= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCashHoldingPercentage() {
        return cashHoldingPercentage;
    }

    public void setCashHoldingPercentage(Integer cashHoldingPercentage) {
        this.cashHoldingPercentage = cashHoldingPercentage;
    }

    public Integer getDriftPercentage() {
        return driftPercentage;
    }

    public void setDriftPercentage(Integer driftPercentage) {
        this.driftPercentage = driftPercentage;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public RebalanceFrequency getRebalanceFrequency() {
        return rebalanceFrequency;
    }

    public void setRebalanceFrequency(RebalanceFrequency rebalanceFrequency) {
        this.rebalanceFrequency = rebalanceFrequency;
    }

    public List<AssetAllocation> getAssetAllocationList() {
        return assetAllocationList;
    }

    public void setAssetAllocationList(List<AssetAllocation> assetAllocationList) {
        this.assetAllocationList = assetAllocationList;
    }

    public void addAssetAllocation(AssetAllocation assetAllocation){
        assetAllocationList.add(assetAllocation);
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
