package com.training.suntravels.service;

import com.training.suntravels.domain.SearchRequestDTO;
import com.training.suntravels.domain.SearchResultDTO;
import org.springframework.transaction.annotation.Transactional;

public interface SearchService
{
	SearchResultDTO getSearchResult( SearchRequestDTO searchRequestDTO, boolean isDeepSearch );
}
