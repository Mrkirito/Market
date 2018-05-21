package com.hangjia.bxj.vo.compare;

import java.util.Comparator;

import com.hangjia.bxj.vo.right.ResourceDTO;



/**
 * @author yaoy
 * @since 2016-06-16
 */
public class ResourceComparator implements Comparator<ResourceDTO> {

    public int compare(ResourceDTO o1, ResourceDTO o2) {
        if (null == o1 && null == o2) {
            return 0;
        }
        else if (null == o1 && null != o2) {
            return -1;
        }
        else if (null != o1 && null == o2) {
            return 1;
        }
        else {
            int sort1 = 0;
            int sort2 = 0;
            if (null != o1.getSort()) {
                sort1 = o1.getSort().intValue();
            }
            if (null != o2.getSort()) {
                sort2 = o2.getSort().intValue();
            }
            return sort1 - sort2;
        }
    }

}
