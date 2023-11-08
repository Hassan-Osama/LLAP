package code;

public class Constants {

    public int unitPriceFood;
    public int unitPriceMaterial;
    public int unitPriceEnergy;

    public int amountRequestFood;
    public int delayRequestFood;

    public int amountRequestMaterial;
    public int delayRequestMaterial;

    public int amountRequestEnergy;
    public int delayRequestEnergy;

    public int priceBuild1;
    public int foodUseBuild1;
    public int materialUseBuild1;
    public int energyUseBuild1;
    public int prosperityBuild1;


    public int priceBuild2;
    public int foodUseBuild2;
    public int materialUseBuild2;
    public int energyUseBuild2;
    public int prosperityBuild2;

    public Constants(int unitPriceFood, int unitPriceMaterial, int unitPriceEnergy, int amountRequestFood, int delayRequestFood, int amountRequestMaterial, int delayRequestMaterial, int amountRequestEnergy, int delayRequestEnergy, int priceBuild1, int foodUseBuild1, int materialUseBuild1, int energyUseBuild1, int prosperityBuild1, int priceBuild2, int foodUseBuild2, int materialUseBuild2, int energyUseBuild2, int prosperityBuild2) {
        this.unitPriceFood = unitPriceFood;
        this.unitPriceMaterial = unitPriceMaterial;
        this.unitPriceEnergy = unitPriceEnergy;
        this.amountRequestFood = amountRequestFood;
        this.delayRequestFood = delayRequestFood;
        this.amountRequestMaterial = amountRequestMaterial;
        this.delayRequestMaterial = delayRequestMaterial;
        this.amountRequestEnergy = amountRequestEnergy;
        this.delayRequestEnergy = delayRequestEnergy;
        this.priceBuild1 = priceBuild1;
        this.foodUseBuild1 = foodUseBuild1;
        this.materialUseBuild1 = materialUseBuild1;
        this.energyUseBuild1 = energyUseBuild1;
        this.prosperityBuild1 = prosperityBuild1;
        this.priceBuild2 = priceBuild2;
        this.foodUseBuild2 = foodUseBuild2;
        this.materialUseBuild2 = materialUseBuild2;
        this.energyUseBuild2 = energyUseBuild2;
        this.prosperityBuild2 = prosperityBuild2;
    }


    @Override
    public String toString() {
        return "Constants{" +
                "unitPriceFood=" + unitPriceFood +
                ", unitPriceMaterial=" + unitPriceMaterial +
                ", unitPriceEnergy=" + unitPriceEnergy +
                ", amountRequestFood=" + amountRequestFood +
                ", delayRequestFood=" + delayRequestFood +
                ", amountRequestMaterial=" + amountRequestMaterial +
                ", delayRequestMaterial=" + delayRequestMaterial +
                ", amountRequestEnergy=" + amountRequestEnergy +
                ", delayRequestEnergy=" + delayRequestEnergy +
                ", priceBuild1=" + priceBuild1 +
                ", foodUseBuild1=" + foodUseBuild1 +
                ", materialUseBuild1=" + materialUseBuild1 +
                ", energyUseBuild1=" + energyUseBuild1 +
                ", prosperityBuild1=" + prosperityBuild1 +
                ", priceBuild2=" + priceBuild2 +
                ", foodUseBuild2=" + foodUseBuild2 +
                ", materialUseBuild2=" + materialUseBuild2 +
                ", energyUseBuild2=" + energyUseBuild2 +
                ", prosperityBuild2=" + prosperityBuild2 +
                '}';
    }
}
