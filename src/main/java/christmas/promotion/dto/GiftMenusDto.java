package christmas.promotion.dto;

import christmas.promotion.domain.menu.Menu;

import java.util.Map;

public record GiftMenusDto(Map<Menu, Integer> giftMenus) {
    public int getGiftMenusSize() {
        return giftMenus.size();
    }
}