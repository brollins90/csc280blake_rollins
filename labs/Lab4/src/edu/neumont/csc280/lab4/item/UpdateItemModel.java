package edu.neumont.csc280.lab4.item;


/**
 * Created by blakerollins on 10/29/14.
 */
public class UpdateItemModel {

    public AuctionItem item;
    public ValidationResult validationResults;

    public UpdateItemModel() {
        validationResults = new ValidationResult();
    }

    public UpdateItemModel(AuctionItem item) {
        this();
        this.item = item;
    }

    public AuctionItem getItem() {
        return item;
    }

    public void setItem(AuctionItem item) {
        this.item = item;
    }

    public ValidationResult getValidationResults() {
        return validationResults;
    }

    public void addValidationResult(ValidationResult result) {
        this.validationResults.combine(result);
    }
}


