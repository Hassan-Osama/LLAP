package code;

import code.strategy.BFStrategy;
import code.strategy.UCStrategy;

import java.util.ArrayList;
import java.util.List;

public class LLAPSearch extends GenericSearch<State> {

    Constants constants;

    LLAPSearch(State initialState, Constants constants, SearchStrategy<State> strategy) {
        super(initialState, strategy);
        this.constants = constants;
    }

    int numExpandedNodes = 0;

    @Override
    public List<Node<State>> expandNode(Node<State> node) {
        numExpandedNodes += 1;

        State currentState = node.state;

        if (currentState.foodDelay == 0) {
            currentState.food += constants.amountRequestFood;
            currentState.food = Math.min(currentState.food, 50);
            currentState.foodDelay = -1;
        }

        if (currentState.materialDelay == 0) {
            currentState.material += constants.amountRequestMaterial;
            currentState.material = Math.min(currentState.material, 50);
            currentState.materialDelay = -1;
        }

        if (currentState.energyDelay == 0) {
            currentState.energy += constants.amountRequestEnergy;
            currentState.energy = Math.min(currentState.energy, 50);
            currentState.energyDelay = -1;
        }


        if (currentState.isDeadState() || currentState.moneyLeft() <= constants.unitPriceFood + constants.unitPriceMaterial + constants.unitPriceEnergy) return new ArrayList<>();

        ArrayList<Node<State>> newNodes = new ArrayList<>();



        // Requesting Actions
        if (!currentState.isWaitingForOrder() && currentState.moneyLeft() >= constants.unitPriceFood + constants.unitPriceMaterial + constants.unitPriceEnergy) {

            int moneyToSpend = constants.unitPriceFood + constants.unitPriceMaterial + constants.unitPriceEnergy;
            if (currentState.food < 50){

                State s1 = currentState.copy();
                s1.foodDelay = constants.delayRequestFood;
                s1.food -= 1;
                s1.energy -= 1;
                s1.material -= 1;
                s1.moneySpent += moneyToSpend;
                Node<State> n1 = makeNode(
                        s1,
                        node,
                        "RequestFood",
                        node.depth + 1,
                        node.pathCost + moneyToSpend
                );
                newNodes.add(n1);
            }

            if (currentState.material < 50){
                State s2 = currentState.copy();
                s2.materialDelay = constants.delayRequestMaterial;
                s2.food -= 1;
                s2.energy -= 1;
                s2.material -= 1;
                s2.moneySpent += moneyToSpend;

                Node<State> n2 = makeNode(
                        s2,
                        node,
                        "RequestMaterials",
                        node.depth + 1,
                        node.pathCost + moneyToSpend
                );
                newNodes.add(n2);
            }

            if (currentState.energy < 50){
                State s3 = currentState.copy();
                s3.energyDelay = constants.delayRequestEnergy;
                s3.food -= 1;
                s3.energy -= 1;
                s3.material -= 1;
                s3.moneySpent += moneyToSpend;

                Node<State> n3 = makeNode(
                        s3,
                        node,
                        "RequestEnergy",
                        node.depth + 1,
                        node.pathCost + moneyToSpend
                );
                newNodes.add(n3);
            }

        }

        // Wait Action
        if (
                currentState.isWaitingForOrder()
                && currentState.moneyLeft() >= constants.unitPriceFood + constants.unitPriceMaterial + constants.unitPriceEnergy
        ) {
           State s = currentState.copy();

            s.food -= 1;
            s.material -= 1;
            s.energy -= 1;
            s.foodDelay -= 1;
            s.materialDelay -= 1;
            s.energyDelay -= 1;

            int moneyToSpend = constants.unitPriceFood + constants.unitPriceMaterial + constants.unitPriceEnergy;
            s.moneySpent += moneyToSpend;

            Node<State> n = makeNode(s, node, "WAIT", node.depth + 1, node.pathCost + moneyToSpend);
            newNodes.add(n);
        }

        // Building Actions
        {
            int build1Cost = constants.priceBuild1
                    + constants.foodUseBuild1 * constants.unitPriceFood
                    + constants.materialUseBuild1 * constants.unitPriceMaterial
                    + constants.energyUseBuild1 * constants.unitPriceEnergy;
            if (
                    currentState.moneyLeft() >= build1Cost
                    && currentState.food >= constants.foodUseBuild1
                    && currentState.material >= constants.materialUseBuild1
                    && currentState.energy >= constants.energyUseBuild1
            ) {

                State s1 = currentState.copy();
                s1.food -= constants.foodUseBuild1;
                s1.material -= constants.materialUseBuild1;
                s1.energy -= constants.energyUseBuild1;
                s1.moneySpent += build1Cost;
                s1.prosperity += constants.prosperityBuild1;


                s1.foodDelay -= 1;
                s1.materialDelay -= 1;
                s1.energyDelay -= 1;

                Node<State> n = makeNode(s1, node, "BUILD1", node.depth + 1, node.pathCost + build1Cost);
                newNodes.add(n);
            }

            int build2Cost = constants.priceBuild2
                    + constants.foodUseBuild2 * constants.unitPriceFood
                    + constants.materialUseBuild2 * constants.unitPriceMaterial
                    + constants.energyUseBuild2 * constants.unitPriceEnergy;
            if (
                    currentState.moneyLeft() >= build2Cost
                            && currentState.food >= constants.foodUseBuild2
                            && currentState.material >= constants.materialUseBuild2
                            && currentState.energy >= constants.energyUseBuild2
            ) {

                State s2 = currentState.copy();
                s2.food -= constants.foodUseBuild2;
                s2.material -= constants.materialUseBuild2;
                s2.energy -= constants.energyUseBuild2;
                s2.moneySpent += build2Cost;
                s2.prosperity += constants.prosperityBuild2;

                s2.foodDelay -= 1;
                s2.materialDelay -= 1;
                s2.energyDelay -= 1;

                Node<State> n = makeNode(s2, node, "BUILD2", node.depth + 1, node.pathCost + build2Cost);
                newNodes.add(n);
            }

        }

        return newNodes;
    }

