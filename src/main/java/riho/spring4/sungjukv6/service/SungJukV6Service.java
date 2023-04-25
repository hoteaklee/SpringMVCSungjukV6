package riho.spring4.sungjukv6.service;


import riho.spring4.sungjukv6.model.SungJukVO;

import java.util.List;

public interface SungJukV6Service {

    void newSungJuk();
    List<SungJukVO> readSungJuk();
    void readOneSungJuk();
    void modifySungJuk();
    void removeSungJuk();
    void computeSungJuk(SungJukVO sj);

}