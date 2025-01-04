package leetcode.graph;

import java.util.*;

public class FindAllRecipes {

    public static void main(String[] args) {
//        String[] recipes = {"bread","sandwich","burger"};
//        List<List<String>> ingredients = List.of(List.of("yeast","flour"), List.of("bread","meat"), List.of("sandwich","meat","bread"));
//        String[] supplies = {"yeast","flour","meat"};
        String[] recipes = {"bread"};
        List<List<String>> ingredients = List.of(List.of("yeast","flour"));
        String[] supplies = {"yeast"};

        System.out.println(findAllRecipes(recipes, ingredients, supplies));
    }

    public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Boolean> map = new HashMap<>();
        Map<String, List<String>> recipeIngredients = new HashMap<>();
        Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));

        for (int i = 0; i < recipes.length; i++) {
            recipeIngredients.put(recipes[i], ingredients.get(i));
        }

        for (String recipe : recipes) {
            if (!map.containsKey(recipe)) {
                canMakeRecipe(recipe, recipeIngredients, map, supplySet, new HashSet<>());
            }
        }

        List<String> result = new ArrayList<>();
        for (String recipe : recipes) {
            if (map.getOrDefault(recipe, false)) result.add(recipe);
        }

        return result;
    }

    private static boolean canMakeRecipe(
            String recipe,
            Map<String, List<String>> recipeIngredients,
            Map<String, Boolean> map,
            Set<String> supplySet,
            Set<String> visited) {
        if (map.containsKey(recipe)) return map.get(recipe);
        if (visited.contains(recipe)) return false;

        visited.add(recipe);

        List<String> ingredients = recipeIngredients.get(recipe);
        if (ingredients == null) {
            map.put(recipe, false);
            return false;
        }

        boolean canMake = true;
        for (String ingredient : ingredients) {
            if (supplySet.contains(ingredient)) continue;
            if (!recipeIngredients.containsKey(ingredient) || !canMakeRecipe(ingredient, recipeIngredients, map, supplySet, visited)) {
                canMake = false;
                break;
            }
        }

        visited.remove(recipe);

        if (canMake) {
            map.put(recipe, true);
            supplySet.add(recipe);
        } else {
            map.put(recipe, false);
        }

        return canMake;
    }

}
