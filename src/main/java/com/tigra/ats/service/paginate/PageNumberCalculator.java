package com.tigra.ats.service.paginate;

public class PageNumberCalculator {
    public static int calcNumberOfPages(int totalPages) {
        int numberOfPages = 1;
        if(totalPages > 0)
            numberOfPages = totalPages;

        return numberOfPages;
    }
}
