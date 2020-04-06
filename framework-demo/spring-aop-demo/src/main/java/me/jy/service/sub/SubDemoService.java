package me.jy.service.sub;

import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Service
public class SubDemoService {

    public void subHandle(int i) {
        System.out.println("sub handle: " + i);
    }
}
