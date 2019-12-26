package com.moyuaninfo.cloud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 逻辑分页工具类
 *
 * @author yixugang
 * @version 1.1
 * @Description 逻辑分页插件，调用静态方法SetStartPage传入需要分页的List集合即可
 */
public class PageHelper {
    public static Map<String, Object> SetStartPage(List<?> list, int pageNow, int Size) {
//        if (list != null && list.size() > 0) {
            boolean isFristPage = false;
            boolean isLastPage = false;
            boolean haveNexPage = false;
            boolean havePerPage = false;
            int pageSize = 0;
            int totalRow = list.size();
            int fromIndex = (pageNow - 1) * Size;
            int toIndex = pageNow * Size;
            if (fromIndex == 0) {
                isFristPage = true;
            } else if (!isFristPage) {
                havePerPage = true;
            }
            if (toIndex >= totalRow) {
                toIndex = totalRow;
                isLastPage = true;
            } else if (!isLastPage) {
                haveNexPage = true;
            }
            if (fromIndex > toIndex) {
                fromIndex = toIndex - Size < 0 ? 0 : toIndex - Size;
            }
            if (totalRow % Size == 0) {
                pageSize = totalRow / Size;
            } else {
                pageSize = totalRow / Size + 1;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("pageIndex", pageNow >= pageSize ? pageSize : pageNow);
            map.put("totalPage", pageSize);
            map.put("totalCount", totalRow);
            map.put("pageSize", Size);
            map.put("isFristPage", isFristPage);
            map.put("isLastPage", isLastPage);
            map.put("haveNexPage", haveNexPage);
            map.put("havePerPage", havePerPage);
            map.put("list", list.subList(fromIndex, toIndex));
            return map;
//        } else {
//            return null;
//        }

    }
}
