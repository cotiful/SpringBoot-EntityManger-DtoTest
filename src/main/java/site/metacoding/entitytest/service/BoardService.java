package site.metacoding.entitytest.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.domain.Board;
import site.metacoding.entitytest.domain.BoardImplRepository;
import site.metacoding.entitytest.domain.BoardRepository;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;
import site.metacoding.entitytest.web.dto.BoardRespDto;

@RequiredArgsConstructor
@Service
public class BoardService {

    // 두 개로 안 쓰고 하나로만 사용하게끔 패턴을 이용해서 만들어봐
    private final BoardRepository boardRepository;
    private final BoardImplRepository boardImplRepository; // JPQL

    // 비영속 상태로 만드는 법
    // DTO로 리턴하니깐 Open-in-view가 필요가 없다.
    public BoardRespDto 상세보기(Integer id) {
        Board boardEntity = boardRepository.findById(id).get();

        BoardRespDto dto = new BoardRespDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent());

        return dto;
    }

    public BoardDetailRespDto 좋아요포함상세보기(Integer id) {
        return boardImplRepository.mFindDetail(id);
    }
}
