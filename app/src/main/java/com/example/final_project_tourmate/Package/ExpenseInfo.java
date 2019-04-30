package com.example.final_project_tourmate.Package;

public class ExpenseInfo {

    private String expenseDescription;
    private String tourCost;
    private String costId;


    public ExpenseInfo() {
    }

    public ExpenseInfo(String expenseDescription, String tourCost) {
        this.expenseDescription = expenseDescription;
        this.tourCost = tourCost;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public String getTourCost() {
        return tourCost;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

}
