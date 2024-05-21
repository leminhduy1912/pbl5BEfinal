package com.pbl5.service;

import com.pbl5.dtos.Pagination.TheaterPaginationDTO;
import com.pbl5.helpers.respone.Message;
import com.pbl5.models.Theater;

public interface ITheaterService {
    // room management by admin

    Message findAllRoomWithPagination(TheaterPaginationDTO dto);
    Message changeStatus(Theater theater);
}
