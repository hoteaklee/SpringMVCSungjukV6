package riho.spring4.sungjukv6.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riho.spring4.sungjukv6.dao.SungJukV4DAO;
import riho.spring4.sungjukv6.dao.SungJukV6DAOImpl;
import riho.spring4.sungjukv6.model.SungJukVO;

import java.util.List;

@Service("sjsrv")
public class SungJukV6ServiceImpl implements SungJukV6Service {
    private SungJukV4DAO sjdao = null;
    private static final Logger logger = LogManager.getLogger(SungJukV6ServiceImpl.class);


    @Autowired
    public SungJukV6ServiceImpl(SungJukV4DAO sjdao) {
        this.sjdao = sjdao;
    }



    public boolean removeSungJuk(int sjno) {
        return false;
    }

    public boolean modifySungJuk(SungJukVO sj) {
        return false;
    }

    public SungJukVO readOneSungJuk(int sjno) {
        return null;
    }

    // 성적 리스트 받아옴 (이름,국어,영어,수학)
    public List<SungJukVO> readSungJuk() {

        return sjdao.selectSungJuk();
    }

    // 성적 데이터 저장
    public boolean newSungJuk(SungJukVO sj) {
        boolean result = false;

        this.computeSungJuk(sj);
        logger.info(sj);    //잘넘어오는지 확인

        if (sjdao.insertSungJuk(sj) > 0) result = true;
        return result;
    }


    //성적 데이터 지정
    public void computeSungJuk(SungJukVO sj) {
        sj.setTot( sj.getKor() + sj.getEng() + sj.getMat() );
        sj.setAvg( (double) sj.getTot() / 3 );

        switch ((int)(sj.getAvg()/10)) {
            case 10: case 9: sj.setGrd('수'); break;
            case 8: sj.setGrd('우'); break;
            case 7: sj.setGrd('미'); break;
            case 6: sj.setGrd('양'); break;
            default: sj.setGrd('가'); break;
        }
    }

}
