package com.todayletter.todayletter.home.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/api")
public class HomeController {

    // 한 서랍장은 4행 x 4열, 총 16개의 서랍으로 구성한다.
    private static final int DRAWER_SIZE = 4;

    // 사용자가 처음 접속하는 시작 페이지.
    // 여기서는 실제 서랍장을 보여주지 않고, 입장 버튼이 있는 랜딩 화면만 보여준다.
    @GetMapping("/")
    public String landingPage() {
        return "landing/index";
    }

    // 입장 후 보이는 메인 홈 화면.
    // page 파라미터가 없으면 1번 서랍장을 보여준다. 예: /home?page=2
    @GetMapping("/home")
    public String homePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "USER") String writerType,
            Model model
    ) {
        // 음수나 0이 들어와도 최소 1페이지로 보정한다.
        int currentPage = Math.max(page, 1);

        // Mustache 템플릿에서 사용할 화면 데이터를 담는다.
        model.addAttribute("page", currentPage);
        model.addAttribute("rows", createDrawerRows(currentPage));
        model.addAttribute("previousPage", currentPage > 1 ? currentPage - 1 : null);
        model.addAttribute("nextPage", currentPage + 1);
        model.addAttribute("writerType", writerType);

        return "home/index";
    }

    // 현재 페이지 번호를 기준으로 4행 x 4열 서랍 데이터를 만든다.
    // 예: 1페이지는 1~16번, 2페이지는 17~32번 서랍을 가진다.
    private List<DrawerRow> createDrawerRows(int page) {
        List<DrawerRow> rows = new ArrayList<>();

        // 페이지마다 서랍 번호가 이어지도록 첫 번째 서랍 번호를 계산한다.
        int drawerNumber = ((page - 1) * DRAWER_SIZE * DRAWER_SIZE) + 1;

        // 바깥 반복문은 행(row)을 만든다.
        for (int row = 1; row <= DRAWER_SIZE; row++) {
            List<DrawerCell> drawers = new ArrayList<>();

            // 안쪽 반복문은 한 행 안의 4개 서랍(column)을 만든다.
            for (int column = 1; column <= DRAWER_SIZE; column++) {
                drawers.add(new DrawerCell(drawerNumber, row, column));
                drawerNumber++;
            }

            rows.add(new DrawerRow(drawers));
        }

        return rows;
    }

    // Mustache에서 {{#rows}}로 반복할 한 줄(row) 데이터.
    public record DrawerRow(List<DrawerCell> drawers) {
    }

    // Mustache에서 각 서랍의 번호, 행, 열 정보를 꺼내 쓰기 위한 데이터.
    public record DrawerCell(int number, int row, int column) {
    }
}
