package com.etiqa.onlinestore.dto;

public class PaginatedResponse<T> extends ApiResponse<T> {
    private PaginationMeta meta;

    public PaginatedResponse(boolean succeeded, String code, T object, PaginationMeta meta) {
        super(succeeded, code, object);
        this.meta = meta;
    }

    public static <T> PaginatedResponse<T> success(T data, PaginationMeta meta) {
        return new PaginatedResponse<>(true, "200", data, meta);
    }
}
