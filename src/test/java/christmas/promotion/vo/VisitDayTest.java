package christmas.promotion.vo;

import christmas.promotion.exception.VisitDayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


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
}