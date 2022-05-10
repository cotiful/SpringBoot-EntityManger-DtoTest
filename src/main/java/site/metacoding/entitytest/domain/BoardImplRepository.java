package site.metacoding.entitytest.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
// 인터페이스는 변수가 전부다 static 이다.
public class BoardImplRepository {

    private final EntityManager em;

    // 1, 제목1, 내용1, true
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM Board b WHERE id = ?";
        TypedQuery<BoardDetailRespDto> query = em.createQuery(sql, BoardDetailRespDto.class)
                .setParameter(1, id);

        BoardDetailRespDto dto = query.getSingleResult();
        return dto;
    }

}
