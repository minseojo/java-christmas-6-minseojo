package christmas.promotion.dto;

import christmas.promotion.domain.event.Event;

import java.util.Map;


public record BenefitDetailsDto(Map<Event, Double> benefitDetails) {

    public Map<Event, Double> getBenefitDetails() {
        return benefitDetails;
    }
}
