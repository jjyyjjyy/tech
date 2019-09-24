package me.jy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jy
 */
@Service
public class AService {

    @Autowired
    private BService bService;

    @Transactional(propagation = Propagation.MANDATORY)
    public void assertNoTx() {
        // ex surely.
    }

    @Transactional
    public void callB() {
        bService.assertShouldNotInExistingTx();
    }

}
