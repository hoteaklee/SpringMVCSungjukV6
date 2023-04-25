package riho.spring4.sungjukv6.dao;

import riho.spring4.sungjukv6.model.SungJukVO;

import java.util.List;

public interface SungJukV4DAO {
        int insertSungJuk(SungJukVO sj);
        List<SungJukVO> selectSungJuk();
        SungJukVO seletOneSungJuk(int sjno);
        int updateSungJuk(SungJukVO sj);
        int deleteSungJuk(int sjno);

}
