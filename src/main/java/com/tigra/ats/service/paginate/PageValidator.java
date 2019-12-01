package com.tigra.ats.service.paginate;

public class PageValidator {
    public static boolean isValidPage(int actualPageNumber, int numberOfPages) {
       return actualPageNumber > 0 && actualPageNumber <= numberOfPages;
    }

    public static boolean hasNextPage(int actualPageNumber, int numberOfPages) {
        return numberOfPages > actualPageNumber;
    }

    public static boolean hasPrevPage(int actualPageNumber) {
        return 1 < actualPageNumber;
    }
}
