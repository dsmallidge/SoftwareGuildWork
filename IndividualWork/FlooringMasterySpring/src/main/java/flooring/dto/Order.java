/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooring.dto;

/**
 *
 * @author apprentice
 */
public class Order {
    
    private int orderNumber;
    private String state;
    private String customerName;
    private String date;
    private String productType;
    private double taxRate;
    private double area;
    private double costPerSquareFoot;
    private double laborCostPerSquareFoot;
    private double materialCost;
    private double laborCost;
    private double tax;
    private double total;
    
    
    
   public Order(int orderNumber, String customerName, String state, double taxRate, String productType, double area, 
           double costPerSquareFoot, double laborCostPerSquareFoot) {
       this.orderNumber = orderNumber;
       this.customerName = customerName;
       this.state = state;
       this.taxRate = taxRate;
       this.area = area;
       this.costPerSquareFoot = costPerSquareFoot;
       this.laborCostPerSquareFoot = laborCostPerSquareFoot;
       this.tax = tax;
       this.total = total;
       this.productType = productType;
       
   } 
   
   public Order () {
       
   }

    // public Order() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the costPerSquareFoot
     */
    public double getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    /**
     * @param costPerSquareFoot the costPerSquareFoot to set
     */
    public void setCostPerSquareFoot(double costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    /**
     * @return the laborCostPerSquareFoot
     */
    public double getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    /**
     * @param laborCostPerSquareFoot the laborCostPerSquareFoot to set
     */
    public void setLaborCostPerSquareFoot(double laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    /**
     * @return the materialCost
     */
    public double getMaterialCost() {
        return (area * costPerSquareFoot);
    }

    
    /**
     * @return the laborCost
     */
    public double getLaborCost() {
        return (area * laborCostPerSquareFoot);
    }

    /**
     * @return the tax
     */
    public double getTax() {
        return ((taxRate / 100) * (getLaborCost() + getMaterialCost()));
    }


    /**
     * @return the total
     */
    public double getTotal() {
        return (getLaborCost() + getMaterialCost() + getTax());
    }


    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
}
