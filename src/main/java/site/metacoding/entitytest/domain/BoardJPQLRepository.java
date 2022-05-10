package site.metacoding.entitytest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import site.metacoding.entitytest.web.dto.BoardDetailRespDto;

@RequiredArgsConstructor
@Repository
// 인터페이스는 변수가 전부다 static 이다.
public class BoardJPQLRepository {

    private final EntityManager em;

    // 1, 제목1, 내용1, true
    public BoardDetailRespDto mFindDetail(Integer id) {
        String sql = "SELECT b.*, true FROM Board b WHERE id = ?";
        Query query = em.createNativeQuery(sql) // createQuery -> DB 모델과 타입이 같아야한다. createNativeQuery -> 내 마음대로 쿼리
                .setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult(); // 버퍼로 singleResult가 쏨 , Mapping을 안해주니 Object로 변경해서

        // 컬럼에 맞게 타입을 캐스팅해서 직접 넣어줌
        Integer boardId = (Integer) result[0];
        String title = (String) result[1];
        String content = (String) result[2];
        boolean isLove = (Boolean) result[3];

        BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);

        return dto;
    }

    public List<BoardDetailRespDto> mFindAll() {
        List<BoardDetailRespDto> dtos = new ArrayList<>();

        String sql = "SELECT b.*, true FROM Board b";
        Query query = em.createNativeQuery(sql);

        // 여러건일 경우
        List<Object[]> results = (List<Object[]>) query.getResultList();

        for (Object[] result : results) {
            Integer boardId = (Integer) result[0];
            String title = (String) result[1];
            String content = (String) result[2];
            boolean isLove = (Boolean) result[3];
            BoardDetailRespDto dto = new BoardDetailRespDto(boardId, title, content, isLove);
            dtos.add(dto);
        }

        return dtos;
    }

    public List<BoardDetailRespDto> mFindAllQLRM() {
        String sql = "SELECT b.*, true FROM Board b";
        Query query = em.createNativeQuery(sql);

        JpaResultMapper mapper = new JpaResultMapper();
        List<BoardDetailRespDto> dtos = mapper.list(query, BoardDetailRespDto.class);

        return dtos;
    }

}