    @Override
    public boolean isGoalState(State state) {
        return state.prosperity >= 100;
    }

    public static String solve(String problem, String strategy, boolean visualize) {
        if (getStrategy(strategy) == null) return "NOSOLUTION";
        LLAPSearch searchProblem = new LLAPSearch(
                getInitialState(problem),
                getProblemConstants(problem),
                getStrategy(strategy));

        Node<State> solutionNode = searchProblem.solve();

        if (solutionNode == null) {
            return "NOSOLUTION";
        }


        String solution = solutionNode.operator;
        Node<State> parent = solutionNode.parent;
        while (parent != null) {
            if (parent.operator != null)
                solution = parent.operator + "," + solution;
            parent = parent.parent;
        }
        String answer = solution + ";" + solutionNode.state.moneySpent + ";" + searchProblem.numExpandedNodes;
        System.out.println(answer);
        return answer;
    }

    static State getInitialState(String problem) {
        String[] sections = problem.split(";");

        int initialProsperity = Integer.parseInt(sections[0]);

        String[] foodMaterialEnergy = sections[1].split(",");
        int food = Integer.parseInt(foodMaterialEnergy[0]);
        int material = Integer.parseInt(foodMaterialEnergy[1]);
        int energy = Integer.parseInt(foodMaterialEnergy[2]);

        return new State(
                food,
                energy,
                material,
                initialProsperity,
                0,
                -1,
                -1,
                -1
        );
    }

    static SearchStrategy<State> getStrategy(String strategy) {
        if (strategy.equalsIgnoreCase("bf")) return new BFStrategy<>();
        if (strategy.equalsIgnoreCase("uc")) return new UCStrategy<>();
        return null;
    }

    static Constants getProblemConstants(String problem) {
        String[] sections = problem.split(";");

        String[] unitPrices = sections[2].split(",");
        int unitFoodPrice = Integer.parseInt(unitPrices[0]);
        int unitMaterialPrice = Integer.parseInt(unitPrices[1]);
        int unitEnergyPrice = Integer.parseInt(unitPrices[2]);

        String[] foodConstants = sections[3].split(",");
        int amountRequestFood = Integer.parseInt(foodConstants[0]);
        int delayRequestFood = Integer.parseInt(foodConstants[1]);

        String[] materialConstants = sections[4].split(",");
        int amountRequestMaterial = Integer.parseInt(materialConstants[0]);
        int delayRequestMaterial = Integer.parseInt(materialConstants[1]);


        String[] energyConstants = sections[5].split(",");
        int amountRequestEnergy = Integer.parseInt(energyConstants[0]);
        int delayRequestEnergy = Integer.parseInt(energyConstants[1]);

        String[] build1Constants = sections[6].split(",");

        int priceBuild1 = Integer.parseInt(build1Constants[0]);
        int foodUseBuild1 = Integer.parseInt(build1Constants[1]);
        int materialUseBuild1 = Integer.parseInt(build1Constants[2]);
        int energyUseBuild1 = Integer.parseInt(build1Constants[3]);
        int prosperityBuild1 = Integer.parseInt(build1Constants[4]);

        String[] build2Constants = sections[7].split(",");

        int priceBuild2 = Integer.parseInt(build2Constants[0]);
        int foodUseBuild2 = Integer.parseInt(build2Constants[1]);
        int materialUseBuild2 = Integer.parseInt(build2Constants[2]);
        int energyUseBuild2 = Integer.parseInt(build2Constants[3]);
        int prosperityBuild2 = Integer.parseInt(build2Constants[4]);

        return new Constants(
                unitFoodPrice,
                unitMaterialPrice,
                unitEnergyPrice,
                amountRequestFood,
                delayRequestFood,
                amountRequestMaterial,
                delayRequestMaterial,
                amountRequestEnergy,
                delayRequestEnergy,
                priceBuild1,
                foodUseBuild1,
                materialUseBuild1,
                energyUseBuild1,
                prosperityBuild1,
                priceBuild2,
                foodUseBuild2,
                materialUseBuild2,
                energyUseBuild2,
                prosperityBuild2
        );
    }

}
