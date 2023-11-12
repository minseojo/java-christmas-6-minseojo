package christmas.promotion.domain.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MenuBoardTest {

    private MenuBoard menuBoard;

    @BeforeEach
    void beforeEach() {
        menuBoard = new MenuBoard();
    }

    @Test
    @DisplayName("s")
    void 정상_케이스() {

        for (EventfulMenu eventfulMenu : menuBoard.getMenus()) {
            if (eventfulMenu.getMenu() instanceof  Beverage) {
                System.out.println("eventfulMenu " + eventfulMenu.getName());
            }
            if (eventfulMenu.getMenu().getClass() == Beverage.class) {
                System.out.println("eventfulMenu " + eventfulMenu.getName());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("foundMenuName")
    @DisplayName("존재하는 메뉴 이름으로 찾기, 성공")
    void 존재하는_메뉴_찾기(String input) {
        EventfulMenu menu = menuBoard.findMenu(input);
        assertNotNull(input);
        assertEquals(menu.getName(), input);
    }

    static Stream<String> foundMenuName() {
        return Stream.of(
                "양송이수프",
                "시저샐러드",
                "해산물파스타",
                "크리스마스파스타",
                "아이스크림",
                "샴페인"
        );
    }

    @ParameterizedTest
    @MethodSource("notFoundMenuName")
    @DisplayName("존재하지 않는 메뉴 이름으로 찾기, 예외")
    void 존재하지_않는_메뉴_찾기(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> menuBoard.findMenu(input));
        assertEquals("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    // 유효하지 않은 메뉴 이름에 대한 MethodSource
    static Stream<String> notFoundMenuName() {
        return Stream.of(
                "",
                "notFound",
                "햄버거",
                "감자탕",
                "a",
                "  "
        );
    }
}