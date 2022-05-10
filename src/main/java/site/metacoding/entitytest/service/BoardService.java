package site.metacoding.entitytest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.domain.Board;
import site.metacoding.entitytest.domain.BoardJPQLRepository;
import site.metacoding.entitytest.domain.BoardRepository;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;
import site.metacoding.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {

    // 두 개로 안 쓰고 하나로만 사용하게끔 패턴을 이용해서 만들어봐
    private final BoardRepository boardRepository;
    private final BoardJPQLRepository boardJPQLRepository; // JPQL

    // 비영속 상태로 만드는 법
    // DTO로 리턴하니깐 Open-in-view가 필요가 없다.
    public BoardRespDto 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent());

        return dto;
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardJPQLRepository.mFindDetail(id);

    }

    public List<BoardDetailRespDto> 전체보기() {
        return boardJPQLRepository.mFindAll();
    }
}
