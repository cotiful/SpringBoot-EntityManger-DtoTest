package site.metacoding.entitytest.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.service.BoardService;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;
import site.metacoding.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/{id}")
    public ResponseEntity<?> 상세보기(@PathVariable Integer id) {
        BoardRespDto dto = boardService.상세보기(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/board/{id}/love")
    public ResponseEntity<?> 좋아요포함상세보기(@PathVariable Integer id) {
        BoardDetailRespDto dto = boardService.좋아요포함상세보기(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
