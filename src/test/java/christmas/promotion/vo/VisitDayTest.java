package christmas.promotion.vo;

import christmas.promotion.exception.VisitDayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


class VisitDayTest {


    @ParameterizedTest
    @ValueSource(strings = {
            "1", // 시작
            "12",
            "25",
            "31" // 끝
    })
    @DisplayName("유저 방문 날짜 입력 정상 케이스")
    void visitDay_정상(String input) {
        VisitDay visitDay = new VisitDay(input);
        assertThat(visitDay.getVisitDay()).isEqualTo(Integer.parseInt(input));

        VisitDay newVisitDay = new VisitDay(input);

        assertThat(visitDay.getVisitDay()).isEqualTo(newVisitDay.getVisitDay()); // 동등성 성립 O
        assertThat(visitDay).hasSameHashCodeAs(newVisitDay); // 같은 값을 가지면, 해시 코드 같음
        assertThat(visitDay).isNotSameAs(newVisitDay); // 동일성 성립 X
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "as",
            "",
            " ",
            "-1",
            "0",
            "32",
            "213"
    })
    @DisplayName("유저 방문 날짜 입력 예외 케이스")
    void visitDay_예외(String input) {
        assertThatThrownBy(() -> new VisitDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VisitDayException.ErrorMessage.VISIT_DAY_ERROR.getMessage());
    }

    @Test
    @DisplayName("동등성 테스트")
    void equalsTest() {
        VisitDay visitDay1 = new VisitDay("1");
        VisitDay visitDay2 = new VisitDay("1");
        VisitDay visitDay3 = new VisitDay("2");

        assertAll(
                () -> assertThat(visitDay1).isEqualTo(visitDay1),
                () -> assertThat(visitDay1).isEqualTo(visitDay2).isEqualTo(visitDay1),
                () -> assertThat(visitDay2).isEqualTo(visitDay1),
                () -> assertThat(visitDay1).isNotEqualTo(visitDay3),
                () -> assertThat(visitDay1).isNotEqualTo(null)
        );
    }

    @Test
    @DisplayName("해시 코드, 동일성 테스트")
    void hashCodeTest() {
        VisitDay visitDay1 = new VisitDay("1");
        VisitDay visitDay2 = new VisitDay("1");

        assertThat(visitDay1).isEqualTo(visitDay2);
        assertThat(visitDay1.hashCode()).isEqualTo(visitDay2.hashCode());

        assertThat(visitDay1).isNotSameAs(visitDay2);
    }
}