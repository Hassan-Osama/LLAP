package code;


import java.util.Objects;

public class State {

    static int START_BUDGET = 100000;

    public int food;
    public int energy;
    public int material;
    public int prosperity;
    public int moneySpent;
    public int foodDelay;
    public int materialDelay;
    public int energyDelay;

    public State(
            int food,
            int energy,
            int material,
            int prosperity,
            int moneySpent,
            int foodDelay,
            int materialDelay,
            int energyDelay
    ) {
        this.food = food;
        this.energy = energy;
        this.material = material;
        this.prosperity = prosperity;
        this.moneySpent = moneySpent;
        this.foodDelay = foodDelay;
        this.materialDelay = materialDelay;
        this.energyDelay = energyDelay;
    }

    State copy() {
        return new State(
                food,
                energy,
                material,
                prosperity,
                moneySpent,
                foodDelay,
                materialDelay,
                energyDelay
        );
    }

    int moneyLeft() {
        return START_BUDGET - moneySpent;
    }

    boolean isDeadState() {
        return
                food <= 0
                || material <= 0
                || energy <= 0
                || moneySpent >= START_BUDGET;
    }

    boolean isWaitingForOrder() {
        return
                foodDelay > 0
                || materialDelay > 0
                || energyDelay > 0;
    }

    @Override
    public String toString() {
        return "State{" +
                "food=" + food +
                ", energy=" + energy +
                ", material=" + material +
                ", prosperity=" + prosperity +
                ", moneySpent=" + moneySpent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return food == state.food && energy == state.energy && material == state.material && prosperity == state.prosperity && moneySpent == state.moneySpent && foodDelay == state.foodDelay && materialDelay == state.materialDelay && energyDelay == state.energyDelay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, energy, material, prosperity, moneySpent, foodDelay, materialDelay, energyDelay);
    }
}
