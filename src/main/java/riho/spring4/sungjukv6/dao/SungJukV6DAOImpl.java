package riho.spring4.sungjukv6.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import riho.spring4.sungjukv6.model.SungJukVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("sjdao")
public class SungJukV6DAOImpl implements SungJukV4DAO {

    private static final Logger logger = LogManager.getLogger(SungJukV6DAOImpl.class);
    // debug, info, warn , error, fatal   // 인포 아니면 에러 많이씀
    private JdbcTemplate jdbcTemplate;

    // jdbc.properties 에 정의한 SQL 가져오기
    @Value("#{jdbc['insertSQL']}") private String insertSQL;
    @Value("#{jdbc['selectSQL']}") private String selectSQL;
    @Value("#{jdbc['selectOneSQL']}") private String selectOneSQL;
    @Value("#{jdbc['updateSQL']}") private String updateSQL;
    @Value("#{jdbc['deleteSQL']}") private String deleteSQL;

    @Autowired
    public SungJukV6DAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public int insertSungJuk(SungJukVO sj) {
        int cnt = -1;

        try {
            //매개변수 정의
            Object[] params = new Object[]{
                    sj.getName(),sj.getKor(),sj.getEng(),sj.getMat(),
                    sj.getTot(),sj.getAvg(), sj.getGrd()+""
            };
            cnt = jdbcTemplate.update(insertSQL,params);
        } catch (Exception ex){
            logger.error("insertSungJuk 오류");
            logger.info(ex.getMessage());
        }

        return cnt;
    }

    @Override
    public List<SungJukVO> selectSungJuk() {
        // 콜백 메서드 선언
        // 즉, SQL을 실행하고, 결과집합이 존재하는 경우
        // 스프링컨터이너에 의해 자동으로 호출하는 메서드
        RowMapper<SungJukVO> mapper = new SungJukMapper();

        return jdbcTemplate.query(selectSQL,mapper);
    }

    private class SungJukMapper implements RowMapper<SungJukVO> {
        @Override
        public SungJukVO mapRow(ResultSet rs, int num) throws SQLException {
            SungJukVO sj = new SungJukVO(rs.getString(2),
                            rs.getInt(3),rs.getInt(4),
                             rs.getInt(5));
            sj.setSjno(rs.getInt(1));
            return sj;
        }
    }

    @Override
    public SungJukVO seletOneSungJuk(int sjno) {
        SungJukVO sj = null;

        return sj;
    }

    @Override
    public int updateSungJuk(SungJukVO sj) {
        int cnt = -1;

        return cnt;
    }

    @Override
    public int deleteSungJuk(int sjno) {
        int cnt = -1;

        return cnt;
    }


}
