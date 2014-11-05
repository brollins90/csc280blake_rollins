package edu.neumont.csc280.lab4.item;


/**
 * Created by blakerollins on 10/29/14.
 */
public class UpdateItemModel {

    public AuctionItemDB item;
    public ValidationResult validationResult;

    public UpdateItemModel() {
        validationResult = new ValidationResult();
    }

    public UpdateItemModel(AuctionItemDB item) {
        this();
        this.item = item;
    }

    public AuctionItemDB getItem() {
        return item;
    }

    public void setItem(AuctionItemDB item) {
        this.item = item;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void addValidationResult(ValidationResult result) {
        this.validationResult.combine(result);
    }
}


