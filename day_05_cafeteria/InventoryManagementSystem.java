package day_5_cafeteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class InventoryManagementSystem {
    List<IngredientIdRange> freshIngredientIdRanges;
    List<Long> availableIngredientIds;

    public InventoryManagementSystem() {
        this.freshIngredientIdRanges = new ArrayList<>();
        this.availableIngredientIds = new ArrayList<>();
    }

    public void addRange(IngredientIdRange range) {
        this.freshIngredientIdRanges.add(range);
    }

    public void addIngredientId(long ingredientId) {
        this.availableIngredientIds.add(ingredientId);
    }

    public long countFreshAvailableIngredients() {
        return this.availableIngredientIds
                .stream()
                .filter(id -> freshIngredientIdRanges
                        .stream()
                        .anyMatch(range -> range.includes(id))
                ).count();
    }

    public long countFreshIngredients() {
        if (freshIngredientIdRanges.isEmpty()) return 0;

        List<IngredientIdRange> sortedRanges = freshIngredientIdRanges.stream()
                .sorted(Comparator.comparingLong(IngredientIdRange::getStartId))
                .toList();

        long totalCount = 0;

        long currentStart = sortedRanges.getFirst().getStartId();
        long currentEnd = sortedRanges.getFirst().getEndId();

        for (int i = 1; i < sortedRanges.size(); i++) {
            IngredientIdRange next = sortedRanges.get(i);

            if (next.getStartId() <= currentEnd + 1) {
                currentEnd = Math.max(currentEnd, next.getEndId());

            } else {
                totalCount += (currentEnd - currentStart + 1);

                currentStart = next.getStartId();
                currentEnd = next.getEndId();
            }
        }

        totalCount += (currentEnd - currentStart + 1);
        return totalCount;
    }
}
