export function generatePageNumbers(pageNumber: number, totalPages: number): (number | string)[] {
    if (totalPages < 10) {
        return Array.from({ length: totalPages }, (_, idx) => 1 + idx);
    }
    if (pageNumber <= 5) {
        return [1, 2, 3, 4, 5, 6, '...', totalPages - 1, totalPages];
    }
    if (pageNumber >= totalPages - 5) {
        return [1, 2, '...', totalPages - 5, totalPages - 4, totalPages - 3, totalPages - 2, totalPages - 1, totalPages];
    }
    return [1, 2, '...', pageNumber - 1, pageNumber, pageNumber + 1, '...', totalPages - 1, totalPages];
}