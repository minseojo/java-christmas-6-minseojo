package christmas.promotion.dto;

import christmas.promotion.domain.event.Event;

import java.util.Map;


public record EventBenefitsDto(Map<Event, christmas.promotion.vo.Price> eventBenefits) {
}
